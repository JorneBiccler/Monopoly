/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Companion klasse horend bij de ImageBox klasse
 * @author Jorne Biccler
 */
public class ImageBoxCompanion {

    public ImageView imageView;
    private Image image;

    public ImageBoxCompanion(String imageURL) {
        this.image = new Image(imageURL);
    }

    public void initialize() {
        imageView.setImage(image);
        double necessaryHeight = (double) imageView.getBoundsInParent().getHeight();
        imageView.setFitHeight(necessaryHeight);
    }

    public ImageView getImageView() {
        return imageView;
    }

}
