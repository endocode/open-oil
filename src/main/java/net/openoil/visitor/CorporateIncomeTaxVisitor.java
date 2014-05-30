package net.openoil.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.CorporateIncomeTaxElement;
import net.openoil.element.ProductionSharingRFactorElement;
import net.openoil.element.ProductionSharingTrancheElement;
import net.openoil.element.ProfitOilElement;
import net.openoil.element.YearElement;

public class CorporateIncomeTaxVisitor extends DefaultVisitor {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> profitOil = new ArrayList<BigDecimal>();

    private List<BigDecimal> governmentProfitOil = new ArrayList<BigDecimal>();

    @Override
    public void visit(YearElement year) {
        this.year = year.getYear();
    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        this.profitOil = profitOilElement.getProfitOil();
    }

    @Override
    public void visit(
            ProductionSharingTrancheElement productionSharingTrancheElement) {

        // Assumes production sharing is *either* via tranche or R factor
        if (null != productionSharingTrancheElement
                && !productionSharingTrancheElement.getGovernmentShare()
                        .isEmpty()) {

            this.governmentProfitOil = productionSharingTrancheElement
                    .getGovernmentShare();
        }

    }

    @Override
    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement) {
        // Assumes production sharing is *either* via tranche or R factor
        if (null != productionSharingRFactorElement
                && !productionSharingRFactorElement.getGovernmentShare()
                        .isEmpty()) {

            this.governmentProfitOil = productionSharingRFactorElement
                    .getGovernmentShare();
        }
    }

    @Override
    public void visit(CorporateIncomeTaxElement corporateIncomeTaxElement) {
        // Was corporate income tax rate provided?
        if (null == corporateIncomeTaxElement.getTaxRate()
                || corporateIncomeTaxElement.getTaxRate().isEmpty()) {
            return;
        }

        List<BigDecimal> corporateIncomeTax = new ArrayList<BigDecimal>();
        BigDecimal corporateIncomeTaxRate = corporateIncomeTaxElement
                .getTaxRate().get(0).movePointLeft(2);

        BigDecimal preTaxProfit;
        BigDecimal taxThisYear;
        for (int y = 0; y < year.size(); y++) {
            preTaxProfit = profitOil.get(y)
                    .subtract(governmentProfitOil.get(y));
            taxThisYear = preTaxProfit.multiply(corporateIncomeTaxRate);

            corporateIncomeTax.add(taxThisYear.setScale(2, RoundingMode.UP));
        }

        corporateIncomeTaxElement.setTax(corporateIncomeTax);
    }

}
