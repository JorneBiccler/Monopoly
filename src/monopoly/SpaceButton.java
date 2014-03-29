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
import javafx.scene.control.ToggleButton;

/**
 *
 * @author jorne
 */
public class SpaceButton extends ToggleButton implements InvalidationListener, EventHandler<ActionEvent> {

    private SimpleIntegerProperty model;
    private int position;

    public SpaceButton(int position, SimpleIntegerProperty model, double height, double width) {
        setOnAction(this);
        this.position = position;
        this.getStyleClass().add("button-unselected");
        this.model = model;
        model.addListener(this);
        this.setPrefHeight(height);
        this.setPrefWidth(width);       
    }

    public SimpleIntegerProperty getModel() {
        return model;
    }

    public void setModel(SimpleIntegerProperty model) {
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
        if (position == model.getValue()) {
            this.getStyleClass().clear();
            this.getStyleClass().add("button-selected");
        } else {
            this.getStyleClass().clear();
            this.setSelected(false);
            this.getStyleClass().add("button-unselected");
        }
    }

    @Override
    public void handle(ActionEvent t) {
        if(this.isSelected() == true){
            model.set(position);
        }
        if(this.isSelected() == false){
            model.set(-1);
        }
    }
}
