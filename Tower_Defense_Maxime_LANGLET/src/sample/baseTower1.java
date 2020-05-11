package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class baseTower1 extends baseTower implements EndBranchTowers {

    public baseTower1(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool) {
        super(pnjs, positionX, positionY, gridPosX, gridPosY, root, bool);

        this.base = new ImageView("/Images/base1.png");
        this.canon = new ImageView("/Images/canon1.png");

        canonUp.add(new ImageView("/Images/canon2.png"));
        canonUp.add(new ImageView("/Images/canon2-2.png"));

        base_damage=0.05;
        dammage=base_damage;
        rate_of_fire=2000;//en milli secondes

        range=200;

        projectileColor = Color.YELLOWGREEN;

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
            return 40;
        }
        else if (upgraded==1){
            return 80;
        }
        else if (upgraded==2){
            return 0;
        }
        return 0;
    }

}
