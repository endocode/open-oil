package net.openoil.io;

import java.util.Iterator;
import java.util.List;

/**
 * This class formats the data in the OutputTable into a suitable 
 * form.
 */
public class ContractOutputter {
    
    private OutputTable outputs;
    
    public ContractOutputter(OutputTable outputs) {
        this.outputs = outputs;
    }
    
    public void output() {
        StringBuilder outputStr = new StringBuilder();

        addToOutput(outputStr, outputs.getYear());
        addToOutput(outputStr, outputs.getProduction());
                
        System.out.println(outputStr);
    }
    
    private void addToOutput(StringBuilder outputStr, List<?> figures) {
        Iterator<?> i = figures.iterator();
        
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
