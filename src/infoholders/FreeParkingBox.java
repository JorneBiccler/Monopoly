/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoholders;

import dialogs.InfoDialogComponent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import monopoly.GameModel;
import monopoly.Player;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class FreeParkingBox extends SpecialBox {

    public FreeParkingBox(Space space, String propString) {
        super(space, propString);
    }

    @Override
    public void doAction(final GameModel gameModel) {
        final Player currentPlayer = gameModel.getCurrentPlayer();
        final int jackpot = gameModel.getJackpot();

        if (gameModel.getJackpot() > 0) {
            Stage dialogStage = new InfoDialogComponent(propString, "Je hebt €" + jackpot + " ontvangen",
                    new EventHandler<Event>() {
                        public void handle(Event t) {
                            currentPlayer.increaseBalance(jackpot);
                            gameModel.setJackpot(0);
                            gameModel.doGameAction();
                        }
                    }
            );
            dialogStage.show();
        }   
        else{
            gameModel.doGameAction();
        }
    }

}
