/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dialogs;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import monopoly.Player;

/**
 *
 * @author jorne
 */
public class AddPlayerDialogComponent extends AnchorPane{
    private final AddPlayerDialogCompanion companion;
    public AddPlayerDialogComponent(ObservableList<Player> playerList) {
          try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("AddPlayerDialog.fxml"));
            loader.setRoot(this);
            this.companion = new AddPlayerDialogCompanion(playerList);
            loader.setController(companion);

            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}
