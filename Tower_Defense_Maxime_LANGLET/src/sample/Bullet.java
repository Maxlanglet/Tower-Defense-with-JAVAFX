package sample;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;


public class Bullet extends Projectile{

    private Ellipse bullet;

    public Bullet(double positionX, double positionY, double PNJX, double PNJY, StackPane root, Tower tower, Color color){
        super( positionX, positionY,  PNJX,  PNJY, root, tower);

        bullet = new Ellipse();
        bullet.setRadiusX(4);
        bullet.setRadiusY(1);
        bullet.setFill(color);
        bullet.setOpacity(0);

        animation.setNode(bullet);
        animation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        animation.play();

        this.root = root;

        this.root.getChildren().add(bullet);

        update();

    }

    private void update(){
        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) { {
                        apparition();
                    }}}));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private double distance(){
        double distance = Math.sqrt((bullet.getTranslateX()-posTowX)*(bullet.getTranslateX()-posTowX) + (bullet.getTranslateY()-posTowY)*(bullet.getTranslateY()-posTowY));
        return distance;
    }

    private double distance(double finX, double finY){
        double distance = Math.sqrt((finX-posTowX)*(finX-posTowX) + (finY-posTowY)*(finY-posTowY));
        return distance;
    }

    private void apparition(){
        distanceParcourue = distance();
        if (distanceParcourue>20){
            bullet.setOpacity(1);
        }
        if (distanceParcourue>distance(posPNJX, posPNJY)-1){
            bullet.setOpacity(0);
            tower.getProjectiles().remove(this);
            root.getChildren().remove(bullet);
        }

    }

    public void setRadius(int radius){
        bullet.setRadiusX(radius);
    }

    public void setPause(){
        animation.pause();
    }

    public void setPlay(){
        animation.play();
    }

    public Ellipse getBullet() {
        return bullet;
    }

    @Override
    public void setOpacity(int i){
        bullet.setOpacity(0);
    }

}