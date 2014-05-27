package net.openoil.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.ProductionSharingTrancheElement;

import net.openoil.TestUtil;

@RunWith(JUnit4.class)
public class ProductionSharingTrancheVisitorTest {

    @Test
    public void shareEquality() {
        ProductionSharingTrancheElement share = Harness.getProductionSharingTrancheElement();
        ProductionSharingTrancheVisitor visitor = new ProductionSharingTrancheVisitor();

        visitor.visit(Harness.getFilledYearElement());
        visitor.visit(Harness.getFilledProductionElement());
        visitor.visit(Harness.getFilledProfitOilElement());
        visitor.visit(share);

        TestUtil.assertEquals(Harness.getGovernmentShare(), share.getGovernmentShare());
        TestUtil.assertEquals(Harness.getCompanyShare(), share.getCompanyShare());
    }

}
