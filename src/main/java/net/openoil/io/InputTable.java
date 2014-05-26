package net.openoil.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.exception.ParameterException;

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

    private List<BigDecimal> stateParticipationRate = new ArrayList<BigDecimal>();

    /**
     * Verifies that all required inputs have been entered correctly.
     */
    public void validateRequiredInputs() throws ParameterException {
        // TODO: Temporary: need a better way of validating input and ensuring
        // all required data is given... e.g. the dependency graph idea.
        if (null == year || year.isEmpty()) {
            throw new ParameterException("Year is missing from input");
        }
        if (null == price || price.isEmpty()) {
            throw new ParameterException("Price is missing from input");
        }
        if (null == production || production.isEmpty()) {
            throw new ParameterException("Production is missing from input");
        }
        if (null == capex || capex.isEmpty()) {
            throw new ParameterException("Capex is missing from input");
        }
        if (null == opexPerBarrel || opexPerBarrel.isEmpty()) {
            throw new ParameterException("Opex is missing from input");
        }
        if (null == costRecoveryCeiling || costRecoveryCeiling.isEmpty()) {
            throw new ParameterException(
                    "Cost recovery ceiling is missing from input");
        }
    }

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
