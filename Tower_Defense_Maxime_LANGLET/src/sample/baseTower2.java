package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class baseTower2 extends baseTower implements EndBranchTowers{

    public baseTower2(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool) {
        super(pnjs, positionX, positionY, gridPosX, gridPosY, root, bool);

        this.base = new ImageView("/Images/base2.png");
        this.canon = new ImageView("/Images/canon2_1.png");

        canonUp.add(new ImageView("/Images/canon2_2.png"));
        canonUp.add(new ImageView("/Images/canon2_3.png"));

        base_damage=0.1;
        dammage=base_damage;

        range=400;

        projectileColor = Color.MEDIUMVIOLETRED;

        findClosestEnemy();
        setRotation();

        update();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        dammage*=2;
        range*=3/2;
        if (upgraded==1){
            projectileColor=Color.GOLD;
        }
        else if (upgraded==2){
            projectileColor=Color.YELLOW;
        }
    }

    @Override
    public int costUpgrade() {
        if (upgraded==0){
            return 50;
        }
        else if (upgraded==1){
            return 100;
        }
        else if (upgraded==2){
            return 0;
        }
        return 0;
    }
}
