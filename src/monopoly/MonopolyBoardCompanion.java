/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import infoHolders.*;
import java.io.IOException;
import java.util.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/*
 * Companion klasse die bij een MonopolyBoard hoort
 * @author Jorne Biccler
 */
public class MonopolyBoardCompanion implements InvalidationListener {

    public ImageView backgroundView;
    public HBox hBoxUp;
    public VBox vBoxLeft;
    public HBox hBoxDown;
    public VBox vBoxRight;
    public BorderPane borderPane;
    private SimpleIntegerProperty model;
    private Board board;
    private Image backgroundImage = new Image("/resources/bord.png");
    private SpaceButton[] buttonAr = new SpaceButton[40];
    private List<InfoBox> infoList;
    private Map<String, InfoBoxFactory> infoFactoryMap;

    public MonopolyBoardCompanion() throws JAXBException {
        infoFactoryMap = new HashMap<>();
        infoFactoryMap.put("CHEST", new SpecialBoxFactory());
        infoFactoryMap.put("START", new SpecialBoxFactory());
        infoFactoryMap.put("CHANCE", new SpecialBoxFactory());
        infoFactoryMap.put("JAIL", new SpecialBoxFactory());
        infoFactoryMap.put("FREE_PARKING", new SpecialBoxFactory());
        infoFactoryMap.put("GO_TO_JAIL", new SpecialBoxFactory());
        infoFactoryMap.put("UTILITY", new UtilityBoxFactory());
        infoFactoryMap.put("STREET", new StreetBoxFactory());
        infoFactoryMap.put("TAX", new TaxBoxFactory());
        infoFactoryMap.put("RAILWAY", new RailwayBoxFactory());

        JAXBContext jc = JAXBContext.newInstance(Board.class);
        board = (Board) jc.createUnmarshaller().unmarshal(Board.class.getResource("/resources/monopoly.xml"));

        model = new SimpleIntegerProperty(-1);
        model.addListener(this);
    }

    public void initialize() throws IOException {
        Properties properties = new Properties();
        properties.load(MonopolyBoardCompanion.class.getResourceAsStream("/resources/ugentopoly.properties"));

        backgroundView.setImage(backgroundImage);
        infoList = new ArrayList<>();
        placeButtons();
        for (Space space : board.getSpaces()) {
            InfoBox tempBox = infoFactoryMap.get(space.getType()).create(space, properties.getProperty(space.getId()));
            infoList.add(tempBox);
            if (space.getType().equals("STREET")) {
                StreetBox tempBox2 = (StreetBox) tempBox;
                for (Area area : board.getAreas()) {
                    if (area.getId().equals(space.getArea())) {
                        tempBox2.setArea(area);
                    }
                }
            }
        }
    }

    //methode die alle knopen creëert en op de juiste plaats zet
    private void placeButtons() {
        for (int i = 0; i < buttonAr.length; i++) {
            buttonAr[i] = new SpaceButton(i, model, 120, 120);
        }
        hBoxDown.getChildren().add(buttonAr[0]);
        hBoxUp.getChildren().add(buttonAr[10]);
        for (int i = 1; i < 10; i++) {
            vBoxLeft.getChildren().add(buttonAr[10 - i]);
            vBoxRight.getChildren().add(buttonAr[20 + i]);

        }
        for (int i = 1; i <= 10; i++) {
            buttonAr[10 + i].setPrefWidth(60);
            buttonAr[40 - i].setPrefWidth(60);
            hBoxUp.getChildren().add(buttonAr[10 + i]);
            hBoxDown.getChildren().add(buttonAr[40 - i]);
        }
        buttonAr[20].setPrefWidth(120);
        buttonAr[30].setPrefWidth(120);
    }

    /* een MonopolyBoardCompanion luistert naar een MonopolyModel
     * en verandert het kind in het centrum zoals gewenst
     */
    @Override
    public void invalidated(Observable o) {
        for (InfoBox info : infoList) {
            if (model.getValue()== info.getPosition()) {
                borderPane.setCenter(info);
            }
        }
        if (model.getValue()== -1) {
            borderPane.setCenter(null);
        }
    }

}
