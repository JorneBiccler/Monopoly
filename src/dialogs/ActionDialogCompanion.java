/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import javafx.event.ActionEvent;
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
    private final EventHandler<ActionEvent> yesHandler;
    private final String infoProp;
    private final String actionInfo;

    public ActionDialogCompanion(String infoProp, String actionInfo, EventHandler<ActionEvent> yesHandler) {
        this.yesHandler = yesHandler;
        this.infoProp = infoProp;
        this.actionInfo = actionInfo;
    }

    public void initialize() {
        infoPropLabel.setText(infoProp);
        actionQuestionLabel.setText(actionInfo);
        yesButton.setOnAction(new finalYesHandler());
        noButton.setOnAction(new finalNoHandler());
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
            noButton.getScene().getWindow().hide();
        }

    }

}
