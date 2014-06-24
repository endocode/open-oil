package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.InflationElement;
import net.openoil.element.PriceElement;
import net.openoil.element.YearElement;

public class PriceVisitor extends DefaultVisitor {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> inflationRate = new ArrayList<BigDecimal>();

    private List<BigDecimal> price = new ArrayList<BigDecimal>();

    public void visit(YearElement yearElement) {
        year = yearElement.getYear();
    }

    public void visit(InflationElement inflationElement) {
        inflationRate = inflationElement.getInflationRate();
    }

    public void visit(PriceElement priceElement) {
        List<BigDecimal> price = priceElement.getPrice();

        // Inflate prices (or keep them the same if we're not modelling
        // inflation)
        BigDecimal inflation = BigDecimal.ONE;
        if (null != inflationRate && !inflationRate.isEmpty()) {
            inflation = inflation.add(inflationRate.get(0).movePointLeft(2));
        }
        for (int y = 1; y < year.size(); y++) {
            BigDecimal priceLastYear = price.get(y - 1);
            BigDecimal inflatedPrice = priceLastYear.multiply(inflation);
            price.set(y, inflatedPrice);
        }

        priceElement.setPrice(price);
    }

}
