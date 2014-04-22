/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * Klasse die een VBox uitbreidt. Een object hiervaan houdt de labels met "niet
 * standaard" informatie bij. Het doel is voornamelijk deze box in te laden aan
 * de hand van een gegeven partner klasse en een gegeven fxml bestand.
 *
 * @author Jorne Biccler
 */
public class LabelBox extends VBox {

    private final LabelBoxCompanion companion;

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
        this.getStyleClass().add("labelBox");
    }

}
