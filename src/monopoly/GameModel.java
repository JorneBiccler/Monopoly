/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

/**
 *
 * @author jorne
 */
public class GameModel implements Observable {

    private final List<InvalidationListener> listerners = new ArrayList<>();
    private final List<Player> possibleModels;
    private int selectedPosition;
    private Player model;
    private boolean canDoAction;
    private int jackpot = 0;
    private final ObservableList<String> logList;
    private int numberOfThrows;
    private boolean canDoDoubleRoll = true;

    public int getNumberOfThrows() {
        return numberOfThrows;
    }

    public void incrementNumberOfThrows() {
        numberOfThrows++;
        if (numberOfThrows >= 3) {
            sentCurrentPlayerToJail();
        }
    }

    public void sentCurrentPlayerToJail() {
        setModelPosition(10, false, false);
        model.setInJail(true);
        doGameAction();
    }

    public void increaseJackpot(int amount) {
        jackpot += amount;
    }

    public int getJackpot() {
        return jackpot;
    }

    public void setJackpot(int jackpot) {
        this.jackpot = jackpot;
    }

    public GameModel(ObservableList<Player> possibleModels, ObservableList<String> logList) {
        this.possibleModels = possibleModels;
        model = possibleModels.get(0);
        this.logList = logList;
        selectedPosition = model.getCurrentPosition();
    }

    public List<Player> getPossibleModels() {
        return possibleModels;
    }

    @Override
    public void addListener(InvalidationListener il) {
        listerners.add(il);
    }

    @Override
    public void removeListener(InvalidationListener il) {
        listerners.remove(il);
    }

    public void fireInvalidationEvent() {
        for (InvalidationListener il : listerners) {
            il.invalidated(this);
        }
    }

    public Player getCurrentPlayer() {
        return model;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public int getModelPosition() {
        return model.getCurrentPosition();
    }

    public void setModelPosition(int modelPosition, boolean collect, boolean canDoAction) {
        if (modelPosition < model.getCurrentPosition() && collect) {
            model.increaseBalance(MonopolyBoardComponent.board.getSettings().getGo());
        }
        model.setCurrentPosition(modelPosition);
        this.selectedPosition = modelPosition;
        this.canDoAction = canDoAction;
        fireInvalidationEvent();
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        fireInvalidationEvent();
    }

    public void doGameAction() {
        List<Integer> lastRoll = GameComponent.diceButton.getLastRoll();
        if (lastRoll.get(0).equals(lastRoll.get(1)) && !model.isInJail() && canDoDoubleRoll) {
            setCanDoAction(false);
        } else {
            nextPlayer();
        }
        GameComponent.diceButton.setDisable(false);
        fireInvalidationEvent();
        System.out.println(numberOfThrows);
    }

    public void nextPlayer() {
        int index = possibleModels.indexOf(model);
        model = possibleModels.get((index + 1) % possibleModels.size());
        numberOfThrows = 0;
        GameComponent.diceButton.useStandardHandler();
        selectedPosition = model.getCurrentPosition();
        canDoAction = false;
        canDoDoubleRoll = true;
        if (model.isInJail()) {
            if (!model.useJailCard()) {
                GameComponent.diceButton.useJailHandler();
                canDoDoubleRoll = false;
            }            
        }
    }

    public boolean isCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    public void relativeModelPosition(int amount, boolean collect, boolean canDoAction) {
        int size = MonopolyBoardComponent.board.getSettings().getSize();
        int newPos = (model.getCurrentPosition() + amount) % size;
        if (amount >= 0) {
            setModelPosition(newPos, true, canDoAction);
        } else {
            if (newPos < 0) {
                setModelPosition(size + newPos, false, canDoAction);
            } else {
                setModelPosition(newPos, false, canDoAction);
            }
        }
    }

    public void standardDiceAction(int diceSum) {
        numberOfThrows++;
        if (numberOfThrows < 3) {
            relativeModelPosition(diceSum, true, true);
        } else {
            sentCurrentPlayerToJail();
        }
    }

}
