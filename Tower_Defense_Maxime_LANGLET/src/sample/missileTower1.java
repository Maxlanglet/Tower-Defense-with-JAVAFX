package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class missileTower1 extends missileTower implements EndBranchTowers {


    public missileTower1(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool) {
        super(pnjs, positionX, positionY, gridPosX, gridPosY, root, bool);

        this.base = new ImageView("/Images/missile_tower1_base.png");//TODO: FAIRE tour et missiles
        this.canon = new ImageView("/Images/missile_tower1_1.png");

        canonUp.add(new ImageView("/Images/missile_tower1_2.png"));
        canonUp.add(new ImageView("/Images/missile_tower1_3.png"));



        base_damage=1.5;
        dammage=base_damage;
        rate_of_fire=30;

        range=400;

        blastRadius=80;

        findClosestEnemy();
        setRotation();


        update();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        dammage*=3/2;
        range*=3/2;
        blastRadius+=10;
        rate_of_fire-=5;
        indicator=0;
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
