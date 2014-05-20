package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class CapexElement implements IContractElement {

    // Capital expenditures in $mm
    private List<BigDecimal> capex;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getCapex() {
        return capex;
    }

    public void setCapex(List<BigDecimal> capex) {
        this.capex = capex;
    }

}
