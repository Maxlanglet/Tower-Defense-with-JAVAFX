package sample;

import javafx.animation.PathTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.util.Duration;


public abstract class Projectile {

    protected double posTowX;
    protected double posTowY;
    protected double posPNJX;
    protected double posPNJY;

    protected Path trajectoire;

    protected PathTransition animation;

    protected double distanceParcourue;

    protected StackPane root;

    protected Tower tower;

    protected Ellipse bullet;

    public Projectile(double positionX, double positionY, double PNJX, double PNJY, StackPane root, Tower tower){
        posPNJX = PNJX;
        posPNJY = PNJY;
        posTowX = positionX;
        posTowY = positionY;

        this.tower=tower;

        trajectoire = new Path();

        trajectoire.getElements().add(new MoveTo(posTowX, posTowY));
        trajectoire.getElements().add(new LineTo(posPNJX, posPNJY));

        animation = new PathTransition();

        animation.setDuration(Duration.seconds(0.2));
        animation.setPath(trajectoire);
        animation.setCycleCount(1);

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

    public void setOpacity(int i){
        bullet.setOpacity(i);
    }


    public PathTransition getAnimation() {
        return animation;
    }
}
