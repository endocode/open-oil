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
import net.openoil.element.StateParticipationElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;

public class StateParticipationVisitor implements IContractElementVisitor {

    private List<Integer> year = new ArrayList<Integer>();

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

    @Override
    public void visit(StateParticipationElement stateParticipationElement) {

        List<BigDecimal> stateParticipationRate = stateParticipationElement
                .getStateParticipationRate();

        // Was state participation supplied?
        if (null == stateParticipationRate || stateParticipationRate.isEmpty()) {
            return;
        }

        List<BigDecimal> stateParticipation = new ArrayList<BigDecimal>();

        BigDecimal stateParticipationN;
        BigDecimal participationRateN;
        for (int y = 0; y < year.size(); y++) {
            // Rate is a static value, therefore get first element
            participationRateN = stateParticipationRate.get(0).movePointLeft(2);
            stateParticipationN = profitOil.get(y).multiply(participationRateN);

            stateParticipation.add(stateParticipationN);
        }

        stateParticipationElement.setStateParticipation(stateParticipation);
    }
}
