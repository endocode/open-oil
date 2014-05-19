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

        addToOutput(outputStr, "year", outputs.getYear());
        addToOutput(outputStr, "production", outputs.getProduction());
        addToOutput(outputStr, "price", outputs.getPrice());
        addToOutput(outputStr, "surface rental", outputs.getSurfaceRental());
        addToOutput(outputStr, "flat royalty", outputs.getFlatRoyalty());

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
