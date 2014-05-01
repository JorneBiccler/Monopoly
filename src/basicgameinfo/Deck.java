/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package basicgameinfo;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Klasse die de standaard info rond een deck object bijhoudt i.h.b. zo
 * opgesteld dat ze van JAXB gebruik kan maken.
 *
 * @author Jorne Biccler
 */
public class Deck {

    private List<Card> deckList;
    private SpaceType type;

    @XmlAttribute(name = "type")
    public void setType(SpaceType type) {
        this.type = type;
    }

    public SpaceType getType() {
        return type;
    }

    @XmlElement(name = "card")
    public List<Card> getDeckList() {
        return deckList;
    }

    public void setDeckList(List<Card> deckList) {
        this.deckList = deckList;
    }

}
