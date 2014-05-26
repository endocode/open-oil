package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.CapexElement;
import net.openoil.element.CorporateIncomeTaxElement;
import net.openoil.element.CostRecoveryElement;
import net.openoil.element.CumulativeProductionRoyaltyElement;
import net.openoil.element.DailyProductionRoyaltyElement;
import net.openoil.element.FlatRoyaltyElement;
import net.openoil.element.OpexElement;
import net.openoil.element.PriceElement;
import net.openoil.element.ProductionElement;
import net.openoil.element.ProductionSharingRFactorElement;
import net.openoil.element.ProductionSharingTrancheElement;
import net.openoil.element.ProfitOilElement;
import net.openoil.element.StateParticipationElement;
import net.openoil.element.SurfaceRentalElement;
import net.openoil.element.YearElement;

/**
 * Calculates surface rental per year based on acreage and rent per square
 * kilometre.
 */
public class SurfaceRentalVisitor implements IContractElementVisitor {

    @Override
    public void visit(ProductionElement production) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(YearElement year) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(PriceElement price) {
        // Do nothing
        return;
    }

    /**
     * Surface rent is a fee paid for use of the concession area regardless
     * whether any oil is found, until such time as an agreement is terminated.
     * 
     * Land rental per kilometer is multiplied by number of square kilometres in
     * the concession area.
     */
    @Override
    public void visit(SurfaceRentalElement surfaceRentalElement) {
        List<BigDecimal> acreage = surfaceRentalElement.getAcreage();
        List<BigDecimal> rentalPerKm = surfaceRentalElement.getRentalPerKm();

        List<BigDecimal> surfaceRental = new ArrayList<BigDecimal>();
        // assert acreage.length == rentalPerKm.length ??

        BigDecimal acreageThisYear;
        BigDecimal rentalPerKmThisYear;
        BigDecimal surfaceRentalThisYear;

        for (int i = 0; i < acreage.size(); i++) {
            acreageThisYear = acreage.get(i);
            rentalPerKmThisYear = rentalPerKm.get(i);

            surfaceRentalThisYear = acreageThisYear
                    .multiply(rentalPerKmThisYear);
            surfaceRental.add(surfaceRentalThisYear);
        }

        surfaceRentalElement.setSurfaceRental(surfaceRental);
    }

    @Override
    public void visit(FlatRoyaltyElement flatRoyaltyElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(CapexElement capexElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(OpexElement opexElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(CostRecoveryElement costRecoveryElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            DailyProductionRoyaltyElement dailyProductionRoyaltyElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            CumulativeProductionRoyaltyElement cumulativeProductionRoyaltyElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(ProfitOilElement profitOilElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            ProductionSharingTrancheElement productionSharingTrancheElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(
            ProductionSharingRFactorElement productionSharingRFactorElement) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(CorporateIncomeTaxElement corporateIncomeTax) {
        // Do nothing.
        return;
    }

    @Override
    public void visit(StateParticipationElement stateParticipationElement) {
        // Do nothing.
        return;
    }

}