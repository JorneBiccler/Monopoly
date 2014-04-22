/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import monopoly.GameModel;
import monopoly.Space;

/**
 * Factory voor RailwayBoxen.
 *
 * @author Jorne Biccler
 */
public class RailwayBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp, GameModel gameModel) {
        return new RailwayBox(space, infoProp, gameModel);
    }

}
