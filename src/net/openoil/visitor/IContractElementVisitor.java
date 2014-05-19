package net.openoil.visitor;

import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;

/**
 * A visitor implements a step of the algorithm. It visits all elements of the
 * contract.
 */
public interface IContractElementVisitor {

    public void visit(ProductionElement production);

    public void visit(PriceElement price);

    public void visit(YearElement year);

    public void visit(SurfaceRentalElement surfaceRentalElement);

}
