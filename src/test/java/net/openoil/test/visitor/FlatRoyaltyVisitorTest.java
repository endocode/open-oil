package net.openoil.test.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.FlatRoyaltyElement;
import net.openoil.test.TestUtil;
import net.openoil.visitor.FlatRoyaltyVisitor;

@RunWith(JUnit4.class)
public class FlatRoyaltyVisitorTest {

    @Test
    public void royaltyEquality() {
        FlatRoyaltyElement flat = Harness.getFlatRoyaltyElement();
        FlatRoyaltyVisitor visitor = new FlatRoyaltyVisitor();

        visitor.visit(Harness.getFilledPriceElement());
        visitor.visit(Harness.getFilledProductionElement());
        visitor.visit(flat);

        TestUtil.assertEquals(Harness.getFlatRoyalty(), flat.getRoyalty());
    }

}
