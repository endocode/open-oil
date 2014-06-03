package net.openoil.test.visitor;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.CorporateIncomeTaxElement;
import net.openoil.test.TestUtil;
import net.openoil.visitor.CorporateIncomeTaxVisitor;

@RunWith(JUnit4.class)
public class CorporateIncomeTaxVisitorTest {

    @Test
    public void taxEqualityViaTranches() {
        CorporateIncomeTaxElement tax = Harness.getCorporateIncomeTaxElement();
        CorporateIncomeTaxVisitor visitor = new CorporateIncomeTaxVisitor();

        visitor.visit(Harness.getFilledYearElement());
        visitor.visit(Harness.getFilledProfitOilElement());
        visitor.visit(Harness.getFilledProductionSharingTrancheElement());
        visitor.visit(tax);

        TestUtil.assertEquals(Harness.getTaxViaTranches(), tax.getTax());
    }

    @Test
    @Ignore
    public void taxEqualityViaRFactor() {
        CorporateIncomeTaxElement tax = Harness.getCorporateIncomeTaxElement();
        CorporateIncomeTaxVisitor visitor = new CorporateIncomeTaxVisitor();

        visitor.visit(Harness.getFilledYearElement());
        visitor.visit(Harness.getFilledProfitOilElement());
        visitor.visit(Harness.getFilledProductionSharingRFactorElement());
        visitor.visit(tax);

        TestUtil.assertEquals(Harness.getTaxViaRFactor(), tax.getTax());
    }

}
