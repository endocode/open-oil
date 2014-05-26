package net.openoil.element;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.openoil.visitor.IContractElementVisitor;

public class CumulativeProductionRoyaltyElement implements IContractElement {
    /**
     * Each map is a 3-tuple representing a tranche of the royalty.
     * 
     * The tuples are: lowerMmbbls, upperMmbbls, royaltyRate
     */
    private List<Map<String, BigDecimal>> trancheTable = new ArrayList<Map<String, BigDecimal>>();

    private List<BigDecimal> cumulativeProductionRoyalty;

    public static final String LOWER_MMBBLS = "lowerMmbbls";
    public static final String UPPER_MMBBLS = "upperMmbbls";
    public static final String ROYALTY_RATE = "royaltyRate";

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public void addTranche(List<BigDecimal> tranch) {
        Map<String, BigDecimal> newRow = new HashMap<String, BigDecimal>();
        newRow.put(LOWER_MMBBLS, tranch.get(0));
        newRow.put(UPPER_MMBBLS, tranch.get(1));
        newRow.put(ROYALTY_RATE, tranch.get(2));

        trancheTable.add(newRow);
    }

    public List<Map<String, BigDecimal>> getTranchTable() {
        return trancheTable;
    }

    public List<BigDecimal> getCumulativeProductionRoyalty() {
        return cumulativeProductionRoyalty;
    }

    public void setCumulativeProductionRoyalty(
            List<BigDecimal> cumulativeProductionRoyalty) {
        this.cumulativeProductionRoyalty = cumulativeProductionRoyalty;
    }

}
