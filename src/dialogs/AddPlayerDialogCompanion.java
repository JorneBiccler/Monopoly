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

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
 * De partner klasse van een AddPlayerDialog
 *
 * @author Jorne Biccler
 */
public class AddPlayerDialogCompanion {

    private static final Token[] TOKENAR = {
        new Token("/resources/token1.png", "Wina"),
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

    /**
     * De constructor neemt een lijst van spelers, en zorgt er voor dat de reeds
     * gebruikte pionen niet meer getoond zullen worden.
     *
     * @param playerList
     */
    public AddPlayerDialogCompanion(ObservableList<Player> playerList) {
        obsList = FXCollections.observableArrayList();
        for (Token token : TOKENAR) {
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
        addPlayerButton.setDisable(true);
        colorPicker.valueProperty().addListener(dummyDisableListener);
        nameField.textProperty().addListener(dummyDisableListener);
        addPlayerButton.setOnAction(new AddPlayerHandler());
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

    /*
     * een InvalidationListener die gebruikt wordt om naar het TextField 
     * en de color picker te luisteren, indien de naam/kleur reeds in gebruik is
     * zal deze de knop disabelen. Ook indien er reeds 4 spelers zijn kan er geen
     * nieuwe speler meer toegevoegd worden.
     */
    private InvalidationListener dummyDisableListener = new InvalidationListener() {

        private boolean computeValue() {
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
            if (playerList.size() >= 4) {
                return true;
            }
            return false;
        }

        @Override
        public void invalidated(Observable o) {
            addPlayerButton.setDisable(computeValue());
        }
    };

    /*
     * cellFactory die de cellen voor de pionen creëert.
     */
    private final Callback<ListView<Token>, ListCell<Token>> cellFactory = new Callback<ListView<Token>, ListCell<Token>>() {
        @Override
        public ListCell<Token> call(ListView<Token> p) {
            return new TokenCell();
        }
    };

    /*
     * de cell bevat de gewenste imageview als grphic en als text de gewenste tekst.
     */
    private class TokenCell extends ListCell<Token> {

        private final ImageView imageView = new ImageView();

        public TokenCell() {
            imageView.setFitHeight(20);
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
    }

}
