/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */

package monopoly;

import javafx.scene.image.Image;

/**
 * Klasse die met een pion correspondeert, deze bevate dus een naam
 * een een image.
 * 
 * @author Jorne Biccler
 */
public class Token {
    private final Image image;
    private final String name;

    public Token(String imagePath, String name) {
        this.image = new Image(imagePath);
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
    
}
