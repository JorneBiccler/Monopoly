/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 *//*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

/**
 * Model die de grote lijnen van het spel vastlegt, vooral welk vakje er
 * geselecteerd werd, hoe groot de bonuspot is, en wie de huidige speler is
 * samen met de bijhorende info rond deze huidige speler.
 *
 * @author Jorne Biccler
 */
public class GameModel implements Observable {

    private final List<InvalidationListener> listeners = new ArrayList<>();
    private final List<Player> listOfPlayers;
    private int selectedPosition;
    private Player currentPlayer;
    private boolean canDoAction;
    private int jackpot = 0;
    private int numberOfThrows;
    private boolean canDoDoubleRoll = true;
    private final GameComponent gameComponent;

    public GameModel(ObservableList<Player> listOfPlayers, GameComponent gameComponent) {
        this.listOfPlayers = listOfPlayers;
        currentPlayer = listOfPlayers.get(0);
        this.gameComponent = gameComponent;
        selectedPosition = currentPlayer.getCurrentPosition();
    }

    /**
     * geeft weer hoeveel keer de huidige speler al gesmeten heeft
     */
    public int getNumberOfThrows() {
        return numberOfThrows;
    }

    /**
     * verhoog het aantal keer dat met de dobbelsteen werd gesmeten, indien dit
     * groter of gelijk aan 3 is wordt de hudige speler naar de gevangenis
     * gestuurd.
     */
    public void incrementNumberOfThrows() {
        numberOfThrows++;
    }

    /**
     * deze methode stuurt de huidige speler naar de gevangenis, hierbij wordt
     * nooit langs start gepasseerd.
     */
    public void sentCurrentPlayerToJail() {
        setCurrentPlayerPosition(10, false, false);
        currentPlayer.setInJail(true);
        nextTurn();
    }

    public void resetJackpot() {
        jackpot = 0;
        fireInvalidationEvent();
    }

    public void increaseJackpot(int amount) {
        jackpot += amount;
        fireInvalidationEvent();
    }

    public int getJackpot() {
        return jackpot;
    }

    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    @Override
    public void addListener(InvalidationListener il) {
        listeners.add(il);
    }

    @Override
    public void removeListener(InvalidationListener il) {
        listeners.remove(il);
    }

    public void fireInvalidationEvent() {
        for (InvalidationListener il : listeners) {
            il.invalidated(this);
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public int getCurrentPlayerPosition() {
        return currentPlayer.getCurrentPosition();
    }

    /**
     * methode die de plaats van de huidige speler aanpast, als er langs start
     * gepaseerd wordt dan wordt de balans op de gepaste wijze verhoogd (indien
     * dit nodig is).
     *
     * @param newPosition de nieuwe positie van de speler
     * @param collect true als de speler langs start kan passeren
     * @param canDoAction true als de speler op de nieuwe positie een actie kan
     * doen
     */
    public void setCurrentPlayerPosition(int newPosition, boolean collect, boolean canDoAction) {
        if (newPosition < getCurrentPlayerPosition() && collect) {
            currentPlayer.increaseBalance(MonopolyBoardComponent.BOARD.getSettings().getGo());
            GameComponent.logListWrapper.addMessage("passStart", new String[]{currentPlayer.getName()});
        }
        currentPlayer.setCurrentPosition(newPosition);
        this.selectedPosition = newPosition;
        this.canDoAction = canDoAction;
        fireInvalidationEvent();
    }

    /**
     * verander de geselecteerde positie
     */
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        fireInvalidationEvent();
    }

    /**
     * standaard actie die na een ronde wordt uitgevoerd, indien beide
     * dobbelstenen hetzelfde waren, dan kan de huidige speler nog een ronde
     * uitvoeren, anders wordt de volgende speler aangeduid.
     */
    public void nextTurn() {
        List<Integer> lastRoll = getLastRoll();
        if (lastRoll.get(0).equals(lastRoll.get(1)) && !currentPlayer.isInJail() && canDoDoubleRoll) {
            setCanDoAction(false);
        } else {
            nextPlayer();
        }
        gameComponent.getDiceButton().setDisable(false);
        fireInvalidationEvent();
    }

    /**
     * selecteer de volgende speler, en 'reset' de benodigde variabelen
     */
    private void nextPlayer() {
        int index = listOfPlayers.indexOf(currentPlayer);
        currentPlayer = listOfPlayers.get((index + 1) % listOfPlayers.size());
        numberOfThrows = 0;
        selectedPosition = currentPlayer.getCurrentPosition();
        canDoAction = false;
        canDoDoubleRoll = true;
        GameComponent.logListWrapper.addMessage("nextPlayer", new String[]{currentPlayer.getName()});
    }

    public boolean isCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    /**
     * Methode die een relative aanpassing doet aan de plaats van de huidige
     * speler. Zie ook setCurrentPlayerPosition()
     */
    public void relativeCurrentPlayerPositionChange(int amount, boolean collect, boolean canDoAction) {
        int size = MonopolyBoardComponent.BOARD.getSettings().getSize();
        int newPos = (currentPlayer.getCurrentPosition() + amount) % size;
        if (amount >= 0) {
            setCurrentPlayerPosition(newPos, true, canDoAction);
        } else {
            if (newPos < 0) {
                setCurrentPlayerPosition(size + newPos, false, canDoAction);
            } else {
                setCurrentPlayerPosition(newPos, false, canDoAction);
            }
        }
    }

    /**
     * Deze methode zorgt voor een Aannpassing van het model indien de
     * dobbelstenen gegooid werden. in het bijzonder is er sterk verschillende
     * gedrag indien de huidige speler in de gevangenis zit.
     */
    public void diceAction(int diceSum) {
        if (getCurrentPlayer().isInJail()) {
            if (!getCurrentPlayer().useJailCard()) {
                canDoDoubleRoll = false;
                if (getLastRoll().get(0).equals(getLastRoll().get(1))) {
                    getCurrentPlayer().setInJail(false);
                    relativeCurrentPlayerPositionChange(2 * getLastRoll().get(0), true, true);
                } else {
                    nextTurn();
                }
            } else {
                standardDiceAction(diceSum);
            }
        } else {
            standardDiceAction(diceSum);
        }
    }

    /**
     * Standaard aanpassing van de positie mbv de som van de dobbelstenen.
     */
    private void standardDiceAction(int diceSum) {
        incrementNumberOfThrows();
        if (numberOfThrows >= 3 && getLastRoll().get(0) == getLastRoll().get(1)) {
            sentCurrentPlayerToJail();
        }
        else{
            relativeCurrentPlayerPositionChange(diceSum, true, true);
        }
    }

    public List<Integer> getLastRoll() {
        return gameComponent.getDice().getLastRoll();
    }

}
