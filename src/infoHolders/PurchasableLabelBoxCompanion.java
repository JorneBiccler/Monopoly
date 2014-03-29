/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.control.Label;
import monopoly.Player;

/*
 * Companion klasse die labels voor de kost en de eigenaar bevat.
 * @author Jorne Biccler
 */
public class PurchasableLabelBoxCompanion implements LabelBoxCompanion {

    private int cost;
    public Label costLabel;
    private Player owner;
    public Label ownerLabel;

    public PurchasableLabelBoxCompanion(int cost) {
        this.cost = cost;
    }

    public void initialize() {
        costLabel.setText("Kostprijs:  €" + cost);
        ownerLabel.setText("Huidige eigenaar: niemand");

    }

    // methode die een nieuwe kost instelt en ook het bijhorende label aanpast 
    public void renewCost(int cost) {
        this.cost = cost;
        costLabel.setText("Kostprijs:  €" + cost);
    }

    public Player getOwner() {
        return owner;
    }

    // methode die een nieuwe eigenaar instelt en ook het bijhorende label aanpast     
    public void renewOwner(Player owner) {
        this.owner = owner;
        ownerLabel.setText("Huidige eigenaar: " + owner.getName());
    }

}
