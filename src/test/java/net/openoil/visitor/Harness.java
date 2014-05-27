package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import net.openoil.element.CumulativeProductionRoyaltyElement;
import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;

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

    // DAILY PRODUCTION ROYALTY

    public static DailyProductionRoyaltyElement getFilledDailyProductionRoyaltyElement() {
        DailyProductionRoyaltyElement element = getDailyProductionRoyaltyElement();

        element.setDailyProductionRoyalty(getDailyProductionRoyalty());

        return element;
    }

    public static DailyProductionRoyaltyElement getDailyProductionRoyaltyElement() {
        DailyProductionRoyaltyElement element = new DailyProductionRoyaltyElement();

        setDailyTranches(element, getDailyTranches());

        return element;
    }

    public static List<BigDecimal> getDailyProductionRoyalty() {
        List<BigDecimal> daily = new ArrayList<BigDecimal>(YEAR_COUNT);

        daily.add(new BigDecimal(0));
        daily.add(new BigDecimal(0));
        daily.add(new BigDecimal(0));
        daily.add(new BigDecimal(0));
        daily.add(new BigDecimal("6.51"));
        daily.add(new BigDecimal("133.18"));
        daily.add(new BigDecimal("119.95"));
        daily.add(new BigDecimal("117.99"));

        assertEquals(YEAR_COUNT, daily.size());

        return daily;
    }

    // YEAR

    public static YearElement getFilledYearElement() {
        YearElement element = new YearElement();

        element.setYear(getYear());

        return element;
    }

    // CUMULATIVE PRODUCTION ROYALTY

    public static CumulativeProductionRoyaltyElement getFilledCumulativeProductionRoyaltyElement() {
        CumulativeProductionRoyaltyElement element = getCumulativeProductionRoyaltyElement();

        element.setCumulativeProductionRoyalty(getCumulativeProductionRoyalty());

        return element;
    }

    public static CumulativeProductionRoyaltyElement getCumulativeProductionRoyaltyElement() {
        CumulativeProductionRoyaltyElement element = new CumulativeProductionRoyaltyElement();

        setCumulativeTranches(element, getCumulativeTranches());

        return element;
    }

    public static List<BigDecimal> getCumulativeProductionRoyalty() {
        List<BigDecimal> cumulative = new ArrayList<BigDecimal>(YEAR_COUNT);

        cumulative.add(new BigDecimal(0));
        cumulative.add(new BigDecimal(0));
        cumulative.add(new BigDecimal(0));
        cumulative.add(new BigDecimal(0));
        cumulative.add(new BigDecimal("2.93"));
        cumulative.add(new BigDecimal("39.61"));
        cumulative.add(new BigDecimal("102.12"));
        cumulative.add(new BigDecimal("191.51"));

        assertEquals(YEAR_COUNT, cumulative.size());

        return cumulative;
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

    // DAILY PRODUCTION ROYALTY PRIVATE

    private static void setDailyTranches(DailyProductionRoyaltyElement royalty, List<List<BigDecimal>> tranches) {
        for (List<BigDecimal> tranche : tranches) {
            royalty.addTranche(tranche);
        }
    }

    private static List<List<BigDecimal>> getDailyTranches() {
        final int trancheCount = 4;
        List<List<BigDecimal>> tranches = new ArrayList<List<BigDecimal>>(trancheCount);

        tranches.add(createDailyTranche(0, 2.5, 5));
        tranches.add(createDailyTranche(2.5, 5, 7.5));
        tranches.add(createDailyTranche(5, 7.5, 10));
        tranches.add(createDailyTranche(7.5, 1000000, 12.5));

        assertEquals(trancheCount, tranches.size());

        return tranches;
    }

    private static List<BigDecimal> createDailyTranche(double lmbopd, double umbopd, double rate)
    {
        final int valueCount = 3;
        List<BigDecimal> tranche = new ArrayList<BigDecimal>(valueCount);

        tranche.add(new BigDecimal(lmbopd));
        tranche.add(new BigDecimal(umbopd));
        tranche.add(new BigDecimal(rate));

        assertEquals(valueCount, tranche.size());

        return tranche;
    }

    // YEAR PRIVATE

    private static List<Integer> getYear() {
        List<Integer> years = new ArrayList<Integer>(YEAR_COUNT);

        for (int i = 1; i <= YEAR_COUNT; ++i) {
            years.add(i);
        }

        assertEquals(YEAR_COUNT, years.size());

        return years;
    }

    // CUMULATIVE PRODUCTION ROYALTY PRIVATE

    private static void setCumulativeTranches(CumulativeProductionRoyaltyElement royalty, List<List<BigDecimal>> tranches) {
        for (List<BigDecimal> tranche : tranches) {
            royalty.addTranche(tranche);
        }
    }

    private static List<List<BigDecimal>> getCumulativeTranches() {
        final int trancheCount = 5;
        List<List<BigDecimal>> tranches = new ArrayList<List<BigDecimal>>(trancheCount);

        tranches.add(createCumulativeTranche(0, 10, 2.5));
        tranches.add(createCumulativeTranche(10, 20, 5));
        tranches.add(createCumulativeTranche(20, 30, 7.5));
        tranches.add(createCumulativeTranche(30, 40, 10));
        tranches.add(createCumulativeTranche(40, 1000000, 12.5));

        assertEquals(trancheCount, tranches.size());

        return tranches;
    }

    private static List<BigDecimal> createCumulativeTranche(double lmmbbls, double ummbbls, double rate)
    {
        final int valueCount = 3;
        List<BigDecimal> tranche = new ArrayList<BigDecimal>(valueCount);

        tranche.add(new BigDecimal(lmmbbls));
        tranche.add(new BigDecimal(ummbbls));
        tranche.add(new BigDecimal(rate));

        assertEquals(valueCount, tranche.size());

        return tranche;
    }

}
