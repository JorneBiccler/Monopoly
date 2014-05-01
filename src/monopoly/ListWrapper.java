/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import java.text.MessageFormat;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wrapper klasse die een lijst van Strings bevat, en een resourceBundle,
 * deze kan met behulp van deze resourceBundle nieuwe Strings toevoegen
 * aan deze lijst.
 * 
 * @author Jorne Biccler
 */
public class ListWrapper{
    
    private final ResourceBundle bundle = ResourceBundle.getBundle("resources/allKindsOfText");
    private final ObservableList<String> list =  FXCollections.observableArrayList();

    /**
     * Maakt een nieuwe string aan met behulp van de gegeven parameters (zoals bij 
     * standaard gebruik van resourcebundles) en voegt deze aan de lijst toe.
     */
    public void addMessage(String key, Object[] parameters){
        String pattern = bundle.getString(key);
        MessageFormat mf = new MessageFormat(pattern);
        String message = mf.format(parameters);     
        list.add(0,message);
    }

    public ObservableList<String> getList() {
        return list;
    }
    

}
