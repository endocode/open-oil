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
import net.openoil.io.OutputTable;

/**
 * The last step of the algorithm. This gathers all the values of the contract
 * after all steps have been run and puts them into an OutputTable.
 */
public class OutputVisitor extends DefaultVisitor {

    private OutputTable outputs;

    public OutputVisitor(OutputTable outputs) {
        this.outputs = outputs;
    }

    @Override
    public void visit(PriceElement price) {
        this.outputs.setPrice(price.getPrice());
    }

    @Override
    public void visit(ProductionElement production) {
        this.outputs.setProduction(production.getProduction());
    }

    @Override
    public void visit(YearElement year) {
        this.outputs.setYear(year.getYear());
    }

    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        this.outputs.setSurfaceRental(surfaceRentalElement.getSurfaceRental());
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        this.outputs.setFlatRoyalty(flatRoyaltyElement.getRoyalty());
    }

    @Override
    public void visit(CapexElement capexElement) {
        this.outputs.setCapex(capexElement.getCapex());
    }

    @Override
    public void visit(OpexElement opexElement) {
        this.outputs.setOpex(opexElement.getOpex());
    }

    public void visit(CostRecoveryElement costRecoveryElement) {
        this.outputs.setCostRecovery(costRecoveryElement.getCostRecovery());
        this.outputs.setCumulativeRecoverableCosts(costRecoveryElement
                .getCumulativeRecoverableCosts());
    }

    @Override
    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement) {
        this.outputs.setDailyProductionRoyalty(dailyProductionRoyaltyElement
                .getDailyProductionRoyalty());

    }

    @Override
    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement) {
        this.outputs
                .setCumulativeProductionRoyalty(cumulativeProductionRoyaltyElement
                        .getCumulativeProductionRoyalty());
    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        this.outputs.setProfitOil(profitOilElement.getProfitOil());
    }

    @Override
    public void visit(
            ProductionSharingTrancheElement productionSharingTrancheElement) {

        if (!productionSharingTrancheElement.getCompanyShare().isEmpty()) {
            this.outputs.setGovernmentShare(productionSharingTrancheElement
                    .getGovernmentShare());
            this.outputs.setCompanyShare(productionSharingTrancheElement
                    .getCompanyShare());
        }
    }

    @Override
    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement) {
        if (!productionSharingRFactorElement.getCompanyShare().isEmpty()) {
            this.outputs.setGovernmentShare(productionSharingRFactorElement
                    .getGovernmentShare());
            this.outputs.setCompanyShare(productionSharingRFactorElement
                    .getCompanyShare());
        }
    }

    @Override
    public void visit(CorporateIncomeTaxElement corporateIncomeTax) {
        this.outputs.setCorporateIncomeTax(corporateIncomeTax.getTax());
    }

    @Override
    public void visit(StateParticipationElement stateParticipationElement) {
        this.outputs.setStateParticipation(stateParticipationElement
                .getStateParticipation());
    }

}
