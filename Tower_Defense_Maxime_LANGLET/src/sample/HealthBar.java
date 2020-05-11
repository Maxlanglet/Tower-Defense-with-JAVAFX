package sample;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar{

    public Rectangle rectInt;

    private double innerWidth;//health


    public HealthBar(path path, double health) {

            double hauteur = 2;


            path.getPathTransition().setOrientation(PathTransition.OrientationType.NONE);

            innerWidth = health;
            rectInt = new Rectangle();
            rectInt.setWidth(innerWidth);
            rectInt.setHeight(hauteur);
            rectInt.setFill(Color.LIMEGREEN);

    }

    public void getdammage( double value) {
        if (innerWidth<=0){
            rectInt.setFill(Color.TRANSPARENT);
        }
        else{
            this.innerWidth-=value;
            rectInt.setWidth(this.innerWidth);
        }
    }

    public double getHealth(){
        return innerWidth;
    }
}
