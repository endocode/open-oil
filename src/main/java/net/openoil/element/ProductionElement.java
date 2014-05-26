package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

/**
 * Production describes how many thousand barrels of oil are produced
 * on a year-by-year basis.
 */
public class ProductionElement implements IContractElement {

    private List<BigDecimal> production;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getProduction() {
        return production;
    }

    public void setProduction(List<BigDecimal> production) {
        this.production = production;
    }

}
