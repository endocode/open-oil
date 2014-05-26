package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
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

    // FLAT ROYALTY

    public static FlatRoyaltyElement getFilledFlatRoyaltyElement() {
        FlatRoyaltyElement element = getFlatRoyaltyElement();

        element.setRoyalty(getFlatRoyalty());

        return element;
    }

    public static FlatRoyaltyElement getFlatRoyaltyElement() {
        FlatRoyaltyElement element = new FlatRoyaltyElement();

        element.setRoyaltyRate(getFlatRoyaltyRate());

        return element;
    }

    public static List<BigDecimal> getFlatRoyalty() {
        List<BigDecimal> expected = new ArrayList<BigDecimal>(YEAR_COUNT);

        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal("17.58"));
        expected.add(new BigDecimal("176.25"));
        expected.add(new BigDecimal("160.40"));
        expected.add(new BigDecimal("158.04"));

        assertEquals(YEAR_COUNT, expected.size());

        return expected;
    }

    // PRICE

    public static PriceElement getFilledPriceElement() {
        PriceElement element = new PriceElement();

        element.setPrice(getPrice());

        return element;
    }

    // PRODUCTION

    public static ProductionElement getFilledProductionElement() {
        ProductionElement element = new ProductionElement();

        element.setProduction(getProduction());

        return element;
    }

    // OPEX

    public static OpexElement getFilledOpexElement() {
        OpexElement element = getOpexElement();

        element.setOpex(getOpex());

        return element;
    }

    public static OpexElement getOpexElement() {
        OpexElement element = new OpexElement();

        element.setOpexPerBarrel(getOpexPerBarrel());

        return element;
    }

    public static List<BigDecimal> getOpex() {
        List<BigDecimal> opex = new ArrayList<BigDecimal>(YEAR_COUNT);

        opex.add(new BigDecimal(0));
        opex.add(new BigDecimal(0));
        opex.add(new BigDecimal(0));
        opex.add(new BigDecimal(0));
        opex.add(new BigDecimal("14.07"));
        opex.add(new BigDecimal("141.00"));
        opex.add(new BigDecimal("128.32"));
        opex.add(new BigDecimal("126.44"));

        assertEquals(YEAR_COUNT, opex.size());

        return opex;
    }

    // SURFACE RENTAL PRIVATE

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

    // FLAT ROYALTY PRIVATE

    private static List<BigDecimal> getFlatRoyaltyRate() {
        List<BigDecimal> flatRate = new ArrayList<BigDecimal>(1);

        flatRate.add(new BigDecimal(15));

        assertEquals(1, flatRate.size());

        return flatRate;
    }

    // PRICE PRIVATE

    private static List<BigDecimal> getPrice() {
        List<BigDecimal> prices = new ArrayList<BigDecimal>(YEAR_COUNT);

        prices.add(new BigDecimal(100));
        prices.add(new BigDecimal(100));
        prices.add(new BigDecimal(100));
        prices.add(new BigDecimal(100));
        prices.add(new BigDecimal(100));
        prices.add(new BigDecimal(100));
        prices.add(new BigDecimal(100));
        prices.add(new BigDecimal(100));

        assertEquals(YEAR_COUNT, prices.size());

        return prices;
    }

    // PRODUCTION PRIVATE

    private static List<BigDecimal> getProduction() {
        List<BigDecimal> production = new ArrayList<BigDecimal>(YEAR_COUNT);

        production.add(new BigDecimal(0));
        production.add(new BigDecimal(0));
        production.add(new BigDecimal(0));
        production.add(new BigDecimal(0));
        production.add(new BigDecimal(1172));
        production.add(new BigDecimal(11750));
        production.add(new BigDecimal(10693));
        production.add(new BigDecimal(10536));

        assertEquals(YEAR_COUNT, production.size());

        return production;
    }

    // OPEX PRIVATE

    private static List<BigDecimal> getOpexPerBarrel() {
        List<BigDecimal> opexPerBarrel = new ArrayList<BigDecimal>(1);

        opexPerBarrel.add(new BigDecimal(12));

        assertEquals(1, opexPerBarrel.size());

        return opexPerBarrel;
    }

}
