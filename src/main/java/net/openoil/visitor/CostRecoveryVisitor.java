package net.openoil.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.CapexElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.CumulativeProductionRoyaltyElement;
import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;

public class CostRecoveryVisitor extends DefaultVisitor {

    private List<BigDecimal> price;

    private List<BigDecimal> production;

    private List<BigDecimal> flatRoyalty;

    private List<BigDecimal> dailyProductionRoyalty;

    private List<BigDecimal> cumulativeProductionRoyalty;

    private List<BigDecimal> capex;

    private List<BigDecimal> opex;

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    @Override
    public void visit(PriceElement price) {
        this.price = price.getPrice();

    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        this.flatRoyalty = flatRoyaltyElement.getRoyalty();
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
    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement) {
        this.dailyProductionRoyalty = dailyProductionRoyaltyElement
                .getDailyProductionRoyalty();
    }

    @Override
    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement) {
        this.cumulativeProductionRoyalty = cumulativeProductionRoyaltyElement
                .getCumulativeProductionRoyalty();
    }

    @Override
    public void visit(CostRecoveryElement costRecoveryElement) {

        // TODO Proper handling of missing values
        List<BigDecimal> costRecoveryCeiling = costRecoveryElement
                .getCostRecoveryCeiling();
        if (null == costRecoveryCeiling || costRecoveryCeiling.isEmpty()) {
            return;
        }

        List<BigDecimal> costsRecovered = new ArrayList<BigDecimal>();
        List<BigDecimal> cumulativeRecoverableCosts = new ArrayList<BigDecimal>();

        BigDecimal grossSalesN;
        BigDecimal cumulativeRecoverableCostPrevious;
        BigDecimal cumulativeRecoverableCostN;
        BigDecimal costRecoveryN;
        BigDecimal costRecoveryBaseN;

        for (int i = 0; i < production.size(); i++) {
            grossSalesN = production.get(i).movePointLeft(3)
                    .multiply(price.get(i));

            // Bounds check... was there a previous year?
            cumulativeRecoverableCostPrevious = BigDecimal.ZERO;
            if (i > 0) {
                cumulativeRecoverableCostPrevious = cumulativeRecoverableCosts
                        .get(i - 1);
            }

            // Cumulative recoverable cost for this year = sum of expenditures.
            cumulativeRecoverableCostN = cumulativeRecoverableCostPrevious
                    .add(capex.get(i).add(opex.get(i)));

            // Now work out the cost recovery base

            // CostRecoveryBase = (GrossSales - Royalty) * CostRecoveryCeiling
            // TODO Could represent royalty in a better way, e.g. have a
            // slightly more abstract Royalty class that more specific Royalties
            // inherit from.
            BigDecimal totalRoyalty = BigDecimal.ZERO;
            if (null != flatRoyalty && !flatRoyalty.isEmpty()) {
                totalRoyalty = totalRoyalty.add(flatRoyalty.get(i));
            }
            if (null != dailyProductionRoyalty
                    && !dailyProductionRoyalty.isEmpty()) {
                totalRoyalty = totalRoyalty.add(dailyProductionRoyalty.get(i));
            }
            if (null != cumulativeProductionRoyalty
                    && !cumulativeProductionRoyalty.isEmpty()) {
                totalRoyalty = totalRoyalty.add(cumulativeProductionRoyalty
                        .get(i));
            }
            costRecoveryBaseN = grossSalesN.subtract(totalRoyalty);

            // Multiply it by the recovery ceiling (make sure to convert to a
            // percentage)
            costRecoveryN = costRecoveryBaseN.multiply(costRecoveryCeiling.get(
                    i).movePointLeft(2));

            // TODO This is just a check! Is it correct?
            costRecoveryN = costRecoveryN.min(cumulativeRecoverableCostN);

            costsRecovered.add(costRecoveryN.setScale(2, RoundingMode.UP));

            cumulativeRecoverableCosts.add(cumulativeRecoverableCostN.subtract(
                    costRecoveryN).setScale(2, RoundingMode.UP));

        }

        costRecoveryElement.setCostRecovery(costsRecovered);
        costRecoveryElement
                .setCumulativeRecoverableCosts(cumulativeRecoverableCosts);
    }

}
