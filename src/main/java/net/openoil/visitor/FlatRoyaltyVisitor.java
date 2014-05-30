package net.openoil.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;

/**
 * A flat royalty is a simple percentage of gross sales.
 */
public class FlatRoyaltyVisitor extends DefaultVisitor {

    private List<BigDecimal> price;

    private List<BigDecimal> production;

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    @Override
    public void visit(PriceElement price) {
        this.price = price.getPrice();
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        // TODO Better checking of whether inputs are supplied or not.
        if (flatRoyaltyElement.getRoyaltyRate().isEmpty()) {
            return;
        }

        // Calculate gross sales (production * price)
        List<BigDecimal> grossSales = new ArrayList<BigDecimal>();
        BigDecimal priceThisYear;
        BigDecimal productionThisYear;

        for (int i = 0; i < this.price.size(); i++) {
            priceThisYear = this.price.get(i);
            productionThisYear = this.production.get(i).movePointLeft(3);
            grossSales.add(priceThisYear.multiply(productionThisYear));
        }

        // Calculate royalty (grossSales * royaltyRate)
        List<BigDecimal> royaltyRate = flatRoyaltyElement.getRoyaltyRate();
        List<BigDecimal> royalty = new ArrayList<BigDecimal>();
        BigDecimal royaltyThisYear;

        for (int i = 0; i < grossSales.size(); i++) {
            // Royalty is a single data point (hence get from index 0)
            // Ensure to turn rate into a percentage
            royaltyThisYear = grossSales.get(i).multiply(
                    royaltyRate.get(0).movePointLeft(2));
            royalty.add(royaltyThisYear.setScale(2, RoundingMode.UP));
        }

        flatRoyaltyElement.setRoyalty(royalty);
    }
}
