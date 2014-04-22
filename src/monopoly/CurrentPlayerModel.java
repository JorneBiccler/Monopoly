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
 * @author Jorne Biccler
 */
public class CurrentPlayerModel implements Observable {

    private Player currentPlayer;
    private List<InvalidationListener> listenerList;
    private boolean canThrow;
    private boolean canDoAction;
    public CurrentPlayerModel(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        listenerList = new ArrayList<>();
        canThrow = true;
        canDoAction = true;
    }

    public boolean isCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }
    
    
    
    
    @Override
    public void addListener(InvalidationListener il) {
        listenerList.add(il);
    }
    
    @Override
    public void removeListener(InvalidationListener il) {
        listenerList.remove(il);
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        canThrow = true;
        canDoAction = true;
        fireInvalidationEvent();
    }

    public boolean getCanThrow() {
        return canThrow;
    }

    public void setCanThrow(boolean canThrow) {
        this.canThrow = canThrow;
        fireInvalidationEvent();
    }
    
    public void fireInvalidationEvent(){
        for(InvalidationListener il : listenerList){
            il.invalidated(this);
        }
    }
    
    
}
