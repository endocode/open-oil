package net.openoil.element;

import java.math.BigDecimal;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

public class SurfaceRentalElement implements IContractElement {

    // Area in km^2
    private List<BigDecimal> acreage;

    // Rent in $/km^2
    private List<BigDecimal> rentalPerKm;

    // Total rent
    private List<BigDecimal> surfaceRental;

    @Override
    public void accept(IContractElementVisitor visitor) {
        visitor.visit(this);
    }

    public List<BigDecimal> getRentalPerKm() {
        return rentalPerKm;
    }

    public void setRentalPerKm(List<BigDecimal> rentalPerKm) {
        this.rentalPerKm = rentalPerKm;
    }

    public List<BigDecimal> getAcreage() {
        return acreage;
    }

    public void setAcreage(List<BigDecimal> acreage) {
        this.acreage = acreage;
    }

    public List<BigDecimal> getSurfaceRental() {
        return surfaceRental;
    }

    public void setSurfaceRental(List<BigDecimal> surfaceRental) {
        this.surfaceRental = surfaceRental;
    }

}
