/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import monopoly.GameComponent;

/**
 *
 * @author jorne
 */
public class StartDialogComponent extends AnchorPane {

    private StartDialogCompanion companion;
    private final GameComponent gameComponent;
    
    
    public StartDialogComponent( GameComponent gameComponent) {
        this.gameComponent = gameComponent;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("StartDialog.fxml"));
            loader.setRoot(this);
            this.companion = new StartDialogCompanion(gameComponent);
            loader.setController(companion);

            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
