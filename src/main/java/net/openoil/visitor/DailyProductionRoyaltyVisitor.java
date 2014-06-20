package net.openoil.visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.YearElement;

public class DailyProductionRoyaltyVisitor extends DefaultVisitor {

    private final int DAYS_PER_YEAR = 365;

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    private List<BigDecimal> price = new ArrayList<BigDecimal>();

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    @Override
    public void visit(PriceElement price) {
        this.price = price.getPrice();
    }

    @Override
    public void visit(YearElement year) {
        this.year = year.getYear();
    }

    @Override
    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement) {

        List<Map<String, BigDecimal>> tranches = dailyProductionRoyaltyElement
                .getTranchTable();

        // If no tranche provided, do nothing.
        if (null == tranches || tranches.isEmpty()) {
            return;
        }

        List<BigDecimal> royalty = new ArrayList<BigDecimal>();

        BigDecimal productionThisYear;
        BigDecimal adp = BigDecimal.ZERO;
        BigDecimal daysPerYear = new BigDecimal(DAYS_PER_YEAR);

        for (int y = 0; y < year.size(); y++) {
            // Check if production is zero and skip this year if so.
            if (production.get(y).compareTo(BigDecimal.ZERO) == 0) {
                royalty.add(BigDecimal.ZERO);
                continue;
            }

            // Calculate the avg. daily production (ADP).
            // Example calculations provided use rounding down.
            productionThisYear = production.get(y);
            adp = productionThisYear.divide(daysPerYear, new MathContext(4,
                    RoundingMode.DOWN));

            /*
             * Find which tranche the ADP falls into. The volume for each
             * tranche needs to be split between the top tranche, N, and all
             * lower tranches down to 1.
             */
            int trancheN;
            Map<String, BigDecimal> tranche;
            BigDecimal lowerBound;
            BigDecimal upperBound;
            for (trancheN = 0; trancheN < tranches.size(); trancheN++) {
                tranche = tranches.get(trancheN);
                lowerBound = tranche
                        .get(DailyProductionRoyaltyElement.LOWER_MBOPD);
                upperBound = tranche
                        .get(DailyProductionRoyaltyElement.UPPER_MBOPD);

                if (adp.compareTo(lowerBound) > 0
                        && adp.compareTo(upperBound) < 0) {
                    break;
                } else if (trancheN == tranches.size() - 1) {
                    // We've reached the top tranche
                    break;
                }
            }

            /*
             * For each tranche:
             * 
             * volumeTrancheN = min(adp, trancheNCeiling) - tranchNFloor
             * 
             * royaltyTrancheN = volumeTranchN * rateTranchN * priceThisYear *
             * daysPerYear
             */

            // Each royalty in this list is a royalty per tranche, but only for
            // this year.
            List<BigDecimal> royaltyThisYear = new ArrayList<BigDecimal>();
            BigDecimal royaltyTrancheN;
            BigDecimal ceilingTrancheN;
            BigDecimal floorTrancheN;
            BigDecimal rateTrancheN;
            BigDecimal volumeTrancheN;

            // We go down from the top tranche N to tranche 1
            for (; trancheN >= 0; trancheN--) {
                tranche = tranches.get(trancheN);
                ceilingTrancheN = tranche
                        .get(DailyProductionRoyaltyElement.UPPER_MBOPD);
                floorTrancheN = tranche
                        .get(DailyProductionRoyaltyElement.LOWER_MBOPD);

                // Convert to a percentage
                rateTrancheN = tranche.get(
                        DailyProductionRoyaltyElement.ROYALTY_RATE)
                        .movePointLeft(2);

                volumeTrancheN = adp.min(ceilingTrancheN).subtract(
                        floorTrancheN);

                royaltyTrancheN = volumeTrancheN.multiply(rateTrancheN)
                        .multiply(price.get(y)).multiply(daysPerYear);

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

        dailyProductionRoyaltyElement.setDailyProductionRoyalty(royalty);
    }
}
