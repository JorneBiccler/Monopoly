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
 * Uitbreiding van een VBox die een image bevat.
 * @author Jorne Biccler
 */
public class ImageBox extends VBox {

    private ImageBoxCompanion imageCompanion;

    public ImageBox(String imageURL) {
        try {
            FXMLLoader imageLoader = new FXMLLoader(
                    getClass().getResource("ImageBox.fxml"));
            imageLoader.setRoot(this);
            imageCompanion = new ImageBoxCompanion(imageURL);
            imageLoader.setController(imageCompanion);
            imageLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        prefHeightProperty().bind(imageCompanion.getImageView().fitHeightProperty());
    }
}
