package sample;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class TowerFactory {

    public static Tower getInstance(menuBar menuBar, ArrayList<PNJ> pnjs, double CenterX, double CenterY, int gridPosX, int gridPosY, StackPane root, boolean isPause){
        if (menuBar.getTowerType()==1){
            if (menuBar.getTowerSelected()==1){
                return new baseTower1((ArrayList<PNJ>) pnjs.clone(), CenterX, CenterY, gridPosX, gridPosY, root, isPause);
            }
            else if (menuBar.getTowerSelected()==2){
                return new baseTower2((ArrayList<PNJ>) pnjs.clone(), CenterX, CenterY, gridPosX, gridPosY, root, isPause);
            }
        }
        else if (menuBar.getTowerType()==2){
            //return new baseTower((ArrayList<PNJ>) pnjs.clone(), CenterX, CenterY, gridPosX, gridPosY, root, isPause);
            if (menuBar.getTowerSelected()==1){
                return new missileTower1((ArrayList<PNJ>) pnjs.clone(), CenterX, CenterY, gridPosX, gridPosY, root, isPause);
            }
            if (menuBar.getTowerSelected()==2){
                return new missileTower2((ArrayList<PNJ>) pnjs.clone(), CenterX, CenterY, gridPosX, gridPosY, root, isPause);
            }
        }

        else if (menuBar.getTowerType()==3){
            if (menuBar.getTowerSelected()==1){
                return new magicTower1((ArrayList<PNJ>) pnjs.clone(), CenterX, CenterY, gridPosX, gridPosY, root, isPause);
            }
            if (menuBar.getTowerSelected()==2){
                return new magicTower2((ArrayList<PNJ>) pnjs.clone(), CenterX, CenterY, gridPosX, gridPosY, root, isPause);
            }
        }
        return new baseTower1((ArrayList<PNJ>) pnjs.clone(), CenterX, CenterY, gridPosX, gridPosY, root, isPause);
    }
}
