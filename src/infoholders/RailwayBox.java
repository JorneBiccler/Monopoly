/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import basicgameinfo.SpaceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * InfoBox met extra informatie rond het specifieke RailWay type.
 *
 * @author Jorne Biccler
 */
public class RailwayBox extends RentableBox implements HasImage {

    private final ImageView imageView;

    public RailwayBox(Space space, String propString) {
        super(space, propString);
        if (SpaceType.RAILWAY != space.getType()) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        String imagePath = "/resources/railway.png";
        imageView = new StandardImageViewWithImage(imagePath);
        addNodeViewBox(imageView);
    }

    @Override
    public int getRent() {
        if (model.getOwner() != null) {
            return (int) (initialRent * Math.pow(2, model.getOwner().numberOwnedOfSomeType(getSpaceType()) - 1));
        }
        return initialRent;
    }

    @Override
    public Image getImage() {
        return imageView.getImage();
    }

}
