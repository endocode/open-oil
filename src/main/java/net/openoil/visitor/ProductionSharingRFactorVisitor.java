package net.openoil.visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.CapexElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.OpexElement;
import net.openoil.element.ProductionSharingRFactorElement;
import net.openoil.element.ProfitOilElement;
import net.openoil.element.YearElement;

public class ProductionSharingRFactorVisitor extends DefaultVisitor {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> capex = new ArrayList<BigDecimal>();

    private List<BigDecimal> opex = new ArrayList<BigDecimal>();

    private List<BigDecimal> profitOil = new ArrayList<BigDecimal>();

    private List<BigDecimal> recoveredCosts = new ArrayList<BigDecimal>();

    @Override
    public void visit(YearElement year) {
        this.year = year.getYear();
    }

    @Override
    public void visit(CapexElement capexElement) {
        this.capex = capexElement.getCapex();
    }

    @Override
    public void visit(OpexElement opexElement) {
        this.opex = opexElement.getOpex();
    }

    @Override
    public void visit(CostRecoveryElement costRecoveryElement) {
        this.recoveredCosts = costRecoveryElement.getCostRecovery();
    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        this.profitOil = profitOilElement.getProfitOil();
    }

    @Override
    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement) {

        // Does contract use R factor?
        List<Boolean> useRFactor = productionSharingRFactorElement.getRFactor();
        if (null == useRFactor || useRFactor.size() == 0) {
            return;
        }
        if (!useRFactor.get(0).booleanValue()) {
            // Not using r factor
            return;
        }

        List<Double> rFactor = new ArrayList<Double>();
        // rFactor is automatically 0 in the first year.
        rFactor.add(0d);

        BigDecimal cumulativeCapex = BigDecimal.ZERO;
        BigDecimal cumulativeOpex = BigDecimal.ZERO;
        double rFactorThisYear = 0d;

        BigDecimal profitOilThisYear;

        List<BigDecimal> governmentShare = new ArrayList<BigDecimal>();
        List<BigDecimal> companyShare = new ArrayList<BigDecimal>();

        for (int y = 0; y < year.size(); y++) {

            cumulativeCapex = cumulativeCapex.add(capex.get(y));
            cumulativeOpex = cumulativeOpex.add(opex.get(y));
            profitOilThisYear = profitOil.get(y);

            // If there's no profit oil to split, next year's R-Factor is 0.
            if (profitOilThisYear.compareTo(BigDecimal.ZERO) == 0) {
                rFactor.add(0d);
                governmentShare.add(BigDecimal.ZERO);
                companyShare.add(BigDecimal.ZERO);
                continue;
            }

            rFactorThisYear = rFactor.get(y);

            // Determine government's share (P) according to the R-Factor table
            // TODO Some way for user to enter this is needed; for now the table
            // is hard-coded
            // when R <= 1, P = 0
            // when 1 < R <= 1.3, P = 0.5
            // when 1.3 < R <= 2.5, P = 0.7 - [ (2.5 - R) / (2.5-1.3) ] * 0.2
            // when R > 2.5, P = 0.7
            double P = 0;
            if (rFactorThisYear <= 1) {
                P = 0d;
            } else if (rFactorThisYear > 1 && rFactorThisYear <= 1.3) {
                P = 0d;
            } else if (rFactorThisYear > 1.3 && rFactorThisYear < 2.5) {
                P = 0.7d - ((2.5d - rFactorThisYear) / 2.5d - 1.3d) * 0.2d;
            } else if (rFactorThisYear > 2.5) {
                P = 0.7d;
            }

            BigDecimal governmentProfit = profitOilThisYear
                    .multiply(new BigDecimal(P));
            BigDecimal companyProfit = profitOilThisYear
                    .multiply(new BigDecimal(1 - P));

            governmentShare.add(governmentProfit.setScale(2, RoundingMode.UP));
            companyShare.add(companyProfit.setScale(2, RoundingMode.UP));

            // Determine next year's R-Factor
            // R_n+1 = X / Y, where:
            // X = RecoveredCost_n + companyShareProfitOil_n
            // Y = CumulativeOpex_n + CumulativeCapex_n
            BigDecimal companyShareProfitOil = profitOilThisYear
                    .multiply(new BigDecimal(rFactor.get(y)));
            BigDecimal X = recoveredCosts.get(y).add(companyShareProfitOil);
            BigDecimal Y = cumulativeCapex.add(cumulativeOpex);
            rFactor.add(X.divide(Y, new MathContext(2, RoundingMode.DOWN))
                    .doubleValue());
        }

        productionSharingRFactorElement.setGovernmentShare(governmentShare);
        productionSharingRFactorElement.setCompanyShare(companyShare);
    }

}
