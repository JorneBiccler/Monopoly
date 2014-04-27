/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Jorne Biccler
 */
public class ActionDialogCompanion {

    public Button yesButton;
    public Button noButton;
    public Label infoPropLabel;
    public Label actionQuestionLabel;
    private final EventHandler<Event> yesHandler;
    private final String infoProp;
    private final String actionInfo;
    private final EventHandler<Event> noHandler;
    private final BooleanProperty disableYesButton;
    
    public ActionDialogCompanion(String infoProp, String actionInfo, 
            EventHandler<Event> yesHandler, EventHandler<Event> noHandler,
            BooleanProperty disableYesButton) {
        this.yesHandler = yesHandler;
        this.noHandler = noHandler;
        this.infoProp = infoProp;
        this.actionInfo = actionInfo;
        this.disableYesButton = disableYesButton;
    }

    public void initialize() {
        infoPropLabel.setText(infoProp);
        actionQuestionLabel.setText(actionInfo);
        yesButton.setOnAction(new finalYesHandler());
        noButton.setOnAction(new finalNoHandler());
        yesButton.disableProperty().bind(disableYesButton);
    }

    class finalYesHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            yesHandler.handle(t);
            yesButton.getScene().getWindow().hide();
        }

    }

    class finalNoHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            noHandler.handle(t);
            noButton.getScene().getWindow().hide();
        }

    }
    

}
