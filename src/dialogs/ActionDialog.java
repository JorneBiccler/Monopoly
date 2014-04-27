/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author jorne
 */
public class ActionDialog extends Stage {


    private final ActionDialogCompanion companion;

    public ActionDialog(String infoProp, String actionInfo,
            EventHandler<Event> extraYesHandler, final EventHandler<Event> extraNoHandler,
            BooleanProperty disableYesButton) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ActionDialog.fxml"));         
            this.companion = new ActionDialogCompanion(infoProp, actionInfo, extraYesHandler, extraNoHandler,disableYesButton);
            loader.setController(companion);
            Parent root = (AnchorPane) loader.load();
            Scene scene = new Scene (root);
            setScene(scene);
            initStyle(StageStyle.UTILITY);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        setOnCloseRequest(new EventHandler<WindowEvent>(){

            @Override
            public void handle(WindowEvent t) {
                extraNoHandler.handle(t);
                }
            
            }
        );    
    }

        
    }
    
    

