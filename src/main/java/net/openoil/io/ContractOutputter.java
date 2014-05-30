package net.openoil.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * This class formats the data in the OutputTable into a suitable form.
 */
public class ContractOutputter {

    private OutputTable outputs;

    private StringBuilder outputStr;

    private Object[][] odsData;
    private String[] columnNames;

    private int rowNum = 0;
    // TODO Work out number of rows!
    final int ROWS = 15;
    int COLUMNS;

    private boolean outputToFile = true;

    // TODO Put in resource files
    private final String SURFACE_RENTAL_DESC = "Acreage (N ha) x Rental per Km ($ N / Km).";
    private final String FLAT_ROYALTY_DESC = "Flat royalty rate (N %) x Gross sales ($ Nmm).";
    private final String OPEX_DESC = "Production (N mmbbls) multiplied by opex per barrel ($ N)";
    private final String DAILY_PROD_ROYALTY_DESC = "Sum of tranche amounts. Tranche 1 ($ Nmm) + Tranche 2 ($ Nmm) + Tranche 3 ($ Nmm) + Tranche 4 ($ Nmm)";
    private final String CUM_PROD_ROYALTY_DESC = "Sum of tranche amounts. Tranche 1 ($ Nmm) + Tranche 2 ($ Nmm) + Tranche 3 ($ Nmm) + Tranche 4 ($ Nmm)";
    private final String PROFIT_OIL_DESC = "Gross sales ($ Nmm) - Total royalty ($ Nmm) - Cost recovery ($ Nmm)";
    private final String CIT_DESC = "(Total profit oil ($ Nmm) - Government share ($ Nmm) ) x Corporate income tax rate (N %)";

    public ContractOutputter(OutputTable outputs) {
        this.outputs = outputs;
        COLUMNS = outputs.getYear().size() + 2;

        odsData = new Object[ROWS][COLUMNS + 1];
    }

    public void output() {
        outputStr = new StringBuilder();

        addToOutput("Year", outputs.getYear());
        addToOutput("Production", outputs.getProduction());
        addToOutput("Price", outputs.getPrice());
        addToOutput("Surface Rental", outputs.getSurfaceRental(),
                SURFACE_RENTAL_DESC);
        addToOutput("Flat Royalty", outputs.getFlatRoyalty(), FLAT_ROYALTY_DESC);
        addToOutput("Capex", outputs.getCapex());
        addToOutput("Opex", outputs.getOpex(), OPEX_DESC);
        addToOutput("Daily Production Royalty",
                outputs.getDailyProductionRoyalty(), DAILY_PROD_ROYALTY_DESC);
        addToOutput("Cumulative Production Royalty",
                outputs.getCumulativeProductionRoyalty(), CUM_PROD_ROYALTY_DESC);
        addToOutput("Cumulative Recoverable Cost",
                outputs.getCumulativeRecoverableCosts());
        addToOutput("Recovered Costs", outputs.getCostRecovery());
        addToOutput("Profit Oil", outputs.getProfitOil(), PROFIT_OIL_DESC);
        addToOutput("Government Share", outputs.getGovernmentShare());
        addToOutput("Company Share", outputs.getCompanyShare());
        addToOutput("Corporate Income Tax", outputs.getCorporateIncomeTax(),
                CIT_DESC);
        addToOutput("State Participation", outputs.getStateParticipation());

        try {
            columnNames = new String[COLUMNS];
            // TODO Create a spreadsheet without column names
            for (int y = 1; y <= outputs.getYear().size(); y++) {
                columnNames[y] = "";
            }

            File f = new File("output.ods");
            TableModel model = new DefaultTableModel(odsData, columnNames);
            SpreadSheet.createEmpty(model).saveAs(f);

            SpreadSheet s = SpreadSheet.createEmpty(model);

        } catch (IOException e) {
            System.out.println("Unable to create file: " + e.getMessage());
            System.out.println("Printing to console instead...");
            outputToFile = false;
        }

        if (!outputToFile) {
            System.out.println(outputStr);
        }
    }

    private void addToOutput(String rowName, List<?> figures) {
        addToOutput(rowName, figures, "");
    }

    private void addToOutput(String rowName, List<?> figures,
            String rowDescription) {
        if (null == figures) {
            return;
        }

        addToFile(rowName, figures, rowDescription);
        addToString(rowName, figures);
    }

    private void addToFile(String rowName, List<?> figures,
            String rowDescription) {

        Object[] newRow = new Object[COLUMNS + 1];
        newRow[0] = rowName;
        newRow[1] = rowDescription;

        for (int i = 0; i < figures.size(); i++) {
            newRow[i + 2] = figures.get(i);
        }

        odsData[rowNum] = newRow;
        rowNum++;
    }

    private void addToString(String rowName, List<?> figures) {

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
