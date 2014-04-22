/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Klasse die VBox uitbreidt en een object hiervan zal een Rectangle weergeven.
 *
 * @author Jorne Biccler
 */
public class RectangleBox extends VBox {

    private final RectangleBoxCompanion rectangleCompanion;

    public RectangleBox() {
        try {
            FXMLLoader imageLoader = new FXMLLoader(
                    getClass().getResource("RectangleBox.fxml"));
            imageLoader.setRoot(this);
            rectangleCompanion = new RectangleBoxCompanion();
            imageLoader.setController(rectangleCompanion);
            imageLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * methode die het kleur van het kind, die een rectangle is, verandert.
     */
    public void changeRectangleColor(Color color) {
        rectangleCompanion.changeRectangleColor(color);
    }
}
