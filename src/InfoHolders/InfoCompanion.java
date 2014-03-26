/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import monopoly.Space;

/**
 *
 * @author jorne
 */
public class InfoCompanion {

    private Space space;
    private String infoProp;

    public InfoCompanion(Space space, String infoProp) {
        this.space = space;
        this.infoProp = infoProp;
    }



    public String getInfoProp() {
        return infoProp;
    }

    public void setInfoProp(String infoProp) {
        this.infoProp = infoProp;
    }

    public String getType() {
        return space.getType();
    }

    public String getId() {
        return space.getId();
    }

}
