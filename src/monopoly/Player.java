/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import basicgameinfo.Area;
import basicgameinfo.Card;
import basicgameinfo.Deck;
import basicgameinfo.SpaceType;
import infoholders.InfoBox;
import infoholders.StreetBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Klasse waarvan objecten de informatie van een speler bijhoudt.
 *
 * @author Jorne Biccler
 */
public class Player implements Observable {

    private List<InvalidationListener> listenerList = new ArrayList<>();
    private final String name;
    private final Token token;
    private final ImageView tokenImageView;
    private final Color color;
    private ObservableList<InfoBox> ownedProperties = FXCollections.observableArrayList();
    private int currentPosition;
    private int balance;
    private boolean inJail;
    private final Map<SpaceType, Card> jailCardMap = new HashMap<>();

    public Player(String name, Token token, Color color, int startBalance) {
        this.name = name;
        this.color = color;
        this.token = token;
        this.balance = MonopolyBoardComponent.BOARD.getSettings().getBalance();
        this.tokenImageView = new ImageView(token.getImage());
        tokenImageView.setFitHeight(25);
        tokenImageView.setFitWidth(15);
        currentPosition = 0;
        inJail = false;
    }

    /**
     * voeg een get out of jail kaartje toe.
     *
     * @param deckType
     * @param jailCard
     */
    public void addJailCard(SpaceType deckType, Card jailCard) {
        jailCardMap.put(deckType, jailCard);
        fireInvalidationEvent();
    }

    public boolean hasJailCard() {
        return (jailCardMap.size() > 0);
    }

    /**
     * probeer een get ouf of jail kaartje te gebruiken, indien dit lukt wordt
     * true terug gegeven, indien niet false; het kaartje wordt ook terug in het
     * gepaste deck gestoken. Opgelet: roep deze m
     */
    public boolean useJailCard() {
        boolean usedCard = false;
        if (isInJail()) {
            for (Deck deck : MonopolyBoardComponent.BOARD.getDecks()) {
                if (jailCardMap.containsKey(deck.getType()) && !usedCard) {
                    deck.getDeckList().add(jailCardMap.get(deck.getType()));
                    jailCardMap.remove(deck.getType());
                    usedCard = true;
                    fireInvalidationEvent();
                }
            }
            setInJail(!usedCard);
        }
        return usedCard;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public ImageView getTokenImageView() {
        return tokenImageView;
    }

    @Override
    public void addListener(InvalidationListener il) {
        listenerList.add(il);
    }

    @Override
    public void removeListener(InvalidationListener il) {
        listenerList.remove(il);
    }

    public void fireInvalidationEvent() {
        for (InvalidationListener il : listenerList) {
            il.invalidated(this);
        }
    }

    public void addProperty(InfoBox property) {
        ownedProperties.add(property);
        fireInvalidationEvent();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        fireInvalidationEvent();
    }

    public int getBalance() {
        return balance;
    }

    public Token getToken() {
        return token;
    }

    public ObservableList<InfoBox> getOwnedProperties() {
        return ownedProperties;
    }

    public void decreaseBalance(int amount) {
        balance -= amount;
        fireInvalidationEvent();
    }

    public void increaseBalance(int amount) {
        balance += amount;
        fireInvalidationEvent();
    }

    /**
     * Geeft het aantal properties van een bepaalde Spacetype die de speler
     * bezit terug
     */
    public int numberOwnedOfSomeType(SpaceType type) {
        int count = 0;
        for (InfoBox box : ownedProperties) {
            if (box.getSpaceType() == type) {
                count++;
            }
        }
        return count;
    }

    /**
     * Geeft het aantal properties van een bepaalde Area die de speler bezit
     * terug
     */
    public int numberOwnedOfSomeArea(Area area) {
        int count = 0;
        for (InfoBox box : getOwnedProperties()) {
            if (box.getSpaceType() == SpaceType.STREET) {
                StreetBox streetCast = (StreetBox) box;
                if (streetCast.getArea().equals(area)) {
                    count++;
                }
            }
        }
        return count;
    }

}
