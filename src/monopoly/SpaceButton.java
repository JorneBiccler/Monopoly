/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author jorne
 */
public class SpaceButton extends Button implements InvalidationListener, EventHandler<ActionEvent> {

    private MonopolyBoardModel model;
    private int position;
    public SpaceButton(int position) {
        setOnAction(this);
        this.position = position;
        this.setStyle("-fx-background-color: transparent;");   
    }

    public MonopolyBoardModel getModel() {
        return model;
    }

    public void setModel(MonopolyBoardModel model) {
        if (model != this.model) {
            if (model != null) {
                model.removeListener(this);
            }
            this.model = model;
            if (model != null) {
                model.addListener(this);
            }
        }
    }

    @Override
    public void invalidated(Observable o) {
        if (position == model.getSelectedPosition()) {
            this.setStyle("-fx-background-color: transparent;-fx-border-color: blue;-fx-border-width: 3;");
        } else {
            this.setStyle("-fx-background-color: transparent;-fx-border-color: blue;-fx-border-width: 0;");
        }
    }

    @Override
    public void handle(ActionEvent t) {
        model.setSelectedPosition(position);
    }
}
