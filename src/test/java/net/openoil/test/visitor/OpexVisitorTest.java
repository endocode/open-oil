package net.openoil.test.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.OpexElement;
import net.openoil.test.TestUtil;
import net.openoil.visitor.OpexVisitor;

@RunWith(JUnit4.class)
public class OpexVisitorTest {

    @Test
    public void opexEquality() {
        OpexElement opex = Harness.getOpexElement();
        OpexVisitor visitor = new OpexVisitor();

        visitor.visit(Harness.getFilledProductionElement());
        visitor.visit(opex);

        TestUtil.assertEquals(Harness.getOpex(), opex.getOpex());
    }

}
