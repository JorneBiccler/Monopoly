/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoholders;

import dialogs.ActionDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import monopoly.GameModel;
import monopoly.Player;
import monopoly.Space;

/**
 *
 * @author Jorne Biccler
 */
public abstract class PurchasableBoxWithImage extends InfoBoxWithImage {

    protected Player owner;
    protected int initialRent;
    protected Image image;
    protected final ImageView imageView = new ImageView();

    public PurchasableBoxWithImage(Space space, String propString, GameModel gameModel) {
        super(space, propString, gameModel);
        initialRent = space.getRent0();
    }


    @Override
    public void doAction(final Player currentPlayer) {
        if (owner == null) {
            Stage dialogStage = new Stage();
            Scene dialogScene;
            dialogScene = new Scene(new ActionDialog(propString, createActionString(),
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            currentPlayer.addProperty(PurchasableBoxWithImage.this);
                            owner = currentPlayer;
                        }
                    }));
            dialogStage.setScene(dialogScene);
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.show();
        } else if(!owner.equals(currentPlayer)) {
            currentPlayer.setBalance(currentPlayer.getBalance() - getRent());
        }
    }

    
    
    public abstract int getRent();

    private String createActionString() {

        return "Wil je " + propString + "kopen voor: €" + space.getCost() + " ?";
    }

    public Player getOwner() {
        return owner;
    }

}
