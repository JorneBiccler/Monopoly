/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Card;
import basicgameinfo.CardType;
import basicgameinfo.Space;
import java.util.List;
import java.util.Random;
import monopoly.*;

/**
 * Uitbreiding van SpecialBox die een deckobject bijhoudt en de gepaste actie
 * zal uitvoeren.
 *
 * @author jorne
 */
public class DeckBox extends SpecialBox {

    private List<Card> deck;
    private final Random rng = new Random();

    public DeckBox(Space space, String propString, List<Card> deck) {
        super(space, propString);
        this.deck = deck;

    }

    /**
     * Een rng trekt een kaart, indien het van het Jail type is wordt deze
     * verwijderd en uiteindelijk wordt voor een willekeurige kaart de gepaste
     * actie opgeroepen.
     */
    @Override
    public void doAction(GameModel gameModel) {
        Card selectedCard = deck.get(rng.nextInt(deck.size()));
        if (selectedCard.getType() == CardType.JAIL) {
            deck.remove(selectedCard);
        }
        selectedCard.getType().doAction(gameModel, selectedCard, getSpaceType());
    }

}
