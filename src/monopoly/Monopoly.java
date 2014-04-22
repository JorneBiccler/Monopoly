package monopoly;

import dialogs.StartDialogComponent;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Bart Mesuere <Bart.Mesuere@UGent.be>
 */
public class Monopoly extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, JAXBException{
        
        GameComponent root = new GameComponent();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/monopoly/MonopolyStyleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        Stage dialogStage = new Stage();
        Scene dialogScene = new Scene(new StartDialogComponent(root));
        dialogStage.setScene(dialogScene);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.show();    
        
        System.out.println(System.getProperties().get("javafx.runtime.version"));
        
//        final Dice dice = new Dice();
//        Button btn = new Button();
//        btn.setText("Rol dobbelstenen");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                dice.roll(new EventHandler<ActionEvent>() {
//
//                    @Override
//                    public void handle(ActionEvent t) {
//                        System.out.println("Klaar met rollen");
//                    }
//                });
//            }
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//
//        Scene scene = new Scene(root, 300, 250);
//
//        primaryStage.setTitle("Dobbelstenen");
//        primaryStage.setScene(scene);
//        primaryStage.show();
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
