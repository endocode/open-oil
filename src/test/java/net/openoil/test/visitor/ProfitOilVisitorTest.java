package net.openoil.test.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.ProfitOilElement;
import net.openoil.test.TestUtil;
import net.openoil.visitor.ProfitOilVisitor;

@RunWith(JUnit4.class)
public class ProfitOilVisitorTest {

    @Test
    public void profitEquality() {
        ProfitOilElement profit = Harness.getProfitOilElement();
        ProfitOilVisitor visitor = new ProfitOilVisitor();

        visitor.visit(Harness.getFilledYearElement());
        visitor.visit(Harness.getFilledPriceElement());
        visitor.visit(Harness.getFilledProductionElement());
        visitor.visit(Harness.getFilledFlatRoyaltyElement());
        visitor.visit(Harness.getFilledCostRecoveryElement());
        visitor.visit(Harness.getFilledDailyProductionRoyaltyElement());
        visitor.visit(Harness.getFilledCumulativeProductionRoyaltyElement());
        visitor.visit(profit);

        TestUtil.assertEquals(Harness.getProfitOil(), profit.getProfitOil());
    }

}
