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

/**
 * A visitor implements a step of the algorithm. It visits all elements of the
 * contract.
 */
public interface IContractElementVisitor {

    public void visit(YearElement year);

    public void visit(PriceElement price);

    public void visit(ProductionElement production);

    public void visit(SurfaceRentalElement surfaceRentalElement);

    public void visit(FlatRoyaltyElement flatRoyaltyElement);

    public void visit(CapexElement capexElement);

    public void visit(OpexElement opexElement);

    public void visit(CostRecoveryElement costRecoveryElement);

    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement);

    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement);

    public void visit(ProfitOilElement profitOilElement);

    public void visit(
            ProductionSharingTrancheElement productionSharingTrancheElement);

    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement);

    public void visit(CorporateIncomeTaxElement corporateIncomeTax);

    public void visit(StateParticipationElement stateParticipationElement);

}
