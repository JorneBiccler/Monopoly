/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dialogs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author jorne
 */
public class InfoDialogCompanion {
    public Label infoLabel;
    public Label infoPropLabel;
    public Button okButton;
    private final String info;
    private final String infoProp;
    private final EventHandler<Event> okCallback;
 
    public void initialize(){
        infoLabel.setText(info);
        infoPropLabel.setText(infoProp);
        okButton.setOnAction(new OkButtonHandler());
    }


    
    
    public InfoDialogCompanion(String info, String infoProp, EventHandler<Event> okCallback) {
        this.info = info;
        this.infoProp = infoProp;
        this.okCallback = okCallback;
    }
    
    class OkButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            okCallback.handle(t);
            infoLabel.getScene().getWindow().hide();
        }     
    }
    
    
}
