/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

import javafx.scene.image.Image;

/**
 *
 * @author jorne
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
