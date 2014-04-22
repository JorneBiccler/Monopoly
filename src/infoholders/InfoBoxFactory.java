/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import monopoly.GameModel;
import monopoly.Space;

/**
 * Factory voor InfoBoxen.
 *
 * @author Jorne Biccler
 */
public interface InfoBoxFactory {

    public InfoBox create(Space space, String infoProp, GameModel gameModel);
}
