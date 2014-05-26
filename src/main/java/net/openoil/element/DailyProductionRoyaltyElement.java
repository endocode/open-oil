package net.openoil.element;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.openoil.visitor.IContractElementVisitor;

public class DailyProductionRoyaltyElement implements IContractElement {

    /**
     * Each map is a 3-tuple representing a tranche of the royalty.
     * 
     * The tuples are: lowerMbopd, upperMbodp, royaltyRate
     */
    private List<Map<String, BigDecimal>> trancheTable = new ArrayList<Map<String, BigDecimal>>();

    private List<BigDecimal> dailyProductionRoyalty;

    public static final String LOWER_MBOPD = "lowerMbopd";
    public static final String UPPER_MBOPD = "upperMbopd";
    public static final String ROYALTY_RATE = "royaltyRate";

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public void addTranche(List<BigDecimal> tranch) {
        Map<String, BigDecimal> newRow = new HashMap<String, BigDecimal>();
        newRow.put(LOWER_MBOPD, tranch.get(0));
        newRow.put(UPPER_MBOPD, tranch.get(1));
        newRow.put(ROYALTY_RATE, tranch.get(2));

        trancheTable.add(newRow);
    }

    public List<Map<String, BigDecimal>> getTranchTable() {
        return trancheTable;
    }

    public List<BigDecimal> getDailyProductionRoyalty() {
        return dailyProductionRoyalty;
    }

    public void setDailyProductionRoyalty(
            List<BigDecimal> dailyProductionRoyalty) {
        this.dailyProductionRoyalty = dailyProductionRoyalty;
    }
}
