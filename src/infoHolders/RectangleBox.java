/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/*
 * Klasse die VBox uitbreidt en hierin een Rectangle met een bepaald kleur zet
 * @author jorne
 */
public class RectangleBox extends VBox {

    private RectangleBoxCompanion rectangleCompanion;

    public RectangleBox(Color color) {
        try {
            FXMLLoader imageLoader = new FXMLLoader(
                    getClass().getResource("RectangleBox.fxml"));
            imageLoader.setRoot(this);
            rectangleCompanion = new RectangleBoxCompanion(color);
            imageLoader.setController(rectangleCompanion);
            imageLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /*
     * methode die het kleur van het kind die een rectangle is verandert
     * dit gebeurt door een gelijkaardige methode op te roepen in de companion klasse
     */
    public void changeRectangleColor(Color color) {
        rectangleCompanion.changeRectangleColor(color);
    }
}
