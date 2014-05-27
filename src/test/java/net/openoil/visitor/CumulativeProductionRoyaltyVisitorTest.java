package net.openoil.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.CumulativeProductionRoyaltyElement;

import net.openoil.TestUtil;

@RunWith(JUnit4.class)
public class CumulativeProductionRoyaltyVisitorTest {

    @Test
    public void royaltyEquality() {
        CumulativeProductionRoyaltyElement royalty = Harness.getCumulativeProductionRoyaltyElement();
        CumulativeProductionRoyaltyVisitor visitor = new CumulativeProductionRoyaltyVisitor();

        visitor.visit(Harness.getFilledYearElement());
        visitor.visit(Harness.getFilledPriceElement());
        visitor.visit(Harness.getFilledProductionElement());
        visitor.visit(royalty);

        TestUtil.assertEquals(Harness.getCumulativeProductionRoyalty(), royalty.getCumulativeProductionRoyalty());
    }

}
