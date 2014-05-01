/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import basicgameinfo.SpaceType;
import dialogs.ActionDialog;
import java.util.List;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import monopoly.*;

/**
 * VBox die alle info rond 'utility' types weergeeft
 *
 * @author Jorne Biccler
 */
public class UtilityBox extends PurchasableBox implements HasImage {

    private final PurchasableLabelBoxCompanion labelBoxCompanion;
    private final ImageView imageView;

    public UtilityBox(Space space, String propString) {
        super(space, propString);
        if (space.getType() != SpaceType.UTILITY) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        String imagePath = "/resources/" + space.getId().replaceAll("space.", "") + ".png";
        imageView = new StandardImageViewWithImage(imagePath);
        addNodeViewBox(imageView);
        labelBoxCompanion = new PurchasableLabelBoxCompanion(space.getCost());
        String labelBoxFxmlPath = "PurchasableLabelBox.fxml";
        createLabelBox(labelBoxCompanion, labelBoxFxmlPath);
    }

    /**
     * methode die de rent teruggeeft, dit is gebaseerd op de som van de
     * dobbelstenen die gegooid werden, en het aantal utility vakjes die de
     * eigenaar bezit.
     */
    public int getRent(List<Integer> lastRoll) {
        int count = 0;
        if (model.getOwner() != null) {
            count = model.getOwner().numberOwnedOfSomeType(getSpaceType());
        }
        int diceSum = lastRoll.get(0) + lastRoll.get(1);
        if (count == 2) {
            return 10 * diceSum;
        } else {
            return 4 * diceSum;
        }
    }

    @Override
    public void invalidated(Observable o) {
        labelBoxCompanion.renewOwner(model.getOwner());
    }

    /**
     * Zorgt ervoor dat de gepaste actie plaats vindt; als er nog geen speler
     * dit vakje bezit dan kan het mogelijks gekocht worden, indien wel en de
     * currentPleyer is niet de owner, dan moet er huur betaald worden, anders
     * gebeurt gaan we standaard naar de volgende "ronde" in het spel.
     */
    @Override
    public void doAction(final GameModel gameModel) {
        final Player currentPlayer = gameModel.getCurrentPlayer();
        int rent = getRent(gameModel.getLastRoll());
        if (model.getOwner() == null) {
            Stage dialogStage = new ActionDialog(propString, createActionString(),
                    new StandardActionYesHandler(gameModel),
                    new StandardActionNoHandler(gameModel),
                    new ReadOnlyBooleanWrapper(!(currentPlayer.getBalance() > cost)));
            dialogStage.show();
        } else if (!model.getOwner().equals(currentPlayer)) {
            currentPlayer.decreaseBalance(rent);
            model.getOwner().increaseBalance(rent);
            GameComponent.logListWrapper.addMessage("payRent", new Object[]{
                currentPlayer.getName(),rent, model.getOwner().getName()});
            gameModel.nextTurn();
        } else {
            gameModel.nextTurn();
        }
    }

    @Override
    public Image getImage() {
        return imageView.getImage();
    }

}
