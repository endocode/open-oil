package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class CostRecoveryElement implements IContractElement {

    // Percentage of total costs that can be recovered
    private List<BigDecimal> costRecoveryCeiling;

    // Recovered costs in $mm
    private List<BigDecimal> costRecovery;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getCostRecoveryCeiling() {
        return costRecoveryCeiling;
    }

    public void setCostRecoveryCeiling(List<BigDecimal> costRecoveryCeiling) {
        this.costRecoveryCeiling = costRecoveryCeiling;
    }

    public List<BigDecimal> getCostRecovery() {
        return costRecovery;
    }

    public void setCostRecovery(List<BigDecimal> costRecovery) {
        this.costRecovery = costRecovery;
    }
}
