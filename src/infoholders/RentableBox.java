/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import dialogs.ActionDialog;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.stage.Stage;
import monopoly.GameComponent;
import monopoly.GameModel;
import monopoly.Player;

/**
 * Klasse waarvan de object horen bij huurbare vakjes, i.e. waarbij er een
 * standaard methode is om de huur te berekenen (niet afhaneklijk van de
 * dobbelstenen).
 *
 * @author Jorne Biccler
 */
public abstract class RentableBox extends PurchasableBox {

    private final RentableLabelBoxCompanion labelBoxCompanion;

    public RentableBox(Space space, String propString) {
        super(space, propString);
        labelBoxCompanion = new RentableLabelBoxCompanion(space.getCost(), initialRent);
        String labelBoxFxmlPath = "RentableLabelBox.fxml";
        createLabelBox(labelBoxCompanion, labelBoxFxmlPath);
    }

    /**
     * Zorgt ervoor dat de gepaste actie plaats vindt; als er nog geen speler dit vakje
     * bezit dan kan het mogelijks gekocht worden, indien wel en de currentPleyer is niet de 
     * owner, dan moet er huur betaald worden, anders gebeurt gaan we standaard naar de
     * volgende "ronde" in het spel.
     */ 
    @Override
    public void doAction(final GameModel gameModel) {
        final Player currentPlayer = gameModel.getCurrentPlayer();
        if (model.getOwner() == null) {
            Stage dialogStage = new ActionDialog(propString, createActionString(),
                    new StandardActionYesHandler(gameModel),
                    new StandardActionNoHandler(gameModel),
                    new ReadOnlyBooleanWrapper(!(currentPlayer.getBalance() > cost)));
            dialogStage.show();
        } else if (!model.getOwner().equals(currentPlayer)) {
            currentPlayer.decreaseBalance(getRent());
            model.getOwner().increaseBalance(getRent());
            GameComponent.logListWrapper.addMessage("payRent", new Object[]{
                currentPlayer.getName(), getRent(), model.getOwner().getName()
            });
            gameModel.nextTurn();
        } else {
            gameModel.nextTurn();
        }
    }

    public abstract int getRent();

    @Override
    public void invalidated(Observable o) {
        labelBoxCompanion.renewRent(getRent());
        labelBoxCompanion.renewOwner(model.getOwner());
    }
}
