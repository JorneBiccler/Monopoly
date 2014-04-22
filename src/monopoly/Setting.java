/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Klasse waarvan de objecten de settings van het monopoly spel bijhouden i.h.b.
 * zo geschreven dat deze van JAXB gebruik kan maken
 *
 * @author jorne
 */
public class Setting {

    private int balance;
    private int size;
    private int go;

    @XmlAttribute
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @XmlAttribute
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @XmlAttribute
    public int getGo() {
        return go;
    }

    public void setGo(int go) {
        this.go = go;
    }

}
