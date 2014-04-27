package dialogs;

import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorne
 */
public class InfoDialogComponent extends Stage{
       private InfoDialogCompanion companion;

    
    public InfoDialogComponent(String infoProp, String info,  final EventHandler<Event> okCallback) {
       try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("InfoDialog.fxml"));         
            this.companion = new InfoDialogCompanion(infoProp, info, okCallback);
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
                okCallback.handle(t);
                }
            
            }
        );  
    }
}
