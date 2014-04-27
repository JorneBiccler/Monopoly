/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoholders;

import dialogs.ActionDialog;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import monopoly.GameModel;
import monopoly.OwnerModel;
import monopoly.Player;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public abstract class PurchasableBox extends InfoBox implements InvalidationListener{
   protected OwnerModel model = new OwnerModel();
    protected int initialRent;
    protected final ImageView imageView = new ImageView();
    private int cost;

    public PurchasableBox(Space space, String propString) {
        super(space, propString);
        initialRent = space.getRent0();
        cost = space.getCost();
        model.addListener(this);
    }

    @Override
    public void doAction(final GameModel gameModel) {
        final Player currentPlayer = gameModel.getCurrentPlayer();
        if (model.getOwner() == null) {
            Stage dialogStage = new ActionDialog(propString, createActionString(),
                    new EventHandler<Event>() {
                        public void handle(Event t) {
                            currentPlayer.addProperty(PurchasableBox.this);
                            model.setOwner(currentPlayer);
                            model.getOwner().decreaseBalance(cost);
                            gameModel.doGameAction();
                        }
                    },
                    new EventHandler<Event>() {
                        @Override
                        public void handle(Event t) {
                            gameModel.doGameAction();
                        }
                    },
                    new ReadOnlyBooleanWrapper(!(currentPlayer.getBalance() > cost)));
            dialogStage.show();
        } else if (!model.getOwner().equals(currentPlayer)) {
            currentPlayer.decreaseBalance(getRent());
            gameModel.doGameAction();
        } else {
            gameModel.doGameAction();
        }
    }

    public abstract int getRent();

    public OwnerModel getModel(){
        return model;
    }
    
    private String createActionString() {

        return "Wil je " + propString + " kopen voor: €" + space.getCost() + " ?";
    }



}
