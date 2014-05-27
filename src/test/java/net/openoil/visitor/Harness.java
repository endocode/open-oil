package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import net.openoil.element.CapexElement;
import net.openoil.element.CorporateIncomeTaxElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.CumulativeProductionRoyaltyElement;
import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.ProductionSharingRFactorElement;
import net.openoil.element.ProductionSharingTrancheElement;
import net.openoil.element.ProfitOilElement;
import net.openoil.element.StateParticipationElement;
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

    // CAPEX

    public static CapexElement getFilledCapexElement() {
        CapexElement element = new CapexElement();

        element.setCapex(getCapex());

        return element;
    }

    // COST RECOVERY

    public static CostRecoveryElement getFilledCostRecoveryElement() {
        CostRecoveryElement element = getCostRecoveryElement();

        element.setCostRecovery(getCostRecovery());
        element.setCumulativeRecoverableCosts(getCumulativeRecoverableCosts());

        return element;
    }

    public static CostRecoveryElement getCostRecoveryElement() {
        CostRecoveryElement element = new CostRecoveryElement();

        element.setCostRecoveryCeiling(getCostRecoveryCeiling());

        return element;
    }

    public static List<BigDecimal> getCostRecovery() {
        List<BigDecimal> recovery = new ArrayList<BigDecimal>(YEAR_COUNT);

        recovery.add(new BigDecimal(0));
        recovery.add(new BigDecimal(0));
        recovery.add(new BigDecimal(0));
        recovery.add(new BigDecimal(0));
        recovery.add(new BigDecimal("57.72"));
        recovery.add(new BigDecimal("536.88"));
        recovery.add(new BigDecimal("453.31"));
        recovery.add(new BigDecimal("392.67"));

        assertEquals(YEAR_COUNT, recovery.size());

        return recovery;
    }

    public static List<BigDecimal> getCumulativeRecoverableCosts() {
        List<BigDecimal> costs = new ArrayList<BigDecimal>(YEAR_COUNT);

        costs.add(new BigDecimal(50));
        costs.add(new BigDecimal(150));
        costs.add(new BigDecimal(300));
        costs.add(new BigDecimal(450));
        costs.add(new BigDecimal("606.36"));
        costs.add(new BigDecimal("410.49"));
        costs.add(new BigDecimal("185.51"));
        costs.add(new BigDecimal("69.29"));

        assertEquals(YEAR_COUNT, costs.size());

        return costs;
    }

    // PROFIT OIL

    public static ProfitOilElement getFilledProfitOilElement() {
        ProfitOilElement element = getProfitOilElement();

        element.setProfitOil(getProfitOil());

        return element;
    }

    public static ProfitOilElement getProfitOilElement() {
        ProfitOilElement element = new ProfitOilElement();

        return element;
    }

    public static List<BigDecimal> getProfitOil() {
        List<BigDecimal> expected = new ArrayList<BigDecimal>(YEAR_COUNT);

        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal("32.46"));
        expected.add(new BigDecimal("289.08"));
        expected.add(new BigDecimal("233.52"));
        expected.add(new BigDecimal("193.39"));

        assertEquals(YEAR_COUNT, expected.size());

        return expected;
    }

    // PRODUCTION SHARING TRANCHE

    public static ProductionSharingTrancheElement getFilledProductionSharingTrancheElement() {
        ProductionSharingTrancheElement element = getProductionSharingTrancheElement();

        element.setGovernmentShare(getGovernmentShare());
        element.setCompanyShare(getCompanyShare());

        return element;
    }

    public static ProductionSharingTrancheElement getProductionSharingTrancheElement() {
        ProductionSharingTrancheElement element = new ProductionSharingTrancheElement();

        setShareTranches(element, getShareTranches());

        return element;
    }

    public static List<BigDecimal> getGovernmentShare() {
        List<BigDecimal> share = new ArrayList<BigDecimal>(YEAR_COUNT);

        share.add(new BigDecimal(0));
        share.add(new BigDecimal(0));
        share.add(new BigDecimal(0));
        share.add(new BigDecimal(0));
        share.add(new BigDecimal("13.96"));
        share.add(new BigDecimal("176.34"));
        share.add(new BigDecimal("130.78"));
        share.add(new BigDecimal("108.30"));

        assertEquals(YEAR_COUNT, share.size());

        return share;
    }

    public static List<BigDecimal> getCompanyShare() {
        List<BigDecimal> share = new ArrayList<BigDecimal>(YEAR_COUNT);

        share.add(new BigDecimal(0));
        share.add(new BigDecimal(0));
        share.add(new BigDecimal(0));
        share.add(new BigDecimal(0));
        share.add(new BigDecimal("18.51"));
        share.add(new BigDecimal("112.75"));
        share.add(new BigDecimal("102.75"));
        share.add(new BigDecimal("85.10"));

        assertEquals(YEAR_COUNT, share.size());

        return share;
    }

    // STATE PARTICIPATION

    public static StateParticipationElement getFilledStateParticipationElement() {
        StateParticipationElement element = getStateParticipationElement();

        element.setStateParticipation(getStateParticipation());

        return element;
    }

    public static StateParticipationElement getStateParticipationElement() {
        StateParticipationElement element = new StateParticipationElement();

        element.setStateParticipationRate(getStateParticipationRate());

        return element;
    }

    public static List<BigDecimal> getStateParticipation() {
        List<BigDecimal> state = new ArrayList<BigDecimal>(YEAR_COUNT);

        state.add(new BigDecimal(0));
        state.add(new BigDecimal(0));
        state.add(new BigDecimal(0));
        state.add(new BigDecimal(0));
        state.add(new BigDecimal("6.17"));
        state.add(new BigDecimal("54.93"));
        state.add(new BigDecimal("44.37"));
        state.add(new BigDecimal("36.75"));

        assertEquals(YEAR_COUNT, state.size());

        return state;
    }

    // PRODUCTION SHARING R FACTOR

    public static ProductionSharingRFactorElement getFilledProductionSharingRFactorElement() {
        // TODO: Implement tests for R-Factor first.
        ProductionSharingRFactorElement factor = getProductionSharingRFactorElement();

        factor.setGovernmentShare(getRFactorGovernmentShare());
        factor.setCompanyShare(getRFactorCompanyShare());

        return factor;
    }

    public static ProductionSharingRFactorElement getProductionSharingRFactorElement() {
        // TODO: Implement tests for R-Factor first.
        ProductionSharingRFactorElement factor = new ProductionSharingRFactorElement();

        return factor;
    }

    public static List<BigDecimal> getRFactorGovernmentShare() {
        // TODO: Implement tests for R-Factor first.
        return null;
    }

    public static List<BigDecimal> getRFactorCompanyShare() {
        // TODO: Implement tests for R-Factor first.
        return null;
    }

    // CORPORATE INCOME TAX

    public static CorporateIncomeTaxElement getFilledCorporateIncomeTaxElementViaTranches() {
        CorporateIncomeTaxElement element = getCorporateIncomeTaxElement();

        element.setTax(getTaxViaTranches());

        return element;
    }

    public static CorporateIncomeTaxElement getFilledCorporateIncomeTaxElementViaRFactor() {
        CorporateIncomeTaxElement element = getCorporateIncomeTaxElement();

        element.setTax(getTaxViaRFactor());

        return element;
    }

    public static CorporateIncomeTaxElement getCorporateIncomeTaxElement() {
        CorporateIncomeTaxElement element = new CorporateIncomeTaxElement();

        element.setTaxRate(getTaxRate());

        return element;
    }

    public static List<BigDecimal> getTaxViaTranches() {
        List<BigDecimal> tax = new ArrayList<BigDecimal>(YEAR_COUNT);

        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal("5.55"));
        tax.add(new BigDecimal("33.83"));
        tax.add(new BigDecimal("30.83"));
        tax.add(new BigDecimal("25.53"));

        assertEquals(YEAR_COUNT, tax.size());

        return tax;
    }

    public static List<BigDecimal> getTaxViaRFactor() {
        List<BigDecimal> tax = new ArrayList<BigDecimal>(YEAR_COUNT);

        // TODO: Put proper expected values.
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));
        tax.add(new BigDecimal(0));

        assertEquals(YEAR_COUNT, tax.size());

        return tax;
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

    // CAPEX PRIVATE

    private static List<BigDecimal> getCapex() {
        List<BigDecimal> capex = new ArrayList<BigDecimal>(YEAR_COUNT);

        capex.add(new BigDecimal(50));
        capex.add(new BigDecimal(100));
        capex.add(new BigDecimal(150));
        capex.add(new BigDecimal(150));
        capex.add(new BigDecimal(200));
        capex.add(new BigDecimal(200));
        capex.add(new BigDecimal(100));
        capex.add(new BigDecimal(150));

        assertEquals(YEAR_COUNT, capex.size());

        return capex;
    }

    // COST RECOVERY PRIVATE

    private static List<BigDecimal> getCostRecoveryCeiling() {
        List<BigDecimal> list = new ArrayList<BigDecimal>(YEAR_COUNT);

        list.add(new BigDecimal(60));
        list.add(new BigDecimal(61));
        list.add(new BigDecimal(62));
        list.add(new BigDecimal(63));
        list.add(new BigDecimal(64));
        list.add(new BigDecimal(65));
        list.add(new BigDecimal(66));
        list.add(new BigDecimal(67));

        assertEquals(YEAR_COUNT, list.size());

        return list;
    }

    // PROFIT OIL PRIVATE

    // nothing.

    // PRODUCTION SHARING TRANCHE PRIVATE

    private static void setShareTranches(ProductionSharingTrancheElement share, List<List<BigDecimal>> tranches) {
        for (List<BigDecimal> tranche : tranches) {
            share.addTranche(tranche);
        }
    }

    private static List<List<BigDecimal>> getShareTranches() {
        final int trancheCount = 6;
        List<List<BigDecimal>> tranches = new ArrayList<List<BigDecimal>>(trancheCount);

        tranches.add(createShareTranche(43, 57, 0, 5));
        tranches.add(createShareTranche(46, 54, 5, 10));
        tranches.add(createShareTranche(51, 49, 10, 20));
        tranches.add(createShareTranche(56, 44, 20, 30));
        tranches.add(createShareTranche(61, 39, 30, 40));
        tranches.add(createShareTranche(66, 34, 40, 1000000));

        assertEquals(trancheCount, tranches.size());

        return tranches;
    }

    private static List<BigDecimal> createShareTranche(double govtShare, double companyShare, double lmbopd, double umbopd)
    {
        final int valueCount = 4;
        List<BigDecimal> tranche = new ArrayList<BigDecimal>(valueCount);

        tranche.add(new BigDecimal(govtShare));
        tranche.add(new BigDecimal(companyShare));
        tranche.add(new BigDecimal(lmbopd));
        tranche.add(new BigDecimal(umbopd));

        assertEquals(valueCount, tranche.size());

        return tranche;
    }

    // STATE PARTICIPATION PRIVATE

    private static List<BigDecimal> getStateParticipationRate() {
        List<BigDecimal> rate = new ArrayList<BigDecimal>(1);

        rate.add(new BigDecimal(19));

        assertEquals(1, rate.size());

        return rate;
    }

    // PRODUCTION SHARING R FACTOR PRIVATE

    // nothing.

    // CORPORATE INCOME TAX PRIVATE

    private static List<BigDecimal> getTaxRate() {
        List<BigDecimal> rate = new ArrayList<BigDecimal>(1);

        rate.add(new BigDecimal(30));

        assertEquals(1, rate.size());

        return rate;
    }

}
