package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.element.CapexElement;
import net.openoil.element.CorporateIncomeTaxElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.CumulativeProductionRoyaltyElement;
import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.InflationElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.ProductionSharingRFactorElement;
import net.openoil.element.ProductionSharingTrancheElement;
import net.openoil.element.ProfitOilElement;
import net.openoil.element.StateParticipationElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;
import net.openoil.io.InputTable;

/**
 * This is the first step of the algorithm. It takes all values entered by the
 * user and initialises the elements of the contract with starting values.
 */
public class InitialisingVisitor extends DefaultVisitor {

    private InputTable inputs;

    public InitialisingVisitor(InputTable inputs) {
        this.inputs = inputs;
    }

    @Override
    public void visit(YearElement year) {
        year.setYear(inputs.getYear());
    }

    @Override
    public void visit(PriceElement price) {
        price.setPrice(inputs.getPrice());
    }

    @Override
    public void visit(ProductionElement production) {
        production.setProduction(inputs.getProduction());
    }

    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        surfaceRentalElement.setAcreage(inputs.getAcreage());
        surfaceRentalElement.setRentalPerKm(inputs.getRentalPerKm());
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        flatRoyaltyElement.setRoyaltyRate(inputs.getFlatRoyaltyRate());
    }

    @Override
    public void visit(CapexElement capexElement) {
        capexElement.setCapex(inputs.getCapex());
    }

    @Override
    public void visit(OpexElement opexElement) {
        opexElement.setOpexPerBarrel(inputs.getOpexPerBarrel());
    }

    @Override
    public void visit(CostRecoveryElement costRecoveryElement) {
        costRecoveryElement.setCostRecoveryCeiling(inputs
                .getCostRecoveryCeiling());
    }

    @Override
    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement) {
        List<List<BigDecimal>> tranches = inputs
                .getDailyProductionRoyaltyTranche();

        for (List<BigDecimal> t : tranches) {
            dailyProductionRoyaltyElement.addTranche(t);
        }
    }

    @Override
    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement) {
        List<List<BigDecimal>> tranches = inputs
                .getCumulativeProductionRoyaltyTranche();

        for (List<BigDecimal> t : tranches) {
            cumulativeProductionRoyaltyElement.addTranche(t);
        }
    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            ProductionSharingTrancheElement productionSharingTrancheElement) {
        List<List<BigDecimal>> tranches = inputs.getProductionSharingTranche();

        for (List<BigDecimal> t : tranches) {
            productionSharingTrancheElement.addTranche(t);
        }
    }

    @Override
    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement) {
        productionSharingRFactorElement.setrFactor(inputs.getRFactor());
    }

    @Override
    public void visit(CorporateIncomeTaxElement corporateIncomeTax) {
        corporateIncomeTax.setTaxRate(inputs.getCorporateIncomeTaxRate());
    }

    @Override
    public void visit(StateParticipationElement stateParticipationElement) {
        stateParticipationElement.setStateParticipationRate(inputs
                .getStateParticipationRate());
    }

    @Override
    public void visit(InflationElement inflationElement) {
        inflationElement.setInflationRate(inputs.getInflationRate());
    }
}
