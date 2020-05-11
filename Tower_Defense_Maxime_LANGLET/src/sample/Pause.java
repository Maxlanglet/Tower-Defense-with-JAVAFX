package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class Pause {

    private grid grid;
    private Button pause;
    private boolean pauseon=false;

    public Pause(grid grid){
        this.grid=grid;

        this.pause = new Button();
        this.pause.setGraphic(new ImageView("/Images/play.png"));
        setPause(this.pause);
    }

    private void setPause(Button button) {
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                makePause(button);
            }
        });
    }

    public void makePause(Button button){
        if (!pauseon){
            for (PNJ pnj : grid.getPnjs()){
                pnj.setPause();
            }
            for (Tower tower : grid.getTowers()){
                tower.setPause(true);
                if (tower.getProjectiles().size()!=0){
                    for (Projectile proj : tower.getProjectiles()){
                        proj.setPause();
                    }
                }
            }
            grid.setPause(true);
            button.setGraphic(new ImageView("/Images/pause.png"));
            pauseon=true;
        }
        else{
            for (PNJ pnj : grid.getPnjs()){
                pnj.setPlay();
            }
            for (Tower tower : grid.getTowers()){
                tower.setPause(false);
                if (tower.getProjectiles().size()!=0){
                    for (Projectile proj : tower.getProjectiles()){
                        proj.setPlay();
                    }
                }
            }
            grid.setPause(false);
            button.setGraphic(new ImageView("/Images/play.png"));
            pauseon=false;
        }
    }

    public Button getPause() {
        return pause;
    }
}
