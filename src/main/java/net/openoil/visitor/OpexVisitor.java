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
        List<BigDecimal> opexPerBarrel = new ArrayList<BigDecimal>();
        opexPerBarrel.add(opexElement.getOpexPerBarrel().get(0));

        List<BigDecimal> opex = new ArrayList<BigDecimal>();

        // Inflate opex prices (or keep them the same if we're not modelling
        // inflation)
        BigDecimal inflation = BigDecimal.ONE;
        if (null != inflationRate && !inflationRate.isEmpty()) {
            inflation = inflation.add(inflationRate.get(0).movePointLeft(2));
        }
        for (int y = 1; y < production.size(); y++) {
            BigDecimal opexLastYear = opexPerBarrel.get(y - 1);
            BigDecimal inflatedOpex = opexLastYear.multiply(inflation);
            opexPerBarrel.add(inflatedOpex);
        }
        // Now calculate actual opex
        for (int y = 0; y < production.size(); y++) {
            BigDecimal opexThisYear = production.get(y).multiply(
                    opexPerBarrel.get(y));
            opex.add(opexThisYear);
        }

        opexElement.setOpex(opex);
    }
}
