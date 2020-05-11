package sample;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class Missile extends Projectile {

    private ImageView missile;


    public Missile(double positionX, double positionY, double PNJX, double PNJY, StackPane root, Tower tower, ImageView missile) {
        super(positionX, positionY, PNJX, PNJY, root, tower);

        this.missile = missile;
        missile.setOpacity(1);

        animation.setNode(missile);
        animation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        animation.setDuration(Duration.seconds(0.35));

        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(missile);
            }
        });

        animation.play();

        this.root = root;

        this.root.getChildren().add(missile);
    }

    @Override
    public void setOpacity(int i){
        missile.setOpacity(0);
    }
}
