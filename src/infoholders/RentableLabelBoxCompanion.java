/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import javafx.scene.control.Label;

/**
 * Partner klasse van RentableLabelBox
 *
 * @author Jorne Biccler
 */
public class RentableLabelBoxCompanion extends PurchasableLabelBoxCompanion {

    public Label rentLabel;
    private final int rent;
    private String initialRentString;
    public RentableLabelBoxCompanion(int cost, int rent) {
        super(cost);
        this.rent = rent;
    }

    @Override
    public void initialize() {
        super.initialize();
        initialRentString = rentLabel.getText();
        rentLabel.setText(initialRentString + rent);
    }

    public void renewRent(int rent){
        rentLabel.setText(initialRentString + rent);
    }
    
}
