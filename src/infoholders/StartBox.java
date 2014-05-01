/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */

package infoholders;

import basicgameinfo.Space;
import monopoly.GameModel;
import monopoly.MonopolyBoardComponent;

/**
 * Uitbreiding van SpecialBox die met een Start vakje correspondeert.
 * 
 * @author Jorne Biccler
 */
public class StartBox extends SpecialBox{

    public StartBox(Space space, String propString ) {
        super(space, propString);
    }

    /**
     * als een speler hier belandt, wordt zijn balans verhoogt met het
     * gespecifieerd bedrag in het xml bestand. (standaard gebeurt dit bovenop
     * een extra bedrag die bekomen wordt door het 'passeren' van dit vakje)
     */
    @Override
    public void doAction(GameModel gameModel) {
        gameModel.getCurrentPlayer().increaseBalance(MonopolyBoardComponent.board.getSettings().getGo());
        gameModel.nextTurn();
    }
    
}
