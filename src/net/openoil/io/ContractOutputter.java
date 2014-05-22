package net.openoil.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * This class formats the data in the OutputTable into a suitable form.
 */
public class ContractOutputter {

    private OutputTable outputs;

    private StringBuilder outputStr;
    private BufferedWriter fileWriter;

    private boolean outputToFile = true;

    public ContractOutputter(OutputTable outputs) {
        this.outputs = outputs;
    }

    public void output() {
        outputStr = new StringBuilder();

        try {
            fileWriter = new BufferedWriter(new FileWriter("output.csv"));
        } catch (IOException e) {
            // TODO Proper error handling
            System.out.println("Cannot write to file: " + e.getMessage());
            System.out.println("Outputting to the console instead");
            outputToFile = false;
        }

        addToOutput("Year", outputs.getYear());
        addToOutput("Production", outputs.getProduction());
        addToOutput("Price", outputs.getPrice());
        addToOutput("Surface Rental", outputs.getSurfaceRental());
        addToOutput("Flat Royalty", outputs.getFlatRoyalty());
        addToOutput("Capex", outputs.getCapex());
        addToOutput("Opex", outputs.getOpex());
        addToOutput("Daily Production Royalty",
                outputs.getDailyProductionRoyalty());
        addToOutput("Cumulative Production Royalty",
                outputs.getCumulativeProductionRoyalty());
        addToOutput("Cumulative Recoverable Cost",
                outputs.getCumulativeRecoverableCosts());
        addToOutput("Recovered Costs", outputs.getCostRecovery());
        addToOutput("Profit Oil", outputs.getProfitOil());
        addToOutput("Government Share", outputs.getGovernmentShare());
        addToOutput("Company Share", outputs.getCompanyShare());
        addToOutput("Corporate Income Tax", outputs.getCorporateIncomeTax());

        if (outputToFile) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println(outputStr);
        }
    }

    private void addToOutput(String rowName, List<?> figures) {
        if (outputToFile) {
            addToFile(rowName, figures);
        } else {
            addToString(rowName, figures);
        }
    }

    private void addToFile(String rowName, List<?> figures) {
        if (null == figures) {
            return;
        }

        Iterator<?> i = figures.iterator();

        try {
            fileWriter.append(rowName + ",");

            while (i.hasNext()) {
                Object figure = i.next();
                fileWriter.append(figure.toString());

                if (i.hasNext()) {
                    fileWriter.append(",");
                }
            }
            fileWriter.append("\n");
        } catch (IOException e) {
            System.out.println("Cannot write to file: " + e.getMessage());
            return;
        }

    }

    private void addToString(String rowName, List<?> figures) {

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
