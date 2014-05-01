/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import monopoly.GameModel;

/**
 * Uitbreiding van SpecialBox die met een goToJail vakje correspondeert.
 *
 * @author Jorne Biccler
 */
public class GoToJailBox extends SpecialBox {

    public GoToJailBox(Space space, String propString) {
        super(space, propString);
    }

    @Override
    public void doAction(GameModel gameModel) {
        gameModel.sentCurrentPlayerToJail();
    }

}
