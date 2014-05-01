package monopoly;

import dialogs.StartDialog;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Bart Mesuere <Bart.Mesuere@UGent.be>
 */
public class Monopoly extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException, JAXBException{
        
        GameComponent gameComponent = new GameComponent();
        Scene scene = new Scene(gameComponent);
        scene.getStylesheets().add("/resources/MonopolyStyleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        Stage dialogStage = new StartDialog(gameComponent);
        dialogStage.show();    
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    

}
