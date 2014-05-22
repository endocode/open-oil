package net.openoil.visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.openoil.element.CapexElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.CumulativeProductionRoyaltyElement;
import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.ProductionSharingRFactorElement;
import net.openoil.element.ProductionSharingTrancheElement;
import net.openoil.element.ProfitOilElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;

public class ProductionSharingTrancheVisitor implements IContractElementVisitor {

    private final int DAYS_PER_YEAR = 365;

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    private List<BigDecimal> profitOil = new ArrayList<BigDecimal>();

    @Override
    public void visit(YearElement year) {
        this.year = year.getYear();
    }

    @Override
    public void visit(PriceElement price) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        // TODO
    }

    @Override
    public void visit(CapexElement capexElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(OpexElement opexElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(CostRecoveryElement costRecoveryElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        this.profitOil = profitOilElement.getProfitOil();
    }

    @Override
    public void visit(
            ProductionSharingTrancheElement productionSharingTrancheElement) {

        List<Map<String, BigDecimal>> tranches = productionSharingTrancheElement
                .getTranchTable();

        // If no tranche provided, do nothing.
        if (null == tranches || tranches.isEmpty()) {
            return;
        }

        List<BigDecimal> governmentShare = new ArrayList<BigDecimal>();
        List<BigDecimal> companyShare = new ArrayList<BigDecimal>();

        BigDecimal productionThisYear;
        BigDecimal adp = BigDecimal.ZERO;
        BigDecimal daysPerYear = new BigDecimal(DAYS_PER_YEAR);

        for (int y = 0; y < year.size(); y++) {
            // Check if production is zero and skip this year if so.
            if (production.get(y).equals(BigDecimal.ZERO)) {
                governmentShare.add(BigDecimal.ZERO);
                companyShare.add(BigDecimal.ZERO);
                continue;
            }

            // Calculate the avg. daily production (ADP).
            productionThisYear = production.get(y);
            adp = productionThisYear.divide(daysPerYear, new MathContext(4,
                    RoundingMode.DOWN));

            // Find which tranche the ADP falls into. We will use that tranche
            // to calculate the shares.
            int trancheN;
            Map<String, BigDecimal> tranche;
            BigDecimal lowerBound;
            BigDecimal upperBound;
            BigDecimal governmentRateTrancheN = BigDecimal.ZERO;
            BigDecimal companyRateTrancheN = BigDecimal.ZERO;
            for (trancheN = 0; trancheN < tranches.size(); trancheN++) {
                tranche = tranches.get(trancheN);
                lowerBound = tranche
                        .get(ProductionSharingTrancheElement.LOWER_MBOPD);
                upperBound = tranche
                        .get(ProductionSharingTrancheElement.UPPER_MBOPD);

                if (adp.compareTo(lowerBound) > 0
                        && adp.compareTo(upperBound) < 0) {
                    // Convert numbers to percentages
                    governmentRateTrancheN = tranche.get(
                            ProductionSharingTrancheElement.GOV_SHARE)
                            .movePointLeft(2);
                    companyRateTrancheN = tranche.get(
                            ProductionSharingTrancheElement.COMPANY_SHARE)
                            .movePointLeft(2);
                    break;
                } else if (trancheN == tranches.size() - 1) {
                    // We've reached the top tranche
                    break;
                }
            }

            // For the tranche, N:
            // governmentShare = profitOil * governmentRateTrancheN
            // companyShare = profitOil * companyRateTrancheN
            BigDecimal governmentShareThisYear = profitOil.get(y).multiply(
                    governmentRateTrancheN);
            BigDecimal companyShareThisYear = profitOil.get(y).multiply(
                    companyRateTrancheN);

            governmentShare.add(governmentShareThisYear);
            companyShare.add(companyShareThisYear);
        }

        productionSharingTrancheElement.setGovernmentShare(governmentShare);
        productionSharingTrancheElement.setCompanyShare(companyShare);
    }

    @Override
    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement) {
        // Do nothing.
        return;
    }
}
