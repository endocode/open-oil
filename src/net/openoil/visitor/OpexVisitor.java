package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.CapexElement;
import net.openoil.element.CorporateIncomeTaxElement;
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

public class OpexVisitor implements IContractElementVisitor {

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    @Override
    public void visit(PriceElement price) {
        // Do nothing.
        return;
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
        // Do nothing.
        return;
    }

    @Override
    public void visit(CapexElement capexElement) {
        // Do nothing.
        return;
    }

    /**
     * Calculates the total operating expenditure (production * opexPerBarrel).
     */
    @Override
    public void visit(OpexElement opexElement) {
        if (null == opexElement.getOpexPerBarrel()
                || opexElement.getOpexPerBarrel().size() == 0) {
            return;
        }

        // Single data point (hence get 0)
        BigDecimal opexPerBarrel = opexElement.getOpexPerBarrel().get(0);
        List<BigDecimal> opex = new ArrayList<BigDecimal>();

        for (int i = 0; i < production.size(); i++) {
            opex.add(production.get(i).multiply(opexPerBarrel));
        }

        opexElement.setOpex(opex);
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
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            ProductionSharingTrancheElement productionSharingTrancheElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(CorporateIncomeTaxElement corporateIncomeTax) {
        // Do nothing.
        return;
    }

}
