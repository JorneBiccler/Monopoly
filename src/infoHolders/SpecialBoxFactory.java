/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import monopoly.Space;

/*
 * Factory die SpecialBoxen kan aanmaken
 * @author jorne
 */
public class SpecialBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new SpecialBox(space, infoProp);
    }

}
