/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecomponents;

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
    private EventHandler<ActionEvent> jailDiceHandler;

    public DiceButton() {
        dice = new Dice();
        setOnAction(new DiceButtonHandler());
        setText("Gooi de dobbelstenen");
    }

    public void setStandardDiceHandler(EventHandler<ActionEvent> standardDiceHandler) {
        this.standardDiceHandler = standardDiceHandler;
        diceHandler = standardDiceHandler;
    }

    public void setJailHandler(EventHandler<ActionEvent> jailDiceHandler) {
        this.jailDiceHandler = jailDiceHandler;
    }

    public List<Integer> getLastRoll() {
        return dice.getLastRoll();
    }

    public void setDiceEventHandler(EventHandler<ActionEvent> handler) {
        diceHandler = handler;
    }

    public void useJailHandler() {
        diceHandler = jailDiceHandler;
    }

    public void useStandardHandler() {
        diceHandler = standardDiceHandler;
    }

    private class DiceButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            setDisable(true);
            dice.roll(diceHandler);
        }
    }
    
    public int getDiceSum(){
        int sum =0;
        for(Integer i : dice.getLastRoll()){
            sum+= i;
        }
        return sum;
    }

}
