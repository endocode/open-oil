package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.openoil.element.CumulativeProductionRoyaltyElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.YearElement;

public class CumulativeProductionRoyaltyVisitor extends DefaultVisitor {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    private List<BigDecimal> price = new ArrayList<BigDecimal>();

    @Override
    public void visit(YearElement year) {
        this.year = year.getYear();
    }

    @Override
    public void visit(PriceElement price) {
        this.price = price.getPrice();
    }

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    @Override
    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement) {

        List<Map<String, BigDecimal>> tranches = cumulativeProductionRoyaltyElement
                .getTranchTable();

        // If no tranche provided, do nothing.
        if (null == tranches || tranches.isEmpty()) {
            return;
        }

        List<BigDecimal> royalty = new ArrayList<BigDecimal>();
        BigDecimal productionThisYear = BigDecimal.ZERO;

        for (int y = 0; y < year.size(); y++) {
            // Calculate cumulative production to this year
            productionThisYear = production.get(y);

            // If cumulative production is still 0, skip to the next year
            if (productionThisYear.compareTo(BigDecimal.ZERO) == 0) {
                royalty.add(BigDecimal.ZERO);
                continue;
            }

            // Find which top tranche, N, the cumulativeProduction falls into.
            // This figure will be split between this tranche and all lower
            // tranches down to 1.
            int trancheN;
            Map<String, BigDecimal> tranche;
            BigDecimal lowerBound;
            BigDecimal upperBound;
            for (trancheN = 0; trancheN < tranches.size(); trancheN++) {
                tranche = tranches.get(trancheN);
                lowerBound = tranche
                        .get(CumulativeProductionRoyaltyElement.LOWER_MMBBLS);
                upperBound = tranche
                        .get(CumulativeProductionRoyaltyElement.UPPER_MMBBLS);

                if (productionThisYear.compareTo(lowerBound) >= 0
                        && productionThisYear.compareTo(upperBound) < 0) {
                    break;
                } else if (trancheN == tranches.size() - 1) {
                    // We've reached the top tranche
                    break;
                }
            }

            /*
             * For each tranche:
             *
             * volumeTrancheN = min(cumProd, trancheNCeiling) - tranchNFloor
             *
             * royaltyTrancheN = volumeTranchN * rateTranchN * priceThisYear
             */

            // Each royalty in this list is a royalty per tranche, but only for
            // this year.
            List<BigDecimal> royaltyThisYear = new ArrayList<BigDecimal>();
            BigDecimal ceilingTrancheN;
            BigDecimal floorTrancheN;
            BigDecimal rateTrancheN;
            BigDecimal volumeTrancheN;
            BigDecimal royaltyTrancheN;

            // We go down from the top tranche N to tranche 1
            for (; trancheN >= 0; trancheN--) {
                tranche = tranches.get(trancheN);
                ceilingTrancheN = tranche
                        .get(CumulativeProductionRoyaltyElement.UPPER_MMBBLS);
                floorTrancheN = tranche
                        .get(CumulativeProductionRoyaltyElement.LOWER_MMBBLS);

                // Convert to a percentage
                rateTrancheN = tranche.get(
                        CumulativeProductionRoyaltyElement.ROYALTY_RATE)
                        .movePointLeft(2);

                volumeTrancheN = productionThisYear.min(ceilingTrancheN)
                        .subtract(floorTrancheN);

                royaltyTrancheN = volumeTrancheN.multiply(rateTrancheN)
                        .multiply(price.get(y));

                royaltyThisYear.add(royaltyTrancheN);
            }

            // At the end
            // royaltyThisYear = sum(tranchN .. tranche1)
            BigDecimal totalRoyaltyThisYear = BigDecimal.ZERO;
            for (BigDecimal r : royaltyThisYear) {
                totalRoyaltyThisYear = totalRoyaltyThisYear.add(r);
            }
            royalty.add(totalRoyaltyThisYear);
        }

        cumulativeProductionRoyaltyElement
                .setCumulativeProductionRoyalty(royalty);
    }

}
