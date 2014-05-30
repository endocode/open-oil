package net.openoil.visitor;

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
import net.openoil.element.StateParticipationElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;

public abstract class DefaultVisitor implements IContractElementVisitor {

    @Override
    public void visit(YearElement year) {
        return;

    }

    @Override
    public void visit(PriceElement price) {
        return;

    }

    @Override
    public void visit(ProductionElement production) {
        return;

    }

    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        return;

    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        return;

    }

    @Override
    public void visit(CapexElement capexElement) {
        return;

    }

    @Override
    public void visit(OpexElement opexElement) {
        return;

    }

    @Override
    public void visit(CostRecoveryElement costRecoveryElement) {
        return;

    }

    @Override
    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement) {
        return;

    }

    @Override
    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement) {
        return;

    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        return;

    }

    @Override
    public void visit(
            ProductionSharingTrancheElement productionSharingTrancheElement) {
        return;

    }

    @Override
    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement) {
        return;

    }

    @Override
    public void visit(CorporateIncomeTaxElement corporateIncomeTax) {
        return;

    }

    @Override
    public void visit(StateParticipationElement stateParticipationElement) {
        return;

    }

}
