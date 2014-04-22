/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import monopoly.GameModel;
import monopoly.Space;
import monopoly.SpaceType;

/**
 * InfoBox met extra informatie rond het specifieke RailWay type.
 *
 * @author Jorne Biccler
 */
public class RailwayBox extends PurchasableBoxWithImage {

    private final RentableLabelBoxCompanion labelBoxCompanion;
    
    public RailwayBox(Space space, String propString, GameModel gameModel
            ) {
        super(space, propString,gameModel);
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
       return initialRent * owner.numberOfTypeOwnedCounter(getSpaceType());
    }

    
    
}
