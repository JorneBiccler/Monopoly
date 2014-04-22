/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import monopoly.GameModel;
import monopoly.Space;

/**
 * Factory voor UtilityBoxen.
 *
 * @author Jorne Biccler
 */
public class UtilityBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp, GameModel gameModel) {
        return new UtilityBox(space, infoProp, gameModel);
    }

}
