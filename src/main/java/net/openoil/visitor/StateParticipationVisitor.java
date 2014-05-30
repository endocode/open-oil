package net.openoil.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.ProfitOilElement;
import net.openoil.element.StateParticipationElement;
import net.openoil.element.YearElement;

public class StateParticipationVisitor extends DefaultVisitor {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> profitOil = new ArrayList<BigDecimal>();

    @Override
    public void visit(YearElement year) {
        this.year = year.getYear();
    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        this.profitOil = profitOilElement.getProfitOil();
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

            stateParticipation.add(stateParticipationN.setScale(2,
                    RoundingMode.UP));
        }

        stateParticipationElement.setStateParticipation(stateParticipation);
    }
}
