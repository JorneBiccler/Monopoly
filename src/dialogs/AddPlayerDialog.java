/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package dialogs;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import monopoly.Player;

/**
 * Component die Stage uitbreidt, hiermee kunnen spelers worden toegevoegd, dit
 * met behulp van een TextField voor een naam in te geven, een ComboBox voor het
 * selecteren van de gewenste pion, en ten slotte een ColorPicker voor het
 * kiezen van een kleur.
 *
 * @author Jorne Biccler
 */
public class AddPlayerDialog extends Stage {

    private final AddPlayerDialogCompanion companion;

    public AddPlayerDialog(ObservableList<Player> playerList) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("AddPlayerDialog.fxml"));
            this.companion = new AddPlayerDialogCompanion(playerList);
            loader.setController(companion);
            Parent root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            setScene(scene);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initStyle(StageStyle.UTILITY);
    }

}
