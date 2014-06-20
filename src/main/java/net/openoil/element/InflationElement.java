package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class InflationElement implements IContractElement {

    private List<BigDecimal> inflationRate;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getInflationRate() {
        return inflationRate;
    }

    public void setInflationRate(List<BigDecimal> rate) {
        this.inflationRate = rate;
    }

}
