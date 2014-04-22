/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Partner klasse horende bij een InfoBox
 *
 * @author Jorne Biccler
 */
public class InfoBoxCompanion {

    public Label infoLabel;
    public VBox bottomBox;
    public VBox viewBox;
    private final String infoProp;

    public InfoBoxCompanion(String infoProp) {
        this.infoProp = infoProp;
    }

    public void initialize() {
        infoLabel.setText(infoProp);
    }

    public VBox getBottomBox() {
        return bottomBox;
    }

    public VBox getViewBox() {
        return viewBox;
    }
    
    
}
