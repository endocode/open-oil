package net.openoil.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.StateParticipationElement;

import net.openoil.TestUtil;

@RunWith(JUnit4.class)
public class StateParticipationVisitorTest {

    @Test
    public void shareEquality() {
        StateParticipationElement state = Harness.getStateParticipationElement();
        StateParticipationVisitor visitor = new StateParticipationVisitor();

        visitor.visit(Harness.getFilledYearElement());
        visitor.visit(Harness.getFilledProfitOilElement());
        visitor.visit(state);

        TestUtil.assertEquals(Harness.getStateParticipation(), state.getStateParticipation());
    }

}
