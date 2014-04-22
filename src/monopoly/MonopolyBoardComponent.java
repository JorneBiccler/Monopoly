/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import infoholders.*;
import java.io.IOException;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import spacebox.SpaceBox;
import spacebox.SpaceBoxPos;

/**
 *
 * @author jorne
 */
public class MonopolyBoardComponent extends StackPane {

    private final MonopolyBoardCompanion companion;
    private final List<InfoBox> infoList;
    private final Map<String, Area> areaMap = new HashMap<>();
    private final Board board;
    private final Properties boardProperties = new Properties();
    private final GameModel gameModel;
    private static final Map<SpaceType, InfoBoxFactory> infoFactoryMap;
    private final SpaceBox[] boxAr = new SpaceBox[40];

    static {
        infoFactoryMap = new EnumMap<>(SpaceType.class);
        infoFactoryMap.put(SpaceType.CHEST, new SpecialBoxFactory());
        infoFactoryMap.put(SpaceType.START, new SpecialBoxFactory());
        infoFactoryMap.put(SpaceType.CHANCE, new SpecialBoxFactory());
        infoFactoryMap.put(SpaceType.JAIL, new SpecialBoxFactory());
        infoFactoryMap.put(SpaceType.FREE_PARKING, new SpecialBoxFactory());
        infoFactoryMap.put(SpaceType.GO_TO_JAIL, new SpecialBoxFactory());
        infoFactoryMap.put(SpaceType.UTILITY, new UtilityBoxFactory());
        infoFactoryMap.put(SpaceType.STREET, new StreetBoxFactory());
        infoFactoryMap.put(SpaceType.TAX, new TaxBoxFactory());
        infoFactoryMap.put(SpaceType.RAILWAY, new RailwayBoxFactory());
    }

    public MonopolyBoardComponent(GameModel gameModel) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MonopolyBoard.fxml"));
            loader.setRoot(this);
            this.companion = new MonopolyBoardCompanion();
            loader.setController(companion);
            loader.load();

            JAXBContext jc = JAXBContext.newInstance(Board.class);
            board = (Board) jc.createUnmarshaller().unmarshal(Board.class.getResource("/resources/monopoly.xml"));
            boardProperties.load(getClass().getResourceAsStream("/resources/ugentopoly.properties"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } catch (JAXBException exception) {
            throw new RuntimeException(exception);
        }
        this.gameModel = gameModel;
        fillAreaMap();
        infoList = new ArrayList<>();
        for (Space space : board.getSpaces()) {
            InfoBox tempBox = infoFactoryMap.get(space.getType()).create(space,
                    boardProperties.getProperty(space.getId()), gameModel);
            infoList.add(tempBox);
            companion.setBoardCenter(tempBox);
            if (SpaceType.STREET == space.getType()) {
                StreetBox tempBox2 = (StreetBox) tempBox;
                tempBox2.setArea(areaMap.get(space.getArea()));
            }
        }
        fillBoxArray();
        companion.placeSpaceBoxes(boxAr);
        for (Player pl : gameModel.getPossibleModels()) {
            pl.setBalance(board.getSettings().getBalance());
        }
    }

    private void fillBoxArray() {
        for (int i = 1; i < 10; i++) {
            boxAr[10 - i] = new SpaceBox(10 - i, gameModel, SpaceBoxPos.HORLEFT, infoList.get(10-i));
            boxAr[20 + i] = new SpaceBox(20 + i, gameModel, SpaceBoxPos.HORLEFT, infoList.get(20+i));
            boxAr[10 + i] = new SpaceBox(10 + i, gameModel, SpaceBoxPos.VERTUP, infoList.get(10+i));
            boxAr[40 - i] = new SpaceBox(40 - i, gameModel, SpaceBoxPos.VERTDOWN, infoList.get(40-i));
        }
        for (int i = 0; i < 4; i++) {
            boxAr[10 * i] = new SpaceBox(10 * i, gameModel, SpaceBoxPos.CORNER, infoList.get(10*i));
        }
    }

    private void fillAreaMap() {
        for (Area area : board.getAreas()) {
            areaMap.put(area.getId(), area);
        }
    }

}
