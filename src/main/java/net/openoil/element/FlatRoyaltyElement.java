package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class FlatRoyaltyElement implements IContractElement {

    private List<BigDecimal> royaltyRate;

    // Amount in $mm
    private List<BigDecimal> royalty;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getRoyaltyRate() {
        return royaltyRate;
    }

    public void setRoyaltyRate(List<BigDecimal> royaltyRate) {
        this.royaltyRate = royaltyRate;
    }

    public List<BigDecimal> getRoyalty() {
        return royalty;
    }

    public void setRoyalty(List<BigDecimal> royalty) {
        this.royalty = royalty;
    }

}
