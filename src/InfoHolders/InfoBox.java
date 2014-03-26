/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import monopoly.MonopolyBoardModel;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class InfoBox extends VBox {

    private InfoCompanion companion;
    private int position;
    private MonopolyBoardModel positionModel;
    private Map<String, InfoCompanionFactory> factories = new HashMap<>();

    public InfoBox(Space space, String propString) {
        factories.put("CHEST", new SpecialInfoCompanionFactory());
        factories.put("START", new SpecialInfoCompanionFactory());
        factories.put("CHANCE", new SpecialInfoCompanionFactory());
        factories.put("JAIL", new SpecialInfoCompanionFactory());
        factories.put("FREE_PARKING", new SpecialInfoCompanionFactory());
        factories.put("GO_TO_JAIL", new SpecialInfoCompanionFactory());
        factories.put("UTILITY", new UtilityInfoCompanionFactory());
        factories.put("STREET", new StreetInfoCompanionFactory());
        factories.put("TAX", new TaxInfoCompanionFactory());
        factories.put("RAILWAY", new RailwayInfoCompanionFactory());
        this.position = space.getPosition();
        try {
            FXMLLoader loader = new FXMLLoader(
                    InfoBox.class.getResource(factories.get(space.getType()).companionType() + ".fxml"));
            loader.setRoot(this);

            this.companion = factories.get(space.getType()).createInfoCompanion(space, propString);

            loader.setController(companion);
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public MonopolyBoardModel getModel() {
        return positionModel;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
