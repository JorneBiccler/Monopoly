/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import monopoly.GameComponent;
import monopoly.GameModel;
import monopoly.Player;

/**
 * Een uitbreiding van SpecialBox die met een freeParking vakje correspondeert.
 *
 * @author Jorne Biccler
 */
public class FreeParkingBox extends SpecialBox {

    public FreeParkingBox(Space space, String propString) {
        super(space, propString);
    }

    /**
     * De bonusPot wordt op de gepaste manier geledigd.
     */
    @Override
    public void doAction(GameModel gameModel) {
        Player currentPlayer = gameModel.getCurrentPlayer();
        int jackpot = gameModel.getJackpot();
        if (gameModel.getJackpot() > 0) {
            gameModel.resetJackpot();
            GameComponent.logListWrapper.addMessage("getJackpot", new Object[]{
                currentPlayer.getName(), jackpot});
        }
        gameModel.nextTurn();
    }

}
