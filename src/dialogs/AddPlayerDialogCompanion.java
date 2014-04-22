/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import monopoly.Player;
import monopoly.Token;

/**
 *
 * @author Jorne Biccler
 */
public class AddPlayerDialogCompanion {

    private static final Token[] tokenAr = {new Token("/resources/token1.png", "Wina"),
        new Token("/resources/token2.png", "VTK"),
        new Token("/resources/token3.png", "Chemica"),
        new Token("/resources/token4.png", "Filologica"),
        new Token("/resources/token5.png", "Geologica"),
        new Token("/resources/token6.png", "VBK"),
        new Token("/resources/token7.png", "VEK"),
        new Token("/resources/token8.png", "VLK")};

    public Button addPlayerButton;
    public ColorPicker colorPicker;
    public TextField nameField;
    public ComboBox<Token> tokenBox;
    private ObservableList<Token> obsList;
    private ObservableList<Player> playerList;

    public AddPlayerDialogCompanion(ObservableList<Player> playerList) {
        obsList = FXCollections.observableArrayList();
        for (Token token : tokenAr) {
            boolean avaible = true;
            for (Player pl : playerList) {
                if (token == pl.getToken()) {
                    avaible = false;
                }
            }
            if (avaible) {
                obsList.add(token);
            }
        }
        this.playerList = playerList;
    }

    public void initialize() {
        tokenBox.setItems(obsList);
        addPlayerButton.disableProperty().bind(new SpecialBinding());
        addPlayerButton.setOnAction(new AddPlayerHandler());

        Callback<ListView<Token>, ListCell<Token>> cellFactory = new Callback<ListView<Token>, ListCell<Token>>() {
            @Override
            public ListCell<Token> call(ListView<Token> p) {
                return new ListCell<Token>() {
                    private final ImageView imageView = new ImageView();

                    {
                        imageView.setFitHeight(30);
                        imageView.setPreserveRatio(true);
                    }

                    @Override
                    protected void updateItem(Token item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            imageView.setImage(item.getImage());
                            setGraphic(imageView);
                            setText(item.getName());
                        }
                    }
                };
            }
        };
        tokenBox.setButtonCell(cellFactory.call(null));
        tokenBox.setCellFactory(cellFactory);

    }

    public class AddPlayerHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            playerList.add(new Player(nameField.getText(), tokenBox.getSelectionModel().getSelectedItem(), colorPicker.getValue(), playerList.size() + 1));
            addPlayerButton.getScene().getWindow().hide();
        }
    }

    private class SpecialBinding extends BooleanBinding {

        public SpecialBinding() {
            bind(nameField.textProperty());
            bind(colorPicker.valueProperty());
        }

        @Override
        protected boolean computeValue() {
            if (playerList.size() == 0) {
                if (nameField.getText().equals("") || tokenBox.getValue() == null) {
                    return true;
                }
            }

            for (Player pl : playerList) {
                if (nameField.getText().equals(pl.getName()) || colorPicker.getValue().equals(pl.getColor())
                        || nameField.getText().equals("") || tokenBox.getValue() == null) {
                    return true;
                }
            }
            if(playerList.size() >= 4){
                return true;
            }
            return false;
        }
    }

}
