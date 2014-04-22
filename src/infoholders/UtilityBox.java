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
 * VBox die alle info rond 'utility' types weergeeft
 *
 * @author Jorne Biccler
 */
public class UtilityBox extends PurchasableBoxWithImage {

    private final PurchasableLabelBoxCompanion labelBoxCompanion;

    public UtilityBox(Space space, String propString, GameModel gameModel) {
        super(space, propString, gameModel);
        if (space.getType() != SpaceType.UTILITY) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        String imagePath = "/resources/" + space.getId().replaceAll("space.", "") + ".png";
        initializeImageView(imagePath);
        labelBoxCompanion = new PurchasableLabelBoxCompanion(space.getCost());
        String labelBoxFxmlPath = "PurchasableLabelBox.fxml";
        createLabelBox(labelBoxCompanion, labelBoxFxmlPath);
    }

    @Override
    public int getRent() {
        return 10050;
    }

}
