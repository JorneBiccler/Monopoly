/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package dialogs;

import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * Basis uitbreiding van Stage die een dialoog meet een ok knop weergeeft, Er
 * moet een extra okHandler megegeven worden voor deze knoppen (de Handle
 * methode wordt dan opgeroepen als er geklikt wordt waarna het dialoogje
 * gesloten wordt). Indien deze dialog met de close button gesloten wordt dan
 * wordt standaard de extraOkHandler ook uitegevoerd.
 *
 * @author Jorne Biccler
 */
public class InfoDialog extends Stage {

    private InfoDialogCompanion companion;

    public InfoDialog(String infoProp, String info, final EventHandler<Event> extraOkHandler) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("InfoDialog.fxml"));
            this.companion = new InfoDialogCompanion(infoProp, info, extraOkHandler);
            loader.setController(companion);
            Parent root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            setScene(scene);
            initStyle(StageStyle.UTILITY);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initModality(Modality.APPLICATION_MODAL);
        setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                extraOkHandler.handle(t);
            }
        });
    }
}
