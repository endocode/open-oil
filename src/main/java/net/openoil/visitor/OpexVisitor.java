package net.openoil.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.OpexElement;
import net.openoil.element.ProductionElement;

public class OpexVisitor extends DefaultVisitor {

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    /**
     * Calculates the total operating expenditure (production * opexPerBarrel).
     */
    @Override
    public void visit(OpexElement opexElement) {
        if (null == opexElement.getOpexPerBarrel()
                || opexElement.getOpexPerBarrel().size() == 0) {
            return;
        }

        // Single data point (hence get 0)
        BigDecimal opexPerBarrel = opexElement.getOpexPerBarrel().get(0);
        List<BigDecimal> opex = new ArrayList<BigDecimal>();

        for (int i = 0; i < production.size(); i++) {
            BigDecimal opexThisYear = production.get(i).movePointLeft(3)
                    .multiply(opexPerBarrel);

            opex.add(opexThisYear.setScale(2, RoundingMode.UP));
        }

        opexElement.setOpex(opex);
    }

}
