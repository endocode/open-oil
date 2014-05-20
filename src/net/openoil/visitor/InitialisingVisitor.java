package net.openoil.visitor;

import net.openoil.element.CapexElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;
import net.openoil.io.InputTable;

/**
 * This is the first step of the algorithm. It takes all values entered by the
 * user and initialises the elements of the contract with starting values.
 */
public class InitialisingVisitor implements IContractElementVisitor {

    private InputTable inputs;

    public InitialisingVisitor(InputTable inputs) {
        this.inputs = inputs;
    }

    @Override
    public void visit(YearElement year) {
        year.setYear(inputs.getYear());
    }

    @Override
    public void visit(PriceElement price) {
        price.setPrice(inputs.getPrice());
    }

    @Override
    public void visit(ProductionElement production) {
        production.setProduction(inputs.getProduction());
    }

    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        surfaceRentalElement.setAcreage(inputs.getAcreage());
        surfaceRentalElement.setRentalPerKm(inputs.getRentalPerKm());
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        flatRoyaltyElement.setRoyaltyRate(inputs.getFlatRoyaltyRate());
    }

    @Override
    public void visit(CapexElement capexElement) {
        capexElement.setCapex(inputs.getCapex());
    }

    @Override
    public void visit(OpexElement opexElement) {
        opexElement.setOpexPerBarrel(inputs.getOpexPerBarrel());
    }

    @Override
    public void visit(CostRecoveryElement costRecoveryElement) {
        costRecoveryElement.setCostRecoveryCeiling(inputs
                .getCostRecoveryCeiling());
    }

}
