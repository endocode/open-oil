package net.openoil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import net.openoil.element.Contract;
import net.openoil.element.IContractElement;
import net.openoil.exception.ParameterException;
import net.openoil.io.ContractOutputter;
import net.openoil.io.InputTable;
import net.openoil.io.OutputTable;
import net.openoil.visitor.CorporateIncomeTaxVisitor;
import net.openoil.visitor.CostRecoveryVisitor;
import net.openoil.visitor.CumulativeProductionRoyaltyVisitor;
import net.openoil.visitor.DailyProductionRoyaltyVisitor;
import net.openoil.visitor.FlatRoyaltyVisitor;
import net.openoil.visitor.InitialisingVisitor;
import net.openoil.visitor.OpexVisitor;
import net.openoil.visitor.OutputVisitor;
import net.openoil.visitor.PriceVisitor;
import net.openoil.visitor.ProductionSharingRFactorVisitor;
import net.openoil.visitor.ProductionSharingTrancheVisitor;
import net.openoil.visitor.ProfitOilVisitor;
import net.openoil.visitor.StateParticipationVisitor;
import net.openoil.visitor.SurfaceRentalVisitor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
            inputs.validateRequiredInputs();
        } catch (IOException e) {
            // TODO Proper error handling
            e.printStackTrace();
            System.exit(1);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (JsonSyntaxException e) {
            System.out
                    .println("There was a problem with the JSON input. Please ensure all numerical values are correctly input.");
            System.exit(1);
        }

        // Set up the contract and initialise it with all initial values.
        IContractElement contract = new Contract();
        contract.accept(new InitialisingVisitor(inputs));

        // TODO All intermediate visitors are called here - order is hard-coded
        // right now. Probably should move into a factory.
        contract.accept(new PriceVisitor());
        contract.accept(new SurfaceRentalVisitor());
        contract.accept(new FlatRoyaltyVisitor());
        contract.accept(new OpexVisitor());
        contract.accept(new DailyProductionRoyaltyVisitor());
        contract.accept(new CumulativeProductionRoyaltyVisitor());
        contract.accept(new CostRecoveryVisitor());
        contract.accept(new ProfitOilVisitor());
        contract.accept(new ProductionSharingTrancheVisitor());
        contract.accept(new ProductionSharingRFactorVisitor());
        contract.accept(new CorporateIncomeTaxVisitor());
        contract.accept(new StateParticipationVisitor());

        // Gather all the final values to be output.
        OutputTable outputs = new OutputTable();
        contract.accept(new OutputVisitor(outputs));

        ContractOutputter outputter = new ContractOutputter(outputs);
        outputter.output();

    }
}
