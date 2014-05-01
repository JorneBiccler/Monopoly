/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package basicgameinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasse waarvan een object alle 'spelelementen' bijhoudt in het bijzonder zo
 * geschreven dat ook JAXB er gebruik van kan maken. Er is ook een initialize
 * methode die best opgeroepen wordt na het inladen.
 *
 * @author Jorne Biccler
 */
@XmlRootElement
public class Board {

    private List<Area> areas;
    private Setting settings;
    private List<Space> spaces;
    private List<Deck> decks;
    private Map<Integer, String> spaceIdMap = new HashMap<>();

    @XmlElement(name = "settings")
    public Setting getSettings() {
        return settings;
    }

    public void setSettings(Setting settings) {
        this.settings = settings;
    }

    @XmlElementWrapper(name = "areas")
    @XmlElement(name = "area")
    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    @XmlElementWrapper(name = "spaces")
    @XmlElement(name = "space")
    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }

    @XmlElement(name = "deck")
    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }

    /**
     * Deze method voert verdere initialisering voor de elementen van het bord
     * uit. I.h.b. wordt numberOfStreet van de areas aangevuld, en wordt de
     * spaceIdMap gevuld.
     */
    public void intialize() {
        for (Space space : getSpaces()) {
            if (space.getType() == SpaceType.STREET) {
                for (Area area : getAreas()) {
                    if (space.getArea().equals(area.getId())) {
                        area.incrementNumberOfStreets();
                    }
                }
            }
            spaceIdMap.put(space.getPosition(), space.getId());;
        }
    }

    /**
     * deze methode geeft een map terug met voro idere positie het bijhorende id
     * van het space objest. Let op: zal enkel werken als de initialize methode
     * reeds is opgeroepen.
     */
    public Map<Integer, String> getSpaceIdMap() {
        return spaceIdMap;
    }

}
