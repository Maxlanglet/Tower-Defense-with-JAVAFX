package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class PNJ{

    //pas besoin d'interface pour enemi car pas de méthodes additionnelles pour les sous enemis


    protected HealthBar health;
    protected path healthpath;
    protected boolean isAlive = true;

    protected path path;

    protected int dammage;
    protected double sante;

    protected Duration duration;

    protected int ressources;


    protected ImageView hitbox;
    protected double spawnx;
    protected double spawny;

    protected Waves wave;
    protected Ressources lables;

    protected StackPane stackPane;

    private Timeline timer;



    protected double x;
    protected double y;

    protected grid grid;

    public PNJ(StackPane stackPane, grid grid, Waves wave, Ressources labels){

        this.wave = wave;
        this.lables = labels;


        this.grid = grid;

        this.stackPane=stackPane;
    }

    public Node getPNJ(){
        return hitbox;
    }

    public boolean isDead(){
        if (health.getHealth()<=0 && isAlive){
            return true;
        }
        else{
            return false;
        }
    }

    public HealthBar getHealth() {
        return health;
    }

    public path getPath() {
        return path;
    }


    public void setPause(){
        path.getPathTransition().pause();
        healthpath.getPathTransition().pause();
    }

    public void setPlay(){
        path.getPathTransition().play();
        healthpath.getPathTransition().play();
    }

    public boolean isInvisible(){
        if (hitbox.getTranslateX()<-9*40 || !isAlive){
            return true;
        }
        else{
            return false;
        }
    }

    public void update(){
        timer = new Timeline(
                new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) { {

                        x = hitbox.getTranslateX()-38;
                        y = hitbox.getTranslateY();
                        //System.out.println(x);

                        if (isDead()){
                            hitbox.setOpacity(0);
                            removePNJ();
                            isAlive=false;
                            lables.setKills(ressources);

                        }
                        doDamage();
                    }}}));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void doDamage() {
        if (hitbox.getTranslateX()>9*40+20+38 && isAlive){
            lables.setCastleDammage(dammage);
            hitbox.setOpacity(0);
            removePNJ();
            isAlive=false;

            if (lables.getCastleHealth()<=0){
                wave.setGameOver();
                timer.stop();
            }
        }
    }

    public void removePNJ(){
        stackPane.getChildren().removeAll(hitbox, health.rectInt);
        wave.getPnjs().remove(this);
        this.grid.getPnjs().remove(this);
        ArrayList<Tower> towers = this.grid.getTowers();
        for (Tower tower : towers){
            tower.getPnjs().remove(this);
        }
    }

    public double getX(){ return x;}

    public double getY(){ return y;}

    public double getTranslateX(){
        return hitbox.getTranslateX();
    }

    public double getTranslateY(){
        return hitbox.getTranslateY();
    }

    public void setDead() {
        isAlive = false;
        stackPane.getChildren().removeAll(hitbox, health.rectInt);
    }
}
