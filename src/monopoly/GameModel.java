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
public class GameModel implements Observable, InvalidationListener {

    private List<InvalidationListener> listerners = new ArrayList<>();
    private final List<Player> possibleModels;
    private int selectedPosition;
    private boolean ActionAllowed;
    private Player model;

    public GameModel(ObservableList<Player> possibleModels) {
        this.possibleModels = possibleModels;
        model = possibleModels.get(0);
        model.addListener(this);
        selectedPosition = 0;
        ActionAllowed = false;
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

    @Override
    public void invalidated(Observable o) {
        selectedPosition = model.getCurrentPosition();
        fireInvalidationEvent();
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

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        fireInvalidationEvent();
    }

    public void nextPlayer() {
        int index = possibleModels.indexOf(model);
        if (index < possibleModels.size() - 1) {
           model = possibleModels.get(index + 1);
        } else {
            model = possibleModels.get(0);
        }
        fireInvalidationEvent();
    }

}
