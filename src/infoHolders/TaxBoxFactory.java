/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */

package infoHolders;

import monopoly.Space;

/*
 * Factory die TaxBoxen aanmaakt
 * @author Jorne Biccler
 */
public class TaxBoxFactory implements InfoBoxFactory{

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new TaxBox(space, infoProp);
    }
    
}
