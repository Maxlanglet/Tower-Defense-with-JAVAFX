package sample;

import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class path {

    //-------------- Definition du chemin pour la gridpane
    private List<Integer> X = Arrays.asList(0,1,2,3,3,3,3,3,3,3,3,3,4,5,6,6,6,6,6,6,6,7,8,9,9,9,9,9,9,9,10,11,
            12,12,12,12,12,12,12,13,14,15,15,15,15,15,15,15,16,17,17,17,17,17,17,17,17,17,18);

    private List<Integer> Y = Arrays.asList( 9,9,9,9,8,7,6,5,4,3,2,1,1,1,1,2,3,4,5,6,7,7,7,7,6,5,4,3,2,1,1,1,1,2,3,4,5,6,
            7,7,7,7,6,5,4,3,2,1,1,1,2,3,4,5,6,7,8,9,9);
    private List<Integer> X2 = X;

    private List<Integer> Y2 = Arrays.asList(9,9,9,9,10,11,12,13,14,15,16,17,17,17,17,16,15,14,13,12,11,11,11,11,12,13,14,15,16,
            17,17,17,17,16,15,14,13,12,11,11,11,11,12,13,14,15,16,17,17,17,16,15,14,13,12,11,10,9,9);

    private PathTransition pathTransition = new PathTransition();


    private Path PNJpath;

    private Random r = new Random();
    private int random = r.nextInt(40)-20;

    private int path12 = r.nextInt(2)+1;

    private int repositionX = (9*40-10-38+random);//-38 a cause du shift de Borderpane
    private int repositionY = (9*40-10+random);

    private Duration duration;


    public path(Duration duration, Ressources ressources){

        if (ressources.getLevel()==1){
            newLevel();
        }

        //-------------- Definition du chemin pour l'animation
        PNJpath = new Path();

        this.duration = duration;

        PNJpath.getElements().add(new MoveTo(-10.5*40, 0));//-9*40 pour remettre sur la grid

        if (path12==1){
            for (int i=0; i<Y.size(); i++){
                PNJpath.getElements().add (new LineTo(X.get(i)*40-repositionX, Y.get(i)*40-repositionY));
            }
        }

        else {
            for (int i=0; i<Y.size(); i++){
                PNJpath.getElements().add (new LineTo(X2.get(i)*40-repositionX, Y2.get(i)*40-repositionY));
            }
        }

        PNJpath.getElements().add(new LineTo(X.get(X.size()-1)*40+13.5*40, Y.get(X.size()-1)*40-repositionY));


        pathTransition.setDuration(this.duration);//a changer en fonction de la vague, Duration.seconds(5)
        pathTransition.setPath(PNJpath);
        pathTransition.setCycleCount(1);//Animation.INDEFINITE
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

    }

    public path(double repositionX, double repositionY, int path12, Duration duration, Ressources ressources){ //Constructor overloading pcq pour healthbar

        if (ressources.getLevel()==1){
            newLevel();
        }

        //-------------- Definition du chemin pour l'animation
        PNJpath = new Path();

        this.duration = duration;

        PNJpath.getElements().add(new MoveTo(-10.5*40, 0));//-9*40 pour remettre sur la grid

        if (path12==1){
            for (int i=0; i<Y.size(); i++){
                PNJpath.getElements().add (new LineTo(X.get(i)*40-repositionX, Y.get(i)*40-repositionY));
            }
        }

        else {
            for (int i=0; i<Y.size(); i++){
                PNJpath.getElements().add (new LineTo(X2.get(i)*40-repositionX, Y2.get(i)*40-repositionY));
            }
        }

        PNJpath.getElements().add(new LineTo(X.get(X.size()-1)*40+13.5*40, Y.get(X.size()-1)*40-repositionY));

        pathTransition.setDuration(duration);
        pathTransition.setPath(PNJpath);
        pathTransition.setCycleCount(1);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

    }

    public List<Integer> getXcomplete() {
        return X;
    }

    public List<Integer> getYcomplete() {
        return Y;
    }

    public List<Integer> getX2complete() {
        return X2;
    }

    public List<Integer> getY2complete() {
        return Y2;
    }

    public int getRepositionX() { return repositionX; }

    public int getRepositionY() { return repositionY; }

    public int getPath12() {
        return path12;
    }

    public PathTransition getPathTransition(){return this.pathTransition;}


    public void setNode(Node node) {
        this.pathTransition.setNode(node);
        this.pathTransition.play();
    }

    public int size(){
        return X.size();
    }

    public void newLevel() {
        //TODO: ajouter path mieux
        X = Arrays.asList( 0, 1, 2, 3, 3, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7,
                8, 9,10,10,10,10,10,10,10,10,10,10,10,11,12, 13,
                13,13,13,13,13,13,13,13,13,13,13,
                14,15,16,
                16,16,16,16,16,16,16,16,
                17,18);

        Y  = Arrays.asList( 9, 9, 9, 9,10,11,11,11,11,11,12,13,14,15,16,
                16,16,16,15,14,13,12,11,10, 9, 8, 7, 6, 6, 6, 6,
                7,8,9,10,11,12,13,14,15,16,17,
                17,17,17,
                16,15,14,13,12,11,10,9,
                9,9);

        X2 = Arrays.asList( 0, 1, 2, 3, 3, 3, 4, 5, 6, 7, 7, 7, 7,6,5,4,3,2,2,2,3,4,5,6,7,8,9,10,11,
                11,11,12,13,14,15,16,17,17,17,16,15,14,13,13,13,14,15,16,16,16,16,16,16,17,18);

        Y2 = Arrays.asList( 9, 9, 9, 9,8,7,7,7,7,7,6,5,4,4,4,4,4,4,3,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,
                1,2,2,2,2,2,3,4,4,4,4,5,6,7,8,9,9,9);
    }
}
