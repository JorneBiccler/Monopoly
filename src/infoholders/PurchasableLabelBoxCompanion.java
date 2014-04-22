/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import javafx.scene.control.Label;
import monopoly.Player;

/**
 * Partner klasse waarvan de objecten de labels voor de kost en de eigenaar
 * bevatten. De objecten houden ook bij welke Strings er in het fxml-bestand
 * gespecifieerd worden.
 *
 * @author Jorne Biccler
 */
public class PurchasableLabelBoxCompanion implements LabelBoxCompanion {

    private int cost;
    public Label costLabel;
    private Player owner;
    public Label ownerLabel;
    private String initialOwnerString;
    private String initialCostString;

    public PurchasableLabelBoxCompanion(int cost) {
        this.cost = cost;
    }

    @Override
    public void initialize() {
        initialOwnerString = ownerLabel.getText();
        initialCostString = costLabel.getText();
        costLabel.setText(initialCostString + cost);
        ownerLabel.setText(initialOwnerString + "niemand");

    }

    /**
     * methode die een nieuwe kost instelt en ook het bijhorende label aanpast
     */
    public void renewCost(int cost) {
        this.cost = cost;
        costLabel.setText(initialCostString + cost);
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * methode die een nieuwe eigenaar instelt en ook het bijhorende label
     * aanpast
     */
    public void renewOwner(Player owner) {
        this.owner = owner;
        ownerLabel.setText(initialOwnerString + owner.getName());
    }

}
