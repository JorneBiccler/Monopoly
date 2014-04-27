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
    public Label ownerLabel;
    private String initialOwnerString;

    public PurchasableLabelBoxCompanion(int cost) {
        this.cost = cost;
    }

    @Override
    public void initialize() {
        initialOwnerString = ownerLabel.getText();
        costLabel.setText(costLabel.getText() + cost);
        ownerLabel.setText(initialOwnerString + "niemand");

    }

    /**
     * methode die een nieuwe eigenaar instelt en ook het bijhorende label
     * aanpast
     */
    public void renewOwner(Player owner) {
        if (owner == null) {
            ownerLabel.setText(initialOwnerString + "niemand");
        } else {
            ownerLabel.setText(initialOwnerString + owner.getName());
        }
    }

}
