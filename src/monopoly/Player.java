/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import infoholders.InfoBox;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
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
    private ObservableList<Card> ownedCards = FXCollections.observableArrayList();
    private int balance;
    private boolean hasOutOfJailCard;
    private boolean canDoAction;

    public boolean isCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    public Player(String name, Token token, Color color, int startBalance) {
        this.name = name;
        this.color = color;
        this.token = token;
        this.balance = balance;
        this.tokenImageView = new ImageView(token.getImage());
        tokenImageView.setFitHeight(25);
        tokenImageView.setFitWidth(15);
        currentPosition = 16;
        hasOutOfJailCard = false;
        canDoAction = false;
    }

    public String getName() {
        return name;
    }

    public Image getTokenImage() {
        return token.getImage();
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

    public void removeProperty(InfoBox property) {
        ownedProperties.remove(property);
        fireInvalidationEvent();
    }

    public void changePosition(int newPosition) {
        currentPosition = newPosition;
        fireInvalidationEvent();
    }

    public void addCard(Card card) {
        ownedCards.add(card);
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

    public void setBalance(int balance) {
        this.balance = balance;
        fireInvalidationEvent();
    }

    public ObservableList<InfoBox> getOwnedProperties() {
        return ownedProperties;
    }

    public boolean isHasOutOfJailCard() {
        return hasOutOfJailCard;
    }

    public void setHasOutOfJailCard(boolean hasOutOfJailCard) {
        this.hasOutOfJailCard = hasOutOfJailCard;
        fireInvalidationEvent();
    }

    public int numberOfTypeOwnedCounter(SpaceType type) {
        int temp = 0;
        for (InfoBox info : ownedProperties) {
            if (info.getSpaceType() == type) {
                temp++;
            }
        }
        return temp;
    }

}
