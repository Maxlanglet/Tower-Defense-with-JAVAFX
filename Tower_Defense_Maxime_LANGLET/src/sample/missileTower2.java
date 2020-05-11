package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class missileTower2 extends missileTower implements EndBranchTowers {


    public missileTower2(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool) {
        super(pnjs, positionX, positionY, gridPosX, gridPosY, root, bool);

        this.base = new ImageView("/Images/missiletower2_base.png");//TODO: FAIRE tour et missiles
        this.canon = new ImageView("/Images/missiletower2_1.png");

        canonUp.add(new ImageView("/Images/missiletower2_2.png"));
        canonUp.add(new ImageView("/Images/missiletower2_3.png"));



        base_damage=2;
        dammage=base_damage;
        rate_of_fire=30;

        range=500;

        blastRadius=100;

        findClosestEnemy();
        setRotation();


        update();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        dammage*=3/2;
        range*=3/2;
        blastRadius+=20;
        rate_of_fire-=5;
        indicator=0;
    }

    @Override
    public int costUpgrade() {
        if (upgraded==0){
            return 60;
        }
        else if (upgraded==1){
            return 120;
        }
        else if (upgraded==2){
            return 0;
        }
        return 0;
    }
}