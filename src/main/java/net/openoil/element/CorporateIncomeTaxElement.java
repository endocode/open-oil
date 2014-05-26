package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class CorporateIncomeTaxElement implements IContractElement {

    private List<BigDecimal> taxRate;

    private List<BigDecimal> tax;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(List<BigDecimal> taxRate) {
        this.taxRate = taxRate;
    }

    public List<BigDecimal> getTax() {
        return tax;
    }

    public void setTax(List<BigDecimal> tax) {
        this.tax = tax;
    }

}
