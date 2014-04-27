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
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javax.xml.bind.JAXBContext;
import spacebox.OwnableSpaceBoxWrapper;
import spacebox.SpaceBox;
import spacebox.SpaceBoxPos;
/**
 *
 * @author jorne
 */
public class MonopolyBoardComponent extends StackPane {

    private final MonopolyBoardCompanion companion;
    private final List<InfoBox> infoList = new ArrayList();
    private final Map<String, Area> areaMap = new HashMap<>();
    public static final Board board;
    public static final Properties boardProperties;
    private final GameModel gameModel;
    private static final Map<SpaceType, InfoBoxFactory> infoFactoryMap;
    private final Node[] boxAr = new Node[40];
    private final Map<SpaceType, List<Card>> deckMap = new EnumMap<>(SpaceType.class);

    static {
        infoFactoryMap = new EnumMap<>(SpaceType.class);
        infoFactoryMap.put(SpaceType.START, new StartBoxFactory());
        infoFactoryMap.put(SpaceType.JAIL, new JailBoxFactory());
        infoFactoryMap.put(SpaceType.FREE_PARKING, new FreeParkingBoxFactory());
        infoFactoryMap.put(SpaceType.GO_TO_JAIL, new GoToJailBoxFactory());
        infoFactoryMap.put(SpaceType.UTILITY, new UtilityBoxFactory());
        infoFactoryMap.put(SpaceType.TAX, new TaxBoxFactory());
        infoFactoryMap.put(SpaceType.RAILWAY, new RailwayBoxFactory());
        
        boardProperties = new Properties();   
        try {
            boardProperties.load(MonopolyBoardComponent.class.getResourceAsStream("/resources/ugentopoly.properties"));
            JAXBContext jc = JAXBContext.newInstance(Board.class);
            board = (Board) jc.createUnmarshaller().unmarshal(Board.class.getResource("/resources/monopoly.xml"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public MonopolyBoardComponent(GameModel gameModel) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MonopolyBoard.fxml"));
            loader.setRoot(this);
            this.companion = new MonopolyBoardCompanion();
            loader.setController(companion);
            loader.load();
            
            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.gameModel = gameModel;
        fillAreaMap();
        fillDeckMap();
        for (Space space : board.getSpaces()) {
            fillInfoList(space);
        }
        fillBoxArray();
        companion.placeSpaceBoxes(boxAr);
        for (Player pl : gameModel.getPossibleModels()) {
            pl.setBalance(board.getSettings().getBalance());
        }
    }

    private void fillBoxArray() {
        for (int i = 1; i < 10; i++) {
            boxAr[10 - i] = createSpaceNode(10 - i,  SpaceBoxPos.HORLEFT, infoList.get(10 - i));
            boxAr[20 + i] = createSpaceNode(20 + i,  SpaceBoxPos.HORRIGHT, infoList.get(20 + i));
            boxAr[10 + i] = createSpaceNode(10 + i,  SpaceBoxPos.VERTUP, infoList.get(10 + i));
            boxAr[40 - i] = createSpaceNode(40 - i,  SpaceBoxPos.VERTDOWN, infoList.get(40 - i));
        }
        for (int i = 0; i < 4; i++) {
            boxAr[10 * i] = createSpaceNode(10 * i, SpaceBoxPos.CORNER, infoList.get(10 * i));
        }
    }
    
    private Node createSpaceNode(int position, SpaceBoxPos spaceBoxEnum, InfoBox infoBox){
        SpaceBox box = new SpaceBox(position,gameModel, spaceBoxEnum,infoBox);
        if(SpaceType.OWNABLE_TYPES.contains(infoBox.getSpaceType())){
            return new OwnableSpaceBoxWrapper(box);
        }
        return box;       
    }
    

    private void fillAreaMap() {
        for (Area area : board.getAreas()) {
            areaMap.put(area.getId(), area);
        }
    }

    private void fillDeckMap() {
        for (Deck deck : board.getDecks()) {
            deckMap.put(deck.getType(), deck.getDeckList());
        }
    }

    private void fillInfoList(Space space) {
        InfoBox tempBox;
        if (infoFactoryMap.containsKey(space.getType())) {
            tempBox = infoFactoryMap.get(space.getType()).create(space,
                    boardProperties.getProperty(space.getId()));
            infoList.add(tempBox);
        } else if (space.getType() == SpaceType.STREET) {
            tempBox = new StreetBox(space, boardProperties.getProperty(space.getId()), 
                    areaMap.get(space.getArea()));
        } else {
            tempBox = new DeckBox(space, boardProperties.getProperty(space.getId()),
                     deckMap.get(space.getType()));
        }
        infoList.add(space.getPosition(), tempBox);
        companion.setBoardCenter(tempBox);
    }
}
