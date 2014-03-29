/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/*
 * Klasse die een VBox uitbreidt waarin de labels met "niet standaard" informatie staat
 * het doel is voornamelijk deze box in te laden aan de hand van een gegeven companion klasse
 * en een gegeven fxml bestand.
 * @author Jorne Biccler
 */
public class LabelBox extends VBox {

    private LabelBoxCompanion companion;

    public LabelBox(LabelBoxCompanion companion, String fxmlpath) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    companion.getClass().getResource(fxmlpath));
            loader.setRoot(this);
            this.companion = companion;
            loader.setController(companion);
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getStyleClass().add("extraLabelBox");
    }

    public LabelBoxCompanion getCompanion() {
        return companion;
    }

}
