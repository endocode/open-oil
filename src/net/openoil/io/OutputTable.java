package net.openoil.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all data that results from the algorithm and can be used to produce
 * a model contract.
 */
public class OutputTable {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    private List<BigDecimal> price = new ArrayList<BigDecimal>();

    // Total surface rental
    private List<BigDecimal> surfaceRental = new ArrayList<BigDecimal>();

    @Override
    public String toString() {
        return "OutputTable {\n" + "\tyears = " + year + ",\n"
                + "\tproduction = " + production + "\n}";
    }

    public List<Integer> getYear() {
        return year;
    }

    public void setYear(List<Integer> year) {
        this.year = year;
    }

    public List<BigDecimal> getProduction() {
        return production;
    }

    public void setProduction(List<BigDecimal> production) {
        this.production = production;
    }

    public List<BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(List<BigDecimal> price) {
        this.price = price;
    }

    public List<BigDecimal> getSurfaceRental() {
        return this.surfaceRental;
    }

    public void setSurfaceRental(List<BigDecimal> surfaceRental) {
        this.surfaceRental = surfaceRental;
    }

}
