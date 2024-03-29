package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.CostRecoveryElement;
import net.openoil.element.CumulativeProductionRoyaltyElement;
import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.ProfitOilElement;
import net.openoil.element.YearElement;

public class ProfitOilVisitor extends DefaultVisitor {

    List<Integer> year;

    List<BigDecimal> price;

    List<BigDecimal> production;

    List<BigDecimal> totalRoyalty = new ArrayList<BigDecimal>();

    List<BigDecimal> costRecovery;

    @Override
    public void visit(YearElement year) {
        this.year = year.getYear();
    }

    @Override
    public void visit(PriceElement price) {
        this.price = price.getPrice();
    }

    @Override
    public void visit(ProductionElement production) {
        this.production = production.getProduction();
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        List<BigDecimal> flatRoyalty = flatRoyaltyElement.getRoyalty();

        if (totalRoyalty.isEmpty()) {
            initialiseRoyalty();
        }

        if (null == flatRoyalty || flatRoyalty.isEmpty()) {
            return;
        }

        for (int i = 0; i < flatRoyalty.size(); i++) {
            BigDecimal royaltyThisYear = totalRoyalty.get(i).add(
                    flatRoyalty.get(i));

            totalRoyalty.set(i, royaltyThisYear);
        }
    }

    @Override
    public void visit(CostRecoveryElement costRecoveryElement) {
        this.costRecovery = costRecoveryElement.getCostRecovery();
    }

    @Override
    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement) {
        List<BigDecimal> dprRoyalty = dailyProductionRoyaltyElement
                .getDailyProductionRoyalty();

        if (totalRoyalty.isEmpty()) {
            initialiseRoyalty();
        }

        if (null == dprRoyalty || dprRoyalty.isEmpty()) {
            return;
        }

        for (int i = 0; i < dprRoyalty.size(); i++) {
            BigDecimal royaltyThisYear = totalRoyalty.get(i).add(
                    dprRoyalty.get(i));

            totalRoyalty.set(i, royaltyThisYear);
        }
    }

    @Override
    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement) {
        List<BigDecimal> cprRoyalty = cumulativeProductionRoyaltyElement
                .getCumulativeProductionRoyalty();

        if (totalRoyalty.isEmpty()) {
            initialiseRoyalty();
        }

        if (null == cprRoyalty || cprRoyalty.isEmpty()) {
            return;
        }

        for (int i = 0; i < cprRoyalty.size(); i++) {
            BigDecimal royaltyThisYear = totalRoyalty.get(i).add(
                    cprRoyalty.get(i));

            totalRoyalty.set(i, royaltyThisYear);
        }
    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        List<BigDecimal> profitOil = new ArrayList<BigDecimal>();
        BigDecimal profitOilThisYear;
        BigDecimal grossSalesThisYear;

        for (int i = 0; i < year.size(); i++) {
            // Skip this year if there was no production
            if (production.get(i).compareTo(BigDecimal.ZERO) == 0) {
                profitOil.add(BigDecimal.ZERO);
                continue;
            }

            grossSalesThisYear = production.get(i).multiply(price.get(i));
            profitOilThisYear = grossSalesThisYear
                    .subtract(totalRoyalty.get(i))
                    .subtract(costRecovery.get(i));

            profitOil.add(profitOilThisYear);
        }

        profitOilElement.setProfitOil(profitOil);
    }

    private void initialiseRoyalty() {
        // Initialise totalRoyalty
        // TODO A royalty abstraction would be better
        for (int i = 0; i < year.size(); i++) {
            totalRoyalty.add(BigDecimal.ZERO);
        }
    }

}
