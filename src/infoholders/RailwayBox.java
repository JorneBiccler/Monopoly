/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import javafx.beans.Observable;
import monopoly.Space;
import monopoly.SpaceType;

/**
 * InfoBox met extra informatie rond het specifieke RailWay type.
 *
 * @author Jorne Biccler
 */
public class RailwayBox extends PurchasableBoxWithImage {

    private final RentableLabelBoxCompanion labelBoxCompanion;

    public RailwayBox(Space space, String propString
    ) {
        super(space, propString);
        if (SpaceType.RAILWAY != space.getType()) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        String imagePath = "/resources/railway.png";
        initializeImageView(imagePath);
        labelBoxCompanion = new RentableLabelBoxCompanion(space.getCost(), space.getRent0());
        String labelBoxFxmlPath = "RentableLabelBox.fxml";
        createLabelBox(labelBoxCompanion, labelBoxFxmlPath);
    }

    @Override
    public int getRent() {
        if (model.getOwner() != null) {
            int count = 0;
            for (InfoBox box : model.getOwner().getOwnedProperties()) {
                if (box.getSpaceType() == getSpaceType()) {
                    count++;
                }
            }
            return (int) (initialRent * Math.pow(2,count-1));

        }
        return initialRent;
    }

    @Override
    public void invalidated(Observable o) {
        labelBoxCompanion.renewRent(getRent());
        labelBoxCompanion.renewOwner(model.getOwner());
    }

}
