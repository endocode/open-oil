package net.openoil.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.CostRecoveryElement;

import net.openoil.TestUtil;

@RunWith(JUnit4.class)
public class CostRecoveryVisitorTest {

    @Test
    public void recoveryEquality() {
        CostRecoveryElement costRecovery = Harness.getCostRecoveryElement();
        CostRecoveryVisitor visitor = new CostRecoveryVisitor();

        visitor.visit(Harness.getFilledPriceElement());
        visitor.visit(Harness.getFilledProductionElement());
        visitor.visit(Harness.getFilledFlatRoyaltyElement());
        visitor.visit(Harness.getFilledCapexElement());
        visitor.visit(Harness.getFilledOpexElement());
        visitor.visit(Harness.getFilledDailyProductionRoyaltyElement());
        visitor.visit(Harness.getFilledCumulativeProductionRoyaltyElement());
        visitor.visit(costRecovery);

        TestUtil.assertEquals(Harness.getCostRecovery(), costRecovery.getCostRecovery());
        TestUtil.assertEquals(Harness.getCumulativeRecoverableCosts(), costRecovery.getCumulativeRecoverableCosts());
    }

}
