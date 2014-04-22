/*
 * Auteur: Jorne Biccler 
 * Project: ugentopoly 
 * Vak: Programmeren 2
 */
package infoholders;

import monopoly.GameModel;
import monopoly.Space;

/**
 * Factory voor StreetBoxen.
 *
 * @author Jorne Biccler
 */
public class StreetBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp, GameModel gameModel) {
        return new StreetBox(space, infoProp, gameModel );
    }

}
