package net.openoil.visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.openoil.element.ProductionElement;

public class SillyVisitor implements IContractElementVisitor {

    @Override
    public void visit(ProductionElement production) {
        List<BigDecimal> productionFiguresIn = production.getProduction();
        List<BigDecimal> productionFiguresOut = new ArrayList<BigDecimal>();
        
        for (BigDecimal p : productionFiguresIn) {
            productionFiguresOut.add(p.add(new BigDecimal(1)));
        }
        
        production.setProduction(productionFiguresOut);
    }

}

