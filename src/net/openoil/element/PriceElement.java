package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class PriceElement implements IContractElement {

    private List<BigDecimal> price;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(List<BigDecimal> price) {
        this.price = price;
    }

}
