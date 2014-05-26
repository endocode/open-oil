package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import net.openoil.element.SurfaceRentalElement;

public class Harness {

    private static final int YEAR_COUNT = 8;

    // SURFACE RENTAL

    public static SurfaceRentalElement getFilledRentalElement() {
        SurfaceRentalElement rental = getRentalElement();

        rental.setSurfaceRental(getSurfaceRental());

        return rental;
    }

    public static SurfaceRentalElement getRentalElement() {
        SurfaceRentalElement rental = new SurfaceRentalElement();

        rental.setRentalPerKm(getRentalPerKm());
        rental.setAcreage(getAcreage());

        return rental;
    }

    public static List<BigDecimal> getSurfaceRental() {
        List<BigDecimal> expected = new ArrayList<BigDecimal>(YEAR_COUNT);

        expected.add(new BigDecimal(24000));
        expected.add(new BigDecimal(28700));
        expected.add(new BigDecimal(33600));
        expected.add(new BigDecimal(38700));
        expected.add(new BigDecimal(44000));
        expected.add(new BigDecimal(49500));
        expected.add(new BigDecimal(55200));
        expected.add(new BigDecimal(61100));

        assertEquals(YEAR_COUNT, expected.size());

        return expected;
    }

    private static List<BigDecimal> getRentalPerKm() {
        List<BigDecimal> rentals = new ArrayList<BigDecimal>(YEAR_COUNT);

        rentals.add(new BigDecimal(30));
        rentals.add(new BigDecimal(35));
        rentals.add(new BigDecimal(40));
        rentals.add(new BigDecimal(45));
        rentals.add(new BigDecimal(50));
        rentals.add(new BigDecimal(55));
        rentals.add(new BigDecimal(60));
        rentals.add(new BigDecimal(65));

        assertEquals(YEAR_COUNT, rentals.size());

        return rentals;
    }

    private static List<BigDecimal> getAcreage() {
        List<BigDecimal> acreages = new ArrayList<BigDecimal>(YEAR_COUNT);

        acreages.add(new BigDecimal(800));
        acreages.add(new BigDecimal(820));
        acreages.add(new BigDecimal(840));
        acreages.add(new BigDecimal(860));
        acreages.add(new BigDecimal(880));
        acreages.add(new BigDecimal(900));
        acreages.add(new BigDecimal(920));
        acreages.add(new BigDecimal(940));

        assertEquals(YEAR_COUNT, acreages.size());

        return acreages;
    }

}
