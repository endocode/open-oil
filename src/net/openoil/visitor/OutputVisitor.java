package net.openoil.visitor;

import net.openoil.element.CapexElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;
import net.openoil.io.OutputTable;

/**
 * The last step of the algorithm. This gathers all the values of the contract
 * after all steps have been run and puts them into an OutputTable.
 */
public class OutputVisitor implements IContractElementVisitor {

    private OutputTable outputs;

    public OutputVisitor(OutputTable outputs) {
        this.outputs = outputs;
    }

    @Override
    public void visit(PriceElement price) {
        this.outputs.setPrice(price.getPrice());
    }

    @Override
    public void visit(ProductionElement production) {
        this.outputs.setProduction(production.getProduction());
    }

    @Override
    public void visit(YearElement year) {
        this.outputs.setYear(year.getYear());
    }

    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        this.outputs.setSurfaceRental(surfaceRentalElement.getSurfaceRental());
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        this.outputs.setFlatRoyalty(flatRoyaltyElement.getRoyalty());
    }

    @Override
    public void visit(CapexElement capexElement) {
        this.outputs.setCapex(capexElement.getCapex());
    }

    @Override
    public void visit(OpexElement opexElement) {
        this.outputs.setOpex(opexElement.getOpex());
    }

    public void visit(CostRecoveryElement costRecoveryElement) {
        this.outputs.setCostRecovery(costRecoveryElement.getCostRecovery());
    }
}
