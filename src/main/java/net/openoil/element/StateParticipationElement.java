package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class StateParticipationElement implements IContractElement {

    private List<BigDecimal> stateParticipationRate;

    private List<BigDecimal> stateParticipation;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getStateParticipationRate() {
        return stateParticipationRate;
    }

    public void setStateParticipationRate(List<BigDecimal> stateParticipationRate) {
        this.stateParticipationRate = stateParticipationRate;
    }

    public List<BigDecimal> getStateParticipation() {
        return stateParticipation;
    }

    public void setStateParticipation(List<BigDecimal> stateParticipation) {
        this.stateParticipation = stateParticipation;
    }

}
