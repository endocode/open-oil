package net.openoil.visitor;

import net.openoil.element.ProductionElement;
import net.openoil.element.YearElement;

/**
 * A visitor implements a step of the algorithm. It visits all 
 * elements of the contract.
 */
public interface IContractElementVisitor {
    
    public void visit(ProductionElement production);
    
    public void visit(YearElement year);

}
