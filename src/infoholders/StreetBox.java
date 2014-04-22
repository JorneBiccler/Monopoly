/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import dialogs.ActionDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import monopoly.*;

/**
 * VBox die alle info rond 'street' types weergeeft
 *
 * @author Jorne Biccler
 */
public class StreetBox extends InfoBox {

    private final RentableLabelBoxCompanion labelBoxCompanion;
    private final RectangleBox rectBox;
    private final int initialrent;
    private final int cost;
    private Player owner;
    private Area area;

    public StreetBox(Space space, String propString, GameModel gameModel) {
        super(space, propString, gameModel);
        if (SpaceType.STREET != space.getType()) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        this.cost = space.getCost();
        this.initialrent = space.getRent0();
        rectBox = new RectangleBox();
        addNodeViewBox(rectBox);
        labelBoxCompanion = new RentableLabelBoxCompanion(space.getCost(), initialrent);
        String labelBoxFxmlPath = "RentableLabelBox.fxml";
        createLabelBox(labelBoxCompanion, labelBoxFxmlPath);
    }

    public int getRent() {
        return initialrent;
    }

    public Area getArea() {
        return area;
    }

    /**
     * methode die de area 'set' en ook de kleur van de Rectangle aanpast
     */
    public void setArea(Area area) {
        this.area = area;
        rectBox.changeRectangleColor(area.getAreaColor());
    }

    public Color getColor() {
        return area.getAreaColor();
    }

    @Override
    public void doAction(final Player currentPlayer) {
        if (owner == null) {
            Stage dialogStage = new Stage();
            Scene dialogScene;
            dialogScene = new Scene(new ActionDialog(propString, createActionString(),
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            currentPlayer.addProperty(StreetBox.this);
                            currentPlayer.setBalance(currentPlayer.getBalance() - getCost());
                            owner = currentPlayer;
                        }
                    }));
            dialogStage.setScene(dialogScene);
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.show();
        } else if(!owner.equals(currentPlayer)) {
            currentPlayer.setBalance(currentPlayer.getBalance() - getRent());
        }
    }

    private String createActionString() {

        return "Wil je " + propString + "kopen voor: €" + space.getCost() + " ?";
    }

    public int getCost() {
        return cost;
    }
    
}
