package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameOver {

    public GameOver(StackPane stackPane, Ressources lables) {
        VBox vBox = new VBox();
        stackPane.getChildren().clear();
        vBox.setAlignment(Pos.CENTER);

        Label gameover = new Label("GAME OVER");
        gameover.setStyle("-fx-border-color: black;\n" +
                "    -fx-border-width: 4;\n" +
                "    -fx-font-size: 15pt;\n" +
                "    -fx-text-fill: black;");
        vBox.getChildren().add(gameover);

        Sauvegarde sauvegarde = new Sauvegarde();

        String text=null;
        if (lables.getScore() > sauvegarde.getScoreTop()){
            text = "New High Score -> "+ lables.getPseudo() + " :"+ Integer.toString(lables.getScore());
        }
        else {
            text = "Your Score :"+ Integer.toString(lables.getScore()) + " High Score -> " + sauvegarde.getNameTop() + " :" + Integer.toString(sauvegarde.getScoreTop());
        }

        Label score = new Label(text);
        vBox.getChildren().add(score);

        MainMenu mainMenu = new MainMenu(stackPane, true);
        Button backMenu = mainMenu.getBackToMenu();
        vBox.getChildren().add(backMenu);

        sauvegarde.writeFile(lables.getPseudo(), lables.getScore());

        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(vBox);
    }
}
