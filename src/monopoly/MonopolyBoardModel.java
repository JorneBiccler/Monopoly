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

/**
 *
 * @author jorne
 */
public class MonopolyBoardModel implements Observable {

    private List<InvalidationListener> listenerList = new ArrayList<>();
    private int selectedPosition;

    public MonopolyBoardModel(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @Override
    public void addListener(InvalidationListener il) {
        listenerList.add(il);
    }

    @Override
    public void removeListener(InvalidationListener il) {
        listenerList.remove(il);
    }

    private void fireInvalidationEvent() {
        for (InvalidationListener il : listenerList) {
            il.invalidated(this);
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        if (this.selectedPosition != selectedPosition) {
            this.selectedPosition = selectedPosition;
            
        } else {
            this.selectedPosition = -1;
            System.out.println(selectedPosition);
        }
        fireInvalidationEvent();
    }
}
