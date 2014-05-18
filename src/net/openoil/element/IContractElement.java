package net.openoil.element;

import net.openoil.visitor.IContractElementVisitor;

/**
 * A contract element is a part of the data from a contract. It may be a single
 * data point or a list of year-by-year figures.
 */
public interface IContractElement {

    public void accept(IContractElementVisitor visitor);

}
