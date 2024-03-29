package net.openoil.element;

import java.util.ArrayList;
import java.util.List;

import net.openoil.visitor.IContractElementVisitor;

/**
 * The contract is technically a contract element, but it represents the whole
 * contract by containing all other elements within it.
 * 
 */
public class Contract implements IContractElement {

    List<IContractElement> contractElements = new ArrayList<IContractElement>();

    public Contract() {
        contractElements.add(new YearElement());
        contractElements.add(new InflationElement());
        contractElements.add(new PriceElement());
        contractElements.add(new ProductionElement());
        contractElements.add(new SurfaceRentalElement());
        contractElements.add(new CapexElement());
        contractElements.add(new OpexElement());
        contractElements.add(new FlatRoyaltyElement());
        contractElements.add(new DailyProductionRoyaltyElement());
        contractElements.add(new CumulativeProductionRoyaltyElement());
        contractElements.add(new CostRecoveryElement());
        contractElements.add(new ProfitOilElement());
        contractElements.add(new ProductionSharingTrancheElement());
        contractElements.add(new ProductionSharingRFactorElement());
        contractElements.add(new CorporateIncomeTaxElement());
        contractElements.add(new StateParticipationElement());
    }

    /**
     * Calls upon all elements of the contract to accept the visitor provided
     * (thus applying that step of the algorithm to the contract).
     */
    @Override
    public void accept(IContractElementVisitor visitor) {
        for (IContractElement element : contractElements) {
            element.accept(visitor);
        }
    }

}
