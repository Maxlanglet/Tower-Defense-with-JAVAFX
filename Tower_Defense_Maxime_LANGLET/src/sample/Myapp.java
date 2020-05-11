package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;


//TODO: change sound et essayer de mettre musique en static
//TODO: set Cursor croix



public class Myapp extends Application {

    StackPane root = new StackPane();
    
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Tower Defense");

        MainMenu mainMenu = new MainMenu(root);


        //Cette Photo ne m'appartient pas


        String style = "-fx-background-image: url(\"/Images/mars_pix.png\"); " + "-fx-background-repeat: stretch;"
                + "-fx-background-size: 1000.0 820.0;";
        root.setStyle(style);
        Scene scene = new Scene(root, 1000, 820);
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(820);
        stage.show();

    }

}
