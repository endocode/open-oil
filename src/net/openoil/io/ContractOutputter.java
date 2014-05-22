package net.openoil.io;

import java.util.Iterator;
import java.util.List;

/**
 * This class formats the data in the OutputTable into a suitable form.
 */
public class ContractOutputter {

    private OutputTable outputs;

    public ContractOutputter(OutputTable outputs) {
        this.outputs = outputs;
    }

    public void output() {
        StringBuilder outputStr = new StringBuilder();

        addToOutput(outputStr, "Year", outputs.getYear());
        addToOutput(outputStr, "Production", outputs.getProduction());
        addToOutput(outputStr, "Price", outputs.getPrice());
        addToOutput(outputStr, "Surface Rental", outputs.getSurfaceRental());
        addToOutput(outputStr, "Flat Royalty", outputs.getFlatRoyalty());
        addToOutput(outputStr, "Capex", outputs.getCapex());
        addToOutput(outputStr, "Opex", outputs.getOpex());
        addToOutput(outputStr, "Daily Production Royalty",
                outputs.getDailyProductionRoyalty());
        addToOutput(outputStr, "Cumulative Production Royalty",
                outputs.getCumulativeProductionRoyalty());
        addToOutput(outputStr, "Cumulative Recoverable Cost",
                outputs.getCumulativeRecoverableCosts());
        addToOutput(outputStr, "Recovered Costs", outputs.getCostRecovery());
        addToOutput(outputStr, "Profit Oil", outputs.getProfitOil());
        addToOutput(outputStr, "Government Share", outputs.getGovernmentShare());
        addToOutput(outputStr, "Company Share", outputs.getCompanyShare());
        addToOutput(outputStr, "Corporate Income Tax",
                outputs.getCorporateIncomeTax());

        System.out.println(outputStr);
    }

    private void addToOutput(StringBuilder outputStr, String rowName,
            List<?> figures) {

        if (null == figures) {
            return;
        }

        Iterator<?> i = figures.iterator();

        outputStr.append(rowName + ",");

        while (i.hasNext()) {
            Object figure = i.next();
            outputStr.append(figure);

            if (i.hasNext()) {
                outputStr.append(",");
            }
        }
        outputStr.append("\n");

    }

}
