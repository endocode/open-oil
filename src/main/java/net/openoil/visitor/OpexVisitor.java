package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.InflationElement;
import net.openoil.element.OpexElement;
import net.openoil.element.ProductionElement;

public class OpexVisitor extends DefaultVisitor {

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    private List<BigDecimal> inflationRate = new ArrayList<BigDecimal>();

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    @Override
    public void visit(InflationElement inflationElement) {
        this.inflationRate = inflationElement.getInflationRate();
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

        // Single data points (hence get 0)
        BigDecimal opexPerBarrel = opexElement.getOpexPerBarrel().get(0);
        BigDecimal inflation = BigDecimal.ONE;
        if (null != inflationRate && !inflationRate.isEmpty()) {
            inflation = inflationRate.get(0).movePointLeft(2).add(inflation);
        }

        List<BigDecimal> opex = new ArrayList<BigDecimal>();

        for (int i = 0; i < production.size(); i++) {
            BigDecimal opexThisYear = production.get(i).multiply(
                    (opexPerBarrel).multiply(inflation));

            opex.add(opexThisYear);
        }

        opexElement.setOpex(opex);
    }

}
