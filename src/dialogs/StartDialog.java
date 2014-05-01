/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package dialogs;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import monopoly.GameComponent;

/**
 * Uitbreiding van Stage die een dialoog meet een Start knop en een voeg een
 * addplayer knop weergeeft. Deze dient om nieuwe speler toe te voegen (mbv een
 * addPlayerDialog) De knoppen worden op de gepaste moment gedisabled. In de
 * constructor moet de bijhorende gameComponent megeleverd worden. Na het
 * indrukkenvan de startknop wordt de initialize methode van dit component
 * opgeroepen.
 *
 * @author Jorne Biccler
 */
public class StartDialog extends Stage {

    private StartDialogCompanion companion;

    public StartDialog(GameComponent gameComponent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("StartDialog.fxml"));
            this.companion = new StartDialogCompanion(gameComponent);
            loader.setController(companion);
            Parent root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            setScene(scene);
            initStyle(StageStyle.UNDECORATED);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
