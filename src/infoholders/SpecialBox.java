/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * InfoBox horende bij 'speciale' types, bvb. START,JAIL,.. i.h.b. types waarbij
 * er enkel een infolabel en image nodig is.
 *
 * @author Jorne Biccler
 */
abstract class SpecialBox extends InfoBox implements HasImage {

    private final Image image;
    private final ImageView imageView = new ImageView();

    public SpecialBox(Space space, String propString) {
        super(space, propString);
        String imagePath = "/resources/" + space.getType().toLowerCase() + ".png";
        image = new Image(imagePath);
        imageView.setImage(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(130);
        imageView.setPreserveRatio(true);
        addNodeViewBox(imageView);
    }

    @Override
    public Image getImage() {
        return image;
    }

}
