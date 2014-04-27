/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import monopoly.GameComponent;
import monopoly.Player;

/**
 *
 * @author jorne
 */
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

        startButton.disableProperty().bind(new DisableAddPlayerBinding());
        addPlayerButton.setOnAction(new AddPlayerHandler());
        startButton.disableProperty().bind(new DisableStartBinding());
        tokenColumn.setCellValueFactory(
                new PropertyValueFactory<Player, ImageView>("tokenImageView"));
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Player, String>("name"));       
        tableView.setItems(playerObsList);
    }

    public class AddPlayerHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Stage dialogStage = new Stage();
            Scene dialogScene = new Scene(new AddPlayerDialogComponent(playerObsList));
            dialogStage.setScene(dialogScene);
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.show();

        }
    }

    private class DisableStartBinding extends BooleanBinding {

        public DisableStartBinding() {
            bind(playerObsList);
        }

        @Override
        protected boolean computeValue() {
            if (playerObsList.size() < 2) {
                return true;
            }
            return false;
        }

    }

    private class DisableAddPlayerBinding extends BooleanBinding {

        public DisableAddPlayerBinding() {
            bind(Bindings.size(playerObsList));
        }

        @Override
        protected boolean computeValue() {
            if (playerObsList.size() >= 4) {
                return true;
            }
            return false;
        }

    }
}
