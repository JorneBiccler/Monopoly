/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacebox;

import infoholders.PurchasableBox;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Orientation;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import monopoly.OwnerModel;

/**
 *
 * @author Jorne Biccler
 */
public class OwnableSpaceBoxWrapper extends StackPane implements InvalidationListener {

    private final OwnerModel model;
    private final Rectangle rect = new Rectangle(0, 0, Color.TRANSPARENT);
    private final PurchasableBox purchasableBox;
    private final SpaceBoxPos styleEnum;

    public OwnableSpaceBoxWrapper(SpaceBox box) {
        this.styleEnum = box.getSpaceBoxPos();
        purchasableBox = (PurchasableBox) box.getInfoBox();
        model = purchasableBox.getModel();
        model.addListener(this);
        getChildren().add(rect);
        getChildren().add(box);
        if (styleEnum.getOrientation() == Orientation.HORIZONTAL) {
            rect.setHeight(styleEnum.getHeight());
            rect.setWidth(10);
        } else {
            rect.setWidth(styleEnum.getWidth());
            rect.setHeight(10);
        }
        setHeight(box.getHeight());
        setWidth(box.getWidth());
        getStyleClass().add(styleEnum.getCSSClass());
    }

    @Override
    public void invalidated(Observable o) {
        if (model.getOwner() != null) {
            rect.setFill(model.getOwner().getColor());
        } else {
            rect.setFill(Color.TRANSPARENT);
        }
    }

}
