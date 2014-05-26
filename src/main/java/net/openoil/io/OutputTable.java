package net.openoil.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all data that results from the algorithm and can be used to produce
 * a model contract.
 */
public class OutputTable {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    private List<BigDecimal> price = new ArrayList<BigDecimal>();

    // Total surface rental in $
    private List<BigDecimal> surfaceRental = new ArrayList<BigDecimal>();

    private List<BigDecimal> flatRoyalty = new ArrayList<BigDecimal>();

    private List<BigDecimal> capex = new ArrayList<BigDecimal>();

    private List<BigDecimal> opex = new ArrayList<BigDecimal>();

    private List<BigDecimal> costRecovery = new ArrayList<BigDecimal>();

    private List<BigDecimal> dailyProductionRoyalty = new ArrayList<BigDecimal>();

    private List<BigDecimal> cumulativeProductionRoyalty = new ArrayList<BigDecimal>();

    private List<BigDecimal> profitOil = new ArrayList<BigDecimal>();

    private List<BigDecimal> governmentShare = new ArrayList<BigDecimal>();

    private List<BigDecimal> companyShare = new ArrayList<BigDecimal>();

    private List<BigDecimal> cumulativeRecoverableCosts = new ArrayList<BigDecimal>();

    private List<BigDecimal> corporateIncomeTax = new ArrayList<BigDecimal>();

    private List<BigDecimal> stateParticipation = new ArrayList<BigDecimal>();;

    public List<Integer> getYear() {
        return year;
    }

    public void setYear(List<Integer> year) {
        this.year = year;
    }

    public List<BigDecimal> getProduction() {
        return production;
    }

    public void setProduction(List<BigDecimal> production) {
        this.production = production;
    }

    public List<BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(List<BigDecimal> price) {
        this.price = price;
    }

    public List<BigDecimal> getSurfaceRental() {
        return this.surfaceRental;
    }

    public void setSurfaceRental(List<BigDecimal> surfaceRental) {
        this.surfaceRental = surfaceRental;
    }

    public void setFlatRoyalty(List<BigDecimal> royalty) {
        this.flatRoyalty = royalty;
    }

    public List<BigDecimal> getFlatRoyalty() {
        return this.flatRoyalty;
    }

    public List<BigDecimal> getCapex() {
        return this.capex;
    }

    public void setCapex(List<BigDecimal> capex) {
        this.capex = capex;
    }

    public List<BigDecimal> getOpex() {
        return this.opex;
    }

    public void setOpex(List<BigDecimal> opex) {
        this.opex = opex;
    }

    public List<BigDecimal> getCostRecovery() {
        return this.costRecovery;
    }

    public void setCostRecovery(List<BigDecimal> costRecovery) {
        this.costRecovery = costRecovery;
    }

    public List<BigDecimal> getDailyProductionRoyalty() {
        return this.dailyProductionRoyalty;
    }

    public void setDailyProductionRoyalty(
            List<BigDecimal> dailyProductionRoyalty) {
        this.dailyProductionRoyalty = dailyProductionRoyalty;
    }

    public List<BigDecimal> getCumulativeProductionRoyalty() {
        return this.cumulativeProductionRoyalty;
    }

    public void setCumulativeProductionRoyalty(
            List<BigDecimal> cumulativeProductionRoyalty) {
        this.cumulativeProductionRoyalty = cumulativeProductionRoyalty;
    }

    public void setProfitOil(List<BigDecimal> profitOil) {
        this.profitOil = profitOil;
    }

    public List<BigDecimal> getProfitOil() {
        return this.profitOil;
    }

    public void setGovernmentShare(List<BigDecimal> governmentShare) {
        this.governmentShare = governmentShare;
    }

    public List<BigDecimal> getGovernmentShare() {
        return this.governmentShare;
    }

    public void setCompanyShare(List<BigDecimal> companyShare) {
        this.companyShare = companyShare;
    }

    public List<BigDecimal> getCompanyShare() {
        return this.companyShare;
    }

    public void setCumulativeRecoverableCosts(
            List<BigDecimal> cumulativeRecoverableCosts) {
        this.cumulativeRecoverableCosts = cumulativeRecoverableCosts;
    }

    public List<BigDecimal> getCumulativeRecoverableCosts() {
        return this.cumulativeRecoverableCosts;
    }

    public List<BigDecimal> getCorporateIncomeTax() {
        return corporateIncomeTax;
    }

    public void setCorporateIncomeTax(List<BigDecimal> corporateIncomeTax) {
        this.corporateIncomeTax = corporateIncomeTax;
    }

    public List<BigDecimal> getStateParticipation() {
        return this.stateParticipation;
    }

    public void setStateParticipation(List<BigDecimal> stateParticipation) {
        this.stateParticipation = stateParticipation;
    }

}
