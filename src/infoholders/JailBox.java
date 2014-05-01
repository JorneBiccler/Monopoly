/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import monopoly.GameModel;

/**
 * Uitbreiding van SpecialBox die correspondeert met het gevangennis vakje.
 * @author Jorne Biccler
 */
public class JailBox extends SpecialBox {

    public JailBox(Space space, String propString ) {
        super(space, propString);

    }

    @Override
    public void doAction(GameModel gameModel) {
        gameModel.nextTurn();
    }

}
