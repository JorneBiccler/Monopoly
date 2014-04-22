/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jorne
 */
public class ActionDialog extends AnchorPane {


    private final ActionDialogCompanion companion;

    public ActionDialog(String infoProp, String actionInfo,
            EventHandler<ActionEvent> yesCallback) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ActionDialog.fxml"));
            loader.setRoot(this);
            this.companion = new ActionDialogCompanion(infoProp, actionInfo, yesCallback);
            loader.setController(companion);

            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
