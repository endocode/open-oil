package net.openoil.element;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class ProductionSharingRFactorElement implements IContractElement {

    // Goverment share in $mm
    private List<BigDecimal> governmentShare = new ArrayList<BigDecimal>();

    // Company share in $mm
    private List<BigDecimal> companyShare = new ArrayList<BigDecimal>();

    // Does contract use R factor?
    private List<Boolean> rFactor = new ArrayList<Boolean>();

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getGovernmentShare() {
        return governmentShare;
    }

    public void setGovernmentShare(List<BigDecimal> governmentShare) {
        this.governmentShare = governmentShare;
    }

    public List<BigDecimal> getCompanyShare() {
        return companyShare;
    }

    public void setCompanyShare(List<BigDecimal> companyShare) {
        this.companyShare = companyShare;
    }

    public List<Boolean> getRFactor() {
        return rFactor;
    }

    public void setrFactor(List<Boolean> rFactor) {
        this.rFactor = rFactor;
    }

}
