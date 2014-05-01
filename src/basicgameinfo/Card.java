/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package basicgameinfo;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Klasse die de standaard info rond kaartjes objecten bijhoudt i.h.b. zo
 * opgesteld dat ze van JAXB gebruik kan maken.
 *
 * @author Jorne Biccler
 */
public class Card {

    private CardType type;
    private String id;
    private int amount;
    private boolean collect;
    private int position;
    private int relative;

    @XmlAttribute
    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @XmlAttribute
    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    @XmlAttribute
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @XmlAttribute
    public int getRelative() {
        return relative;
    }

    public void setRelative(int relative) {
        this.relative = relative;
    }

}
