/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import javafx.scene.control.Label;

/**
 * Partner klasse van TaxLabelBox, bij het inladen van het fxml-bestand zal de
 * tekst die daarin gespecifieerd is aangevult worden met het bedrag
 *
 * @author Jorne Biccler
 */
public class TaxLabelBoxCompanion implements LabelBoxCompanion {

    public Label amountLabel;
    private final int amount;

    public TaxLabelBoxCompanion(int amount) {
        this.amount = amount;
    }

    @Override
    public void initialize() {
        amountLabel.setText(amountLabel.getText() + amount);
    }

}
