package net.openoil.element;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.openoil.visitor.IContractElementVisitor;

public class ProductionSharingTrancheElement implements IContractElement {

    /**
     * Each map is a 4-tuple representing a tranche of the production sharing.
     * 
     * The tuples are: (governmentShare, companyShare, lowerMbopd, upperMbodp)
     */
    private List<Map<String, BigDecimal>> trancheTable = new ArrayList<Map<String, BigDecimal>>();

    public static String GOV_SHARE = "govShare";
    public static String COMPANY_SHARE = "companyShare";
    public static String LOWER_MBOPD = "lowerMbopd";
    public static String UPPER_MBOPD = "upperMbopd";

    // Goverment share in $mm
    private List<BigDecimal> governmentShare = new ArrayList<BigDecimal>();

    // Company share in $mm
    private List<BigDecimal> companyShare = new ArrayList<BigDecimal>();

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public void addTranche(List<BigDecimal> tranche) {
        Map<String, BigDecimal> newRow = new HashMap<String, BigDecimal>();
        newRow.put(GOV_SHARE, tranche.get(0));
        newRow.put(COMPANY_SHARE, tranche.get(1));
        newRow.put(LOWER_MBOPD, tranche.get(2));
        newRow.put(UPPER_MBOPD, tranche.get(3));

        trancheTable.add(newRow);
    }

    public List<Map<String, BigDecimal>> getTranchTable() {
        return trancheTable;
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
}
