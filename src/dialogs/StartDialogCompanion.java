/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package dialogs;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import monopoly.GameComponent;
import monopoly.Player;

public class StartDialogCompanion {

    public Button startButton;
    public Button addPlayerButton;
    public TableView<Player> tableView;
    public TableColumn<Player, ImageView> tokenColumn;
    public TableColumn<Player, String> nameColumn;
    private ObservableList<Player> playerObsList;
    private GameComponent gameComponent;

    public StartDialogCompanion(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
        playerObsList = FXCollections.observableArrayList();
    }

    public void initialize() {
        startButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        gameComponent.initializeGame(playerObsList);
                        startButton.getScene().getWindow().hide();
                    }
                });
        startButton.setDisable(true);
        playerObsList.addListener(new SomeDisableListener(2, "less", startButton));
        playerObsList.addListener(new SomeDisableListener(3, "greater", addPlayerButton));
        addPlayerButton.setOnAction(new AddPlayerHandler());
        tokenColumn.setCellValueFactory(
                new PropertyValueFactory<Player, ImageView>("tokenImageView"));
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Player, String>("name"));
        tableView.setItems(playerObsList);
    }

    public class AddPlayerHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Stage dialogStage = new AddPlayerDialog(playerObsList);
            dialogStage.show();

        }
    }

    private class SomeDisableListener implements InvalidationListener {

        private final String type;
        private final int size;
        private final Button someButton;

        public SomeDisableListener(int size, String type, Button someButton) {
            this.someButton = someButton;
            this.type = type;
            this.size = size;
        }

        public boolean computeValue() {
            if (type.equals("less")) {
                return playerObsList.size() < size;

            } else {
                return playerObsList.size() > size;
            }
        }

        @Override
        public void invalidated(Observable o) {
            someButton.setDisable(computeValue());
        }
    }

}
