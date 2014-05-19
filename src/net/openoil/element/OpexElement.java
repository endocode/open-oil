package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class OpexElement implements IContractElement {

    // Operating expenditures per barrel in $
    private List<BigDecimal> opexPerBarrel;

    // Operating expenditures in $mm
    private List<BigDecimal> opex;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getOpexPerBarrel() {
        return opexPerBarrel;
    }

    public void setOpexPerBarrel(List<BigDecimal> opexPerBarrel) {
        this.opexPerBarrel = opexPerBarrel;
    }

    public List<BigDecimal> getOpex() {
        return opex;
    }

    public void setOpex(List<BigDecimal> opex) {
        this.opex = opex;
    }

}
