package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class magicTower1 extends magicTower implements EndBranchTowers{

    public magicTower1(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool) {
        super(pnjs, positionX, positionY, gridPosX, gridPosY, root, bool);

        this.base = new ImageView("/Images/magic_base1.png");
        this.canon = new ImageView("/Images/magic_canon1_1.png");

        canonUp.add(new ImageView("/Images/magic_canon1_2.png"));
        canonUp.add(new ImageView("/Images/magic_canon1_3.png"));

        base_damage = 0.2;
        dammage = base_damage;

        range = 250;

        projectileColor = Color.LIGHTBLUE;



        update();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        dammage*=1.8;
        range*=1.2;
        if (upgraded==1){
            projectileColor=Color.DEEPSKYBLUE;
            max_pnj+=2;
        }
        else if (upgraded==2){
            projectileColor=Color.RED;
            rate_max=3;//augmente la fréquence de tire pour le dernier niveau
            max_pnj+=3;//augmente de 5 le nombre max de pnj qui se font tirer dessus
        }
    }

    @Override
    public int costUpgrade() {
        if (upgraded==0){
            return 100;
        }
        else if (upgraded==1){
            return 200;
        }
        else if (upgraded==2){
            return 0;
        }
        return 0;
    }
}

