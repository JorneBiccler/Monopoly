/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import monopoly.dice.Dice;

/**
 *
 * @author Jorne Biccler
 */
public class DiceButton extends Button {

    private final Dice dice;
    private EventHandler<ActionEvent> diceHandler;
    private EventHandler<ActionEvent> standardDiceHandler;
    public DiceButton() {
        dice = new Dice();
        this.setOnAction(new DiceButtonHandler());
   }

    public void setStandardDiceHandler(EventHandler<ActionEvent> standardDiceHandler) {
        this.standardDiceHandler = standardDiceHandler;
        diceHandler = standardDiceHandler;
    }
    
    public List<Integer> getLastRoll(){
        return dice.getLastRoll();
    }
    
    public void setDiceEventHandler(EventHandler<ActionEvent> handler){
        diceHandler = handler;
    }
    
    
    private class DiceButtonHandler implements EventHandler<ActionEvent> {
        
        @Override
        public void handle(ActionEvent t) {
            dice.roll(diceHandler);
        }
    }
    
}
