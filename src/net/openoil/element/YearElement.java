package net.openoil.element;

import java.util.ArrayList;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

/**
 * The year element defines how many years the contract covers. 
 */
public class YearElement implements IContractElement {
    
    private List<Integer> year = new ArrayList<Integer>();

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<Integer> getYear() {
        return year;
    }

    public void setYear(List<Integer> year) {
        this.year = year;
    }
}
