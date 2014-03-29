/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import monopoly.Space;

/*
 * Factory die StreetBoxen aanmaakt
 * @author Jorne Biccler
 */
public class StreetBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new StreetBox(space, infoProp);
    }

}
