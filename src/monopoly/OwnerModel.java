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
public class OwnerModel implements Observable, InvalidationListener {

    private final List<InvalidationListener> listenerList = new ArrayList<>();
    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        if (!owner.equals(this.owner)) {
            this.owner = owner;
            if(owner != null){
                owner.addListener(this);
            }
            fireInvalidationEvent();
        }
    }

    private void fireInvalidationEvent() {
        for (InvalidationListener il : listenerList) {
            il.invalidated(this);
        }
    }

    @Override
    public void addListener(InvalidationListener il) {
        listenerList.add(il);
    }

    @Override
    public void removeListener(InvalidationListener il) {
        listenerList.remove(il);
    }

    @Override
    public void invalidated(Observable o) {
        fireInvalidationEvent();
    }

}
