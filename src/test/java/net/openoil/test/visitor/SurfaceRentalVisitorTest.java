package net.openoil.test.visitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.openoil.element.SurfaceRentalElement;
import net.openoil.test.TestUtil;
import net.openoil.visitor.SurfaceRentalVisitor;

@RunWith(JUnit4.class)
public class SurfaceRentalVisitorTest {

    @Test
    public void rentalEquality() {
        SurfaceRentalElement rental = Harness.getRentalElement();
        SurfaceRentalVisitor visitor = new SurfaceRentalVisitor();

        visitor.visit(rental);

        TestUtil.assertEquals(Harness.getSurfaceRental(), rental.getSurfaceRental());
    }

}
