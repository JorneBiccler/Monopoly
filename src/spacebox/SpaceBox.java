/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 *//*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package spacebox;

import basicgameinfo.SpaceType;
import infoholders.InfoBox;
import infoholders.PurchasableBox;
import java.io.IOException;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import monopoly.*;

/**
 * Uitbreiding van StackPane die i.h.b. een positie bijhoudt en van deze gebruik
 * maakt bij het luisteren naar een gameModel, en mogelijks een ownermodel (afhankelijk van het type)
 * ook kunnen spaceBoxen 'getoggled' worden bij het aanklikken van het vakje, 
 * dit geeft ook aanleiding tot een vernieuwing van de styleClasses van het bijhorende 
 * flowPane. Op dit flowPane zijn de tokens te zien horende bij dit vakje. 
 * Als het vakje gekocht wordt dan wordt de rectangle aangepast naar het 
 * benodigde kleur.
 *
 * @author Jorne Biccler
 */
public class SpaceBox extends StackPane implements InvalidationListener {

    private GameModel gameModel;
    private final int position;
    private Toggled toggle = Toggled.UNTOGGLED;
    private final SpaceBoxPos spaceBoxPos;
    private final SpaceBoxCompanion companion;
    private OwnerModel owner = null;
    private InfoBox infoBox;
    private final Rectangle rect = new Rectangle(0, 0, Color.TRANSPARENT);

    /**
     * De constructor zorgt ervoor dat het fxml bestand ingeladen wordt en in
     * het bijzonder dat de hoogte en breedte zoals ze in de spaceBoxPos
     * gespecificieerd zijn wordt.
     */
    public SpaceBox(int position, GameModel gameModel, SpaceBoxPos spaceBoxPos, InfoBox infoBox) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("SpaceBox.fxml"));
            loader.setRoot(this);
            this.companion = new SpaceBoxCompanion(spaceBoxPos);
            loader.setController(companion);
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        if (SpaceType.OWNABLE_TYPES.contains(infoBox.getSpaceType())) {
            owner = ((PurchasableBox) infoBox).getModel();
            owner.addListener(this);
            if (spaceBoxPos.getOrientation() == Orientation.HORIZONTAL) {
                rect.setHeight(spaceBoxPos.getHeight());
                rect.setWidth(10);
            } else {
                rect.setWidth(spaceBoxPos.getWidth());
                rect.setHeight(10);
            }
        }
        getChildren().add(0,rect);
        this.spaceBoxPos = spaceBoxPos;
        this.position = position;
        this.gameModel = gameModel;
        this.infoBox = infoBox;
        infoBox.setVisible(false);
        setPrefSize(spaceBoxPos.getWidth(), spaceBoxPos.getHeight());
        getStyleClass().add(spaceBoxPos.getCSSClass());
        this.gameModel.addListener(this);
        setOnMouseClicked(new SpaceBoxClickHandler());
        for (Player pl : gameModel.getListOfPlayers()) {
            if (pl.getCurrentPosition() == position) {
                companion.addFlowPaneChild(pl.getTokenImageView());
            }
        }
        updateSelection();
    }

    public InfoBox getInfoBox() {
        return infoBox;
    }

    public SpaceBoxPos getSpaceBoxPos() {
        return spaceBoxPos;
    }

    /**
     * Methode die het uitzicht van een spaceBox aanpast als het model
     * verandert. In het bijzonder wordt een pion toegevoegd aan het flowPane op het 
     * gepaste moment. En wordt de selectie aangepast indien nodig. Ook wordt indien
     * nodig de gepaste doAction() bij de infoBox opgeroepen.
     */
    @Override
    public void invalidated(Observable o) {
        Player currentPlayer = gameModel.getCurrentPlayer();
        if (gameModel.getCurrentPlayerPosition() == position
                && !getFlowPane().getChildren().contains(currentPlayer.getTokenImageView())) {
            ImageView temp = gameModel.getCurrentPlayer().getTokenImageView();
            temp.setRotate(spaceBoxPos.getRotate());
            getFlowPane().getChildren().add(temp);
            GameComponent.logListWrapper.addMessage("movePosition", new Object[]{
                gameModel.getCurrentPlayer().getName(),infoBox.getPropString()});
        }
        updateSelection();
        if (gameModel.getCurrentPlayerPosition() == position
                && gameModel.isCanDoAction()) {
            gameModel.setCanDoAction(false);
            infoBox.doAction(gameModel);
        }
        if (owner != null) {
            if (owner.getOwner() != null) {
                rect.setFill(owner.getOwner().getColor());
            } else {
                rect.setFill(Color.TRANSPARENT);
            }
        }
    }

    /**
     * MouseEvent handler, die de selectie aanpast via de invertSelection() methode.
     */
    private class SpaceBoxClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.PRIMARY) {
                invertSelection();
            }
        }
    }

    /**
     * methode die de selectie omdraait als erop geklikt wordt en de positie van de 
     * huidige speler niet met dit vakje correspondeert. Als het niet geselecteerd is wordt
     * het hierna geselecteerd, anders wordt de selectedPosition in het gameModel
     * terug de positie waarop de huidige speler zich bevindt.
     */
    private void invertSelection() {
        if (toggle == Toggled.UNTOGGLED) {
            gameModel.setSelectedPosition(position);

        } else {
            gameModel.setSelectedPosition(gameModel.getCurrentPlayerPosition());
        }
    }

    /**
     * past de lay-out van het flowPane op de gepaste manier aan.
     */
    private void updateSelection() {
        while (getFlowPane().getStyleClass().remove(toggle.getCSSClass()));
        if (position != gameModel.getSelectedPosition()) {
            toggle = Toggled.UNTOGGLED;
        } else {
            toggle = Toggled.TOGGLED;
        }

        getFlowPane().getStyleClass().add(toggle.getCSSClass());
        if (toggle == Toggled.TOGGLED) {
            infoBox.setVisible(true);
        } else {
            infoBox.setVisible(false);
        }
    }

    private FlowPane getFlowPane(){
        return companion.getFlowPane();
    }
    
}
