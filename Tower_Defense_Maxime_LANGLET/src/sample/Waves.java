package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Waves {


    private ArrayList<PNJ> pnjs = new ArrayList<PNJ>();
    private int wave_num = 1;
    private int mult_num = 10;

    private StackPane stackPane;
    private grid grid;
    private Ressources labels;

    private boolean isGameOver= false;

    private Timeline timer;

    public Waves(StackPane stackPane, grid grid, Ressources labels){
        this.stackPane = stackPane;
        this.grid = grid;
        this.labels = labels;

        update();
    }

    private void update() {
        timer = new Timeline(
                new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) { {
                        if (isNewWave()){
                            spawn_wave();
                        }
                    }}}));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void spawn_wave(){
        labels.setWave();
        pnjs.clear();
        grid.clearArrays();
        for (int i =mult_num*wave_num; i >0; i--) {//mult_num*wave_num
            if (i % 2 == 0) {
                spawn_Light_Enemy();
            } else {
                spawn_Heavy_Enemy();
            }
        }
    }

    private void spawn_Light_Enemy(){
        PNJ pnj = new LightEnemy(stackPane, grid, this, labels);
        grid.addPNJ(pnj);
        pnjs.add(pnj);
    }

    private void spawn_Heavy_Enemy(){
        PNJ pnj = new HeavyEnemy(stackPane, grid, this, labels);
        grid.addPNJ(pnj);
        pnjs.add(pnj);
    }

    private boolean isNewWave() {
        int k=0;
        for (PNJ pnj : pnjs){
            if (!pnj.isDead()){
                k++;
            }
        }
        if (k==0 && !isGameOver){
            wave_num++;
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<PNJ> getPnjs() {
        return pnjs;
    }

    public void setGameOver() {
        timer.stop();
        if (!isGameOver && labels.getLevel()==1){
            GameOver gameOver = new GameOver( stackPane, this.labels);
            isGameOver=true;
            for (Tower tower : grid.getTowers()){
                tower.clearPNJ();
                tower.clearProjectile();
                tower.towerDead();
            }
            pnjs.clear();
            grid.clearArrays();
        }

        else if (!isGameOver && labels.getLevel()==0){//sinon continue de spawn
            newGame();
        }
    }

    public void newGame(){
        isGameOver=true;//pour temporairement arreter le spawn au cas ou

        labels.setLevel();

        for (PNJ pnj : pnjs){
            pnj.setDead();
        }

        pnjs.clear();
        for (Tower tower : grid.getTowers()){
            tower.clearPNJ();
            tower.clearProjectile();
            tower.towerDead();
            stackPane.getChildren().remove(tower);
        }

        grid.newLevel();

        wave_num=0;//redemarrage des vagues
        labels.setWave(wave_num);


        isGameOver=false;
        labels.restoreCastle();
        timer.play();
    }
}
