/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import infoHolders.InfoBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 *
 * @author jorne
 */
public class MonopolyBoardCompanion implements InvalidationListener{

    public ImageView backgroundView;
    public HBox hBoxUp;
    public VBox vBoxLeft;
    public HBox hBoxDown;
    public VBox vBoxRight;
    public BorderPane borderPane;
    private MonopolyBoardModel model;
    private Board board;
    private Image backgroundImage = new Image("/resources/bord.png");
    private SpaceButton[] buttonAr = new SpaceButton[40];
    private List<InfoBox> infoList;

    public MonopolyBoardCompanion() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Board.class);
        board = (Board) jc.createUnmarshaller().unmarshal(Board.class.getResource("/resources/monopoly.xml"));

        model = new MonopolyBoardModel(-1);
        model.addListener(this);
        for (int i = 0; i < buttonAr.length; i++) {
            buttonAr[i] = new SpaceButton(i);
            buttonAr[i].setModel(model);
            buttonAr[i].setPrefWidth(120);
            buttonAr[i].setPrefHeight(120);
        }
    }

    public void initialize() throws IOException {
        Properties properties = new Properties();
        properties.load(MonopolyBoardCompanion.class.getResourceAsStream("/resources/ugentopoly.properties"));

        backgroundView.setImage(backgroundImage);
        infoList = new ArrayList<>();
        placeButtons(buttonAr);

        for (Space space : board.getSpaces()) {
            infoList.add(new InfoBox(space,  properties.getProperty(space.getId())));
        }
    }


    /* Sinds ik ervanuit ga dat een monopoly spel altijd evenveel vakjes wordt gebruik ik de volgende aanpak
     om de buttons in de juiste volgorde te plaatsen*/
    private void placeButtons(SpaceButton[] ar) {
        hBoxDown.getChildren().add(ar[0]);
        hBoxUp.getChildren().add(ar[10]);
        for (int i = 1; i < 10; i++) {
            vBoxLeft.getChildren().add(ar[10 - i]);
            vBoxRight.getChildren().add(ar[20 + i]);

        }
        for (int i = 1; i <= 10; i++) {
            ar[10 + i].setPrefWidth(60);
            ar[40 - i].setPrefWidth(60);
            hBoxUp.getChildren().add(ar[10 + i]);
            hBoxDown.getChildren().add(ar[40 - i]);
        }
        ar[20].setPrefWidth(120);
        ar[30].setPrefWidth(120);
    }

    @Override
    public void invalidated(Observable o) {
        for(InfoBox info: infoList){
            if(model.getSelectedPosition()== info.getPosition()){
                borderPane.setCenter(info);
            }
        }
        if(model.getSelectedPosition() == -1){
            borderPane.setCenter(null);
        }
    }

}
