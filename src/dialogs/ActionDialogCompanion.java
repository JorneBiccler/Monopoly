/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 *//*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 *//*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 *//*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package dialogs;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Partner klasse horend bij een ActionDialog, er zijn twee binneklassen die de
 * eventhandlers van de button's zijn zoals het in ActionDialog gespecificeerd
 * staat.
 *
 * @author Jorne Biccler
 */
public class ActionDialogCompanion {

    public Button yesButton;
    public Button noButton;
    public Label infoPropLabel;
    public Label actionQuestionLabel;
    private final EventHandler<Event> yesHandler;
    private final String infoProp;
    private final String actionInfo;
    private final EventHandler<Event> noHandler;
    private final BooleanProperty disableYesButton;

    public ActionDialogCompanion(String infoProp, String actionInfo,
            EventHandler<Event> yesHandler, EventHandler<Event> noHandler,
            BooleanProperty disableYesButton) {
        this.yesHandler = yesHandler;
        this.noHandler = noHandler;
        this.infoProp = infoProp;
        this.actionInfo = actionInfo;
        this.disableYesButton = disableYesButton;
    }

    public void initialize() {
        infoPropLabel.setText(infoProp);
        actionQuestionLabel.setText(actionInfo);
        yesButton.setOnAction(new FINALYESHANDLER());
        noButton.setOnAction(new FINALNOHANDLER());
        yesButton.disableProperty().bind(disableYesButton);
    }

    class FINALYESHANDLER implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            yesHandler.handle(t);
            yesButton.getScene().getWindow().hide();
        }

    }

    class FINALNOHANDLER implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            noHandler.handle(t);
            noButton.getScene().getWindow().hide();
        }
    }

}
