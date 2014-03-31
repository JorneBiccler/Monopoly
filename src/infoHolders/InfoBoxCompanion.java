/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import monopoly.Space;

/*
 * Companion klasse horende bij een InfoBox
 * @author Jorne Biccler
 */
public class InfoBoxCompanion {

    private Space space;
    public Label infoLabel;
    public VBox bottomBox;
    public VBox viewBox;
    private String infoProp;

    public InfoBoxCompanion(Space space, String infoProp) {
        this.space = space;
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
