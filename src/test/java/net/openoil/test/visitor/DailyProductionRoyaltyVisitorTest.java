package net.openoil.test.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.test.TestUtil;
import net.openoil.visitor.DailyProductionRoyaltyVisitor;

@RunWith(JUnit4.class)
public class DailyProductionRoyaltyVisitorTest {

    @Test
    public void royaltyEquality() {
        DailyProductionRoyaltyElement royalty = Harness.getDailyProductionRoyaltyElement();
        DailyProductionRoyaltyVisitor visitor = new DailyProductionRoyaltyVisitor();

        visitor.visit(Harness.getFilledYearElement());
        visitor.visit(Harness.getFilledPriceElement());
        visitor.visit(Harness.getFilledProductionElement());
        visitor.visit(royalty);

        TestUtil.assertEquals(Harness.getDailyProductionRoyalty(), royalty.getDailyProductionRoyalty());
    }

}
