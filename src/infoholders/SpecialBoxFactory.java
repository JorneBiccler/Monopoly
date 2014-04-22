/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import monopoly.GameModel;
import monopoly.Space;

/**
 * Factory voor SpecialBoxen.
 *
 * @author Jorne Biccler
 */
public class SpecialBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp, GameModel gameModel) {
        return new SpecialBox(space, infoProp, gameModel);
    }

}
