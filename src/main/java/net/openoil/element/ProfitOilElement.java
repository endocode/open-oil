package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class ProfitOilElement implements IContractElement {

    private List<BigDecimal> profitOil;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getProfitOil() {
        return profitOil;
    }

    public void setProfitOil(List<BigDecimal> profitOil) {
        this.profitOil = profitOil;
    }

}
