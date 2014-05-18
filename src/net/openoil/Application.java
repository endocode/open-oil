package net.openoil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import net.openoil.element.Contract;
import net.openoil.element.IContractElement;
import net.openoil.io.ContractOutputter;
import net.openoil.io.InputTable;
import net.openoil.io.OutputTable;
import net.openoil.visitor.InitialisingVisitor;
import net.openoil.visitor.OutputVisitor;
import net.openoil.visitor.SurfaceRentalVisitor;

import com.google.gson.Gson;

public class Application {

    /*
     * Everything starts here. The inputs are provided in a file formatted in
     * JSON.
     * 
     * The core of the algorithm is implemented as a Visitor pattern, which
     * separates the operations from the data being operated on.
     * 
     * Each operation is implemented as a visitor; each piece of data in the
     * contract is an element.
     */
    public static void main(String[] args) {
        // args[0] - JSON file name

        Gson gson = new Gson();
        InputTable inputs = new InputTable();

        if (args.length != 1) {
            // TODO usage instructions to user
            System.exit(1);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));

            // Convert JSON string to InputTable
            inputs = gson.fromJson(br, InputTable.class);

            // TODO Useful for testing right now, but get rid of it real soon...
            System.out.println(inputs);
        } catch (IOException e) {
            // TODO Proper error handling
            e.printStackTrace();
            System.exit(1);
        }

        // Set up the contract and initialise it with all initial values.
        IContractElement contract = new Contract();
        contract.accept(new InitialisingVisitor(inputs));

        // TODO All intermediate visitors will be called here.

        contract.accept(new SurfaceRentalVisitor());

        // Gather all the final values to be output.
        OutputTable outputs = new OutputTable();
        contract.accept(new OutputVisitor(outputs));

        ContractOutputter outputter = new ContractOutputter(outputs);
        outputter.output();

    }
}
