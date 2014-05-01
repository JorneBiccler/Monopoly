/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package spacebox;

import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 * Partner klasse van een SpaceBox deze zal i.h.b. de imageView en image van de
 * speler op het vakje bijhouden.
 *
 * @author Jorne Biccler
 */
public class SpaceBoxCompanion {

    public StackPane stackPane;
    public FlowPane flowPane;
    private final SpaceBoxPos spaceBoxPos;

    public SpaceBoxCompanion(SpaceBoxPos spaceBoxPos) {
        this.spaceBoxPos = spaceBoxPos;
    }

    public void initialize() {
        flowPane.getStyleClass().add(Toggled.UNTOGGLED.getCSSClass());
        flowPane.setOrientation(spaceBoxPos.getOrientation());
        flowPane.getStyleClass().add(spaceBoxPos.getCSSClass());

    }

    public void addFlowPaneChild(Node node) {
        flowPane.getChildren().add(node);
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

}
