/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/*
 * uitbreiding van Observable die als model dient om aan te geven welke positie 
 * geselecteerd is. (zal waarschijnlijk later meer functionaliteit krijgen)
 * @author Jorne Biccler
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

    /* verandert selectedPosition zodat er een niet observeerbare positie
    *  komt als de reeds ingevulde positie hetzelfde als de nieuwe is.
    */
    public void setSelectedPosition(int selectedPosition) {
        if (this.selectedPosition != selectedPosition) {
            this.selectedPosition = selectedPosition;
            fireInvalidationEvent();
        }
        
    }
}
