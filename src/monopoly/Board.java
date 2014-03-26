/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorne
 */
@XmlRootElement
public class Board {

    private List<Area> areas;
    private Setting settings;
    private List<Space> spaces;

    @XmlElement(name = "settings")
    public Setting getSettings(){
        return settings;
    }
    public void setSettings(Setting settings){
        this.settings = settings;
    }
    
    
    @XmlElementWrapper(name="areas")
    @XmlElement(name="area")
    public List<Area> getAreas(){
        return areas;
    }
    public void setAreas(List<Area> areas){
        this.areas = areas;
    }
    
    
    @XmlElementWrapper(name="spaces")
    @XmlElement(name="space")
    public List<Space> getSpaces(){
        return spaces;
    }
    public void setSpaces(List<Space> spaces){
        this.spaces = spaces;
    }
    
}