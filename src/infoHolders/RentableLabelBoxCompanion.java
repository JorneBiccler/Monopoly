/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.control.Label;

/*
 * companion klasse van RentableLabelBox
 * @author Jorne Biccler
 */
public class RentableLabelBoxCompanion extends PurchasableLabelBoxCompanion {

    public Label rentLabel;
    private int currentRent;

    public RentableLabelBoxCompanion(int cost, int currentRent) {
        super(cost);
        this.currentRent = currentRent;
    }

    public void initialize() {
        super.initialize();
        rentLabel.setText("Huur:  €" + currentRent);
    }

    //methode die de huur verandert en ook het bijhorende label aanpast
    public void changeRentLabel(int currentRent) {
        this.currentRent = currentRent;
        rentLabel.setText("Huur:  €" + currentRent);
    }

}
