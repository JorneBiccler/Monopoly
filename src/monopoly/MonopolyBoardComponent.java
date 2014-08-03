/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */


package monopoly;

import basicgameinfo.*;
import infoholders.*;
import java.io.IOException;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javax.xml.bind.JAXBContext;
import spacebox.SpaceBox;
import spacebox.SpaceBoxPos;

/**
 * Klasse voor het aanmaken van een monopolyBord dit is dus met de gepaste
 * vakjes en infoCards.
 *
 * @author Jorne Biccler
 */
public class MonopolyBoardComponent extends StackPane {

    private final MonopolyBoardCompanion companion;
    private final List<InfoBox> infoList = new ArrayList();
    private final Map<String, Area> areaMap = new HashMap<>();

    private final GameModel gameModel;
    private final Node[] boxAr = new Node[40];
    private final Map<SpaceType, List<Card>> deckMap = new EnumMap<>(SpaceType.class);
    private static final Map<SpaceType, InfoBoxFactory> INFOFACTORYMAP;
    public static final Board BOARD;
    public static final Properties BOARDPROPERTIES;

    /**
     * static initialise van de statische variabelen, in het bijzonder wordt ook
     * de .initialize() methode van het board element opgeroepen.
     */
    static {
        INFOFACTORYMAP = new EnumMap<>(SpaceType.class);
        INFOFACTORYMAP.put(SpaceType.START, new StartBoxFactory());
        INFOFACTORYMAP.put(SpaceType.JAIL, new JailBoxFactory());
        INFOFACTORYMAP.put(SpaceType.FREE_PARKING, new FreeParkingBoxFactory());
        INFOFACTORYMAP.put(SpaceType.GO_TO_JAIL, new GoToJailBoxFactory());
        INFOFACTORYMAP.put(SpaceType.UTILITY, new UtilityBoxFactory());
        INFOFACTORYMAP.put(SpaceType.TAX, new TaxBoxFactory());
        INFOFACTORYMAP.put(SpaceType.RAILWAY, new RailwayBoxFactory());

        BOARDPROPERTIES = new Properties();
        try {
            BOARDPROPERTIES.load(MonopolyBoardComponent.class.getResourceAsStream("/resources/ugentopoly.properties"));
            JAXBContext jc = JAXBContext.newInstance(Board.class);
            BOARD = (Board) jc.createUnmarshaller().unmarshal(Board.class.getResource("/resources/monopoly.xml"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        BOARD.intialize();
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
        fillInfoList();
        fillBoxArray();
        companion.placeSpaceBoxes(boxAr);
    }

    /**
     * hulp methode voor het invullen van de array met de InfoBoxen.
     */
    private void fillBoxArray() {
        for (int i = 1; i < 10; i++) {
            boxAr[10 - i] = new SpaceBox(10 - i, gameModel, SpaceBoxPos.HORLEFT, infoList.get(10 - i));
            boxAr[20 + i] = new SpaceBox(20 + i, gameModel, SpaceBoxPos.HORRIGHT, infoList.get(20 + i));
            boxAr[10 + i] = new SpaceBox(10 + i, gameModel, SpaceBoxPos.VERTUP, infoList.get(10 + i));
            boxAr[40 - i] = new SpaceBox(40 - i, gameModel, SpaceBoxPos.VERTDOWN, infoList.get(40 - i));
        }
        for (int i = 0; i < 4; i++) {
            boxAr[10 * i] = new SpaceBox(10 * i, gameModel, SpaceBoxPos.CORNER, infoList.get(10 * i));
        }
    }

    private void fillAreaMap() {
        for (Area area : BOARD.getAreas()) {
            areaMap.put(area.getId(), area);
        }
    }

    private void fillDeckMap() {
        for (Deck deck : BOARD.getDecks()) {
            deckMap.put(deck.getType(), deck.getDeckList());
        }
    }

    /**
     * hulp methode die InfoBoxen aanmaakt en ze in het centrum van het bord
     * plaatst.
     */
    private void fillInfoList() {
        for (Space space : BOARD.getSpaces()) {
            InfoBox tempBox;
            if (INFOFACTORYMAP.containsKey(space.getType())) {
                tempBox = INFOFACTORYMAP.get(space.getType()).create(space,
                        BOARDPROPERTIES.getProperty(space.getId()));
                infoList.add(tempBox);
            } else if (space.getType() == SpaceType.STREET) {
                tempBox = new StreetBox(space, BOARDPROPERTIES.getProperty(space.getId()),
                        areaMap.get(space.getArea()));
            } else {
                tempBox = new DeckBox(space, BOARDPROPERTIES.getProperty(space.getId()),
                        deckMap.get(space.getType()));
            }
            infoList.add(space.getPosition(), tempBox);
            companion.setBoardCenter(tempBox);
        }
    }
}
