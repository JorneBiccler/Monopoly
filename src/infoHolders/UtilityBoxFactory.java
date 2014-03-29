/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import monopoly.Space;

/*
 * Factory die UtilityBoxen aanmaakt
 * @author Jorne Biccler
 */
public class UtilityBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new UtilityBox(space, infoProp);
    }

}
