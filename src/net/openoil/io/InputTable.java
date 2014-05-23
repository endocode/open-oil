package net.openoil.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all data input to the algorithm.
 */
public class InputTable {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> price = new ArrayList<BigDecimal>();

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    private List<BigDecimal> acreage = new ArrayList<BigDecimal>();

    private List<BigDecimal> rentalPerKm = new ArrayList<BigDecimal>();

    private List<BigDecimal> flatRoyaltyRate = new ArrayList<BigDecimal>();

    private List<BigDecimal> capex = new ArrayList<BigDecimal>();

    private List<BigDecimal> opexPerBarrel = new ArrayList<BigDecimal>();

    private List<BigDecimal> costRecoveryCeiling = new ArrayList<BigDecimal>();

    private List<List<BigDecimal>> dailyProductionRoyaltyTranche = new ArrayList<List<BigDecimal>>();

    private List<List<BigDecimal>> cumulativeProductionRoyaltyTranche = new ArrayList<List<BigDecimal>>();

    private List<List<BigDecimal>> productionSharingTranche = new ArrayList<List<BigDecimal>>();

    private List<Boolean> rFactor = new ArrayList<Boolean>();

    private List<BigDecimal> corporateIncomeTaxRate = new ArrayList<BigDecimal>();

    private List<BigDecimal> stateParticipationRate = new ArrayList<BigDecimal>();;

    public List<Integer> getYear() {
        return year;
    }

    public List<BigDecimal> getProduction() {
        return production;
    }

    public List<BigDecimal> getPrice() {
        return price;
    }

    public List<BigDecimal> getAcreage() {
        return this.acreage;
    }

    public List<BigDecimal> getRentalPerKm() {
        return this.rentalPerKm;
    }

    public List<BigDecimal> getFlatRoyaltyRate() {
        return this.flatRoyaltyRate;
    }

    public List<BigDecimal> getCapex() {
        return this.capex;
    }

    public List<BigDecimal> getOpexPerBarrel() {
        return this.opexPerBarrel;
    }

    public List<BigDecimal> getCostRecoveryCeiling() {
        return this.costRecoveryCeiling;
    }

    public List<List<BigDecimal>> getDailyProductionRoyaltyTranche() {
        return this.dailyProductionRoyaltyTranche;
    }

    public List<List<BigDecimal>> getCumulativeProductionRoyaltyTranche() {
        return this.cumulativeProductionRoyaltyTranche;
    }

    public List<List<BigDecimal>> getProductionSharingTranche() {
        return this.productionSharingTranche;
    }

    public List<Boolean> getRFactor() {
        return rFactor;
    }

    public List<BigDecimal> getCorporateIncomeTaxRate() {
        return this.corporateIncomeTaxRate;
    }

    public void setCorporateIncomeTaxRate(
            List<BigDecimal> corporateIncomeTaxRate) {
        this.corporateIncomeTaxRate = corporateIncomeTaxRate;
    }

    public List<BigDecimal> getStateParticipationRate() {
        return this.stateParticipationRate;
    }
}
