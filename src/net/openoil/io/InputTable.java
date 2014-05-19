package net.openoil.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all data input to the algorithm.
 */
public class InputTable {

    private List<Integer> year = new ArrayList<Integer>();

    private List<BigDecimal> price = new ArrayList<BigDecimal>();

    private List<BigDecimal> production = new ArrayList<BigDecimal>();

    private List<BigDecimal> acreage = new ArrayList<BigDecimal>();

    private List<BigDecimal> rentalPerKm = new ArrayList<BigDecimal>();

    private List<BigDecimal> flatRoyaltyRate = new ArrayList<BigDecimal>();

    @Override
    public String toString() {
        // TODO useful in early stages for checking... should probably
        // remove/refactor later.
        return "InputTable {\n" + "\tyears = " + year + ",\n"
                + "\tproduction = " + production + "\n" + "\tacreage = "
                + acreage + "\n" + "\trentalPerKm = " + rentalPerKm + "\n}";
    }

    public List<Integer> getYear() {
        return year;
    }

    public List<BigDecimal> getProduction() {
        return production;
    }

    public List<BigDecimal> getPrice() {
        return price;
    }

    public List<BigDecimal> getAcreage() {
        return this.acreage;
    }

    public List<BigDecimal> getRentalPerKm() {
        return this.rentalPerKm;
    }

    public List<BigDecimal> getFlatRoyaltyRate() {
        return this.flatRoyaltyRate;
    }

}
