/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasse waarvan een object alle 'spelelementen' bijhoudt in het bijzonder zo
 * geschreven dat ook JAXB er gebruik van kan maken
 *
 * @author Jorne Biccler
 */
@XmlRootElement
public class Board {

    private List<Area> areas;
    private Setting settings;
    private List<Space> spaces;
    private List<Deck> decks;


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
        for(Area area: areas){
            
        }
        
        
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
    

}
