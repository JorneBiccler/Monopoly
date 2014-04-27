/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoholders;

import java.util.List;
import java.util.Random;
import monopoly.*;

/**
 *
 * @author jorne
 */
public class DeckBox extends SpecialBox {

    private List<Card> deck;
    private static final Random rng = new Random();

    public DeckBox(Space space, String propString, List<Card> deck) {
        super(space, propString);
        this.deck = deck;

    }

    @Override
    public void doAction(GameModel gameModel) {
        Card selectedCard = deck.get(rng.nextInt(deck.size()));;

        if (selectedCard.getType() == CardType.JAIL) {
            deck.remove(selectedCard);
        }
        selectedCard.getType().doAction(gameModel, selectedCard, getSpaceType());
    }

}
