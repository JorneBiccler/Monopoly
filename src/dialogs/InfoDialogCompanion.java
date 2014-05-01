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
 * Basis uitbreiding van Stage die een dialoog meet een ok knop weergeeft, Er
 * moet een extra okHandler megegeven worden voor deze knoppen (de Handle
 * methode wordt dan opgeroepen als er geklikt wordt waarna het dialoogje
 * gesloten wordt). Indien deze dialog met de close button gesloten wordt dan
 * wordt standaard de extraNoHandler ook uitegevoerd.
 *
 * @author Jorne Biccler
 */
public class InfoDialogCompanion {

    public Label infoLabel;
    public Label infoPropLabel;
    public Button okButton;
    private final String info;
    private final String infoProp;
    private final EventHandler<Event> extraOkHandler;

    public void initialize() {
        infoLabel.setText(info);
        infoPropLabel.setText(infoProp);
        okButton.setOnAction(new OkButtonHandler());
    }

    public InfoDialogCompanion(String info, String infoProp, EventHandler<Event> extraOkHandler) {
        this.info = info;
        this.infoProp = infoProp;
        this.extraOkHandler = extraOkHandler;
    }

    class OkButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent t) {
            extraOkHandler.handle(t);
            infoLabel.getScene().getWindow().hide();
        }
    }

}
