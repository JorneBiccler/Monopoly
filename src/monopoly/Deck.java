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

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author jorne
 */
public class Deck {
    private List<Card> deckList;
    private SpaceType type;
    
    @XmlAttribute(name="type")
    public void setType(SpaceType type) {
        this.type = type;
    }

    public SpaceType getType() {
        return type;
    }


    @XmlElement(name="card")
    public List<Card> getDeckList() {
        return deckList;
    }

    public void setDeckList(List<Card> deckList) {
        this.deckList = deckList;
    }
 
}
