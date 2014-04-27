/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import javafx.beans.Observable;
import monopoly.GameComponent;
import monopoly.Space;
import monopoly.SpaceType;

/**
 * VBox die alle info rond 'utility' types weergeeft
 *
 * @author Jorne Biccler
 */
public class UtilityBox extends PurchasableBoxWithImage {

    private final PurchasableLabelBoxCompanion labelBoxCompanion;

    public UtilityBox(Space space, String propString ) {
        super(space, propString);
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
        
        int count = 0;
        if(model.getOwner() !=null){
            for(InfoBox box : model.getOwner().getOwnedProperties()){
                if(box.getSpaceType() == SpaceType.UTILITY){
                    count++;
                }
            }
        }
        if(count == 2){
            return 10*GameComponent.diceButton.getDiceSum();
        }
        else{
            return 4*GameComponent.diceButton.getDiceSum();
        }
        
        
    }

    @Override
    public void invalidated(Observable o) {
        labelBoxCompanion.renewOwner(model.getOwner());
    }

}
