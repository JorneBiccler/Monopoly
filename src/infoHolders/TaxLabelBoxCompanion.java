/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.control.Label;

/*
 * companion klasse van TaxLabelBox
 * @author Jorne Biccler
 */
public class TaxLabelBoxCompanion implements LabelBoxCompanion {

    public Label amountLabel;
    private int amount;

    public TaxLabelBoxCompanion(int amount) {
        this.amount = amount;
    }

    @Override
    public void initialize() {
        amountLabel.setText("Betaal: \u20ac" + amount);
    }

}
