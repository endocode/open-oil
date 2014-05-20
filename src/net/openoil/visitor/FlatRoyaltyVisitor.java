package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.CapexElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;

/**
 * A flat royalty is a simple percentage of gross sales.
 */
public class FlatRoyaltyVisitor implements IContractElementVisitor {

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
    public void visit(YearElement year) {
        // Do nothing
    }

    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        // Do nothing
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
            productionThisYear = this.production.get(i);
            grossSales.add(priceThisYear.multiply(productionThisYear));
        }

        // Calculate royalty (grossSales * royaltyRate)
        List<BigDecimal> royaltyRate = flatRoyaltyElement.getRoyaltyRate();
        List<BigDecimal> royalty = new ArrayList<BigDecimal>();
        BigDecimal royaltyThisYear;

        for (int i = 0; i < grossSales.size(); i++) {
            // Royalty is a single data point (hence get from index 0)
            royaltyThisYear = grossSales.get(i).multiply(royaltyRate.get(0));
            royalty.add(royaltyThisYear);
        }

        flatRoyaltyElement.setRoyalty(royalty);
    }

    @Override
    public void visit(CapexElement capexElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(OpexElement opexElement) {
        // Do nothing.
        return;
    }

}
