/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import monopoly.Space;

/*
 * Factory die RailwayBoxes aanmaakt. 
 * @author Jorne Biccler
 */
public class RailwayBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new RailwayBox(space, infoProp);
    }

}
