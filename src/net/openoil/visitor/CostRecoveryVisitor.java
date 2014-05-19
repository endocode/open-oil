package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.CapexElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;

public class CostRecoveryVisitor implements IContractElementVisitor {

    private List<BigDecimal> price;

    private List<BigDecimal> production;

    private List<BigDecimal> royalty;

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
    public void visit(YearElement year) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        this.royalty = flatRoyaltyElement.getRoyalty();

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

        // TODO Proper handling of missing values
        List<BigDecimal> costRecoveryCeiling = costRecoveryElement
                .getCostRecoveryCeiling();
        if (null == costRecoveryCeiling || costRecoveryCeiling.isEmpty()) {
            return;
        }

        List<BigDecimal> cumulativeRecoverableCost = new ArrayList<BigDecimal>();
        List<BigDecimal> costCarriedForward = new ArrayList<BigDecimal>();

        BigDecimal grossSalesN;
        BigDecimal costCarriedForwardPrevious;
        BigDecimal recoverableCostN;
        BigDecimal costRecoveryN;
        BigDecimal costRecoveryBaseN;

        for (int i = 0; i < production.size(); i++) {
            grossSalesN = production.get(i).multiply(price.get(i));

            // Bounds check... was there a previous year?
            costCarriedForwardPrevious = new BigDecimal(0);
            if (i > 0) {
                costCarriedForwardPrevious = costCarriedForward.get(i - 1);
            }

            // Cumulative recoverable cost for this year = sum of expenditures.
            recoverableCostN = costCarriedForwardPrevious.add(capex.get(i).add(
                    opex.get(i)));

            // Subtract the royalty
            costRecoveryBaseN = grossSalesN.subtract(royalty.get(i));

            // Multiply it by the recovery ceiling (a percentage)
            costRecoveryN = costRecoveryBaseN.multiply(costRecoveryCeiling.get(
                    i).divide(new BigDecimal(100)));

            cumulativeRecoverableCost.add(recoverableCostN);

            // If this year's total costs are greater than the gross sales, then
            // the remaining cost is carried forward to next year.
            if (recoverableCostN.compareTo(costRecoveryN) > 0) {
                costCarriedForward
                        .add(recoverableCostN.subtract(costRecoveryN));
            } else {
                costCarriedForward.add(new BigDecimal(0));
            }
        }

        costRecoveryElement.setCostRecovery(cumulativeRecoverableCost);
    }
}
