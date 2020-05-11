package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class magicTower2 extends magicTower implements EndBranchTowers{

    public magicTower2(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool) {
        super(pnjs, positionX, positionY, gridPosX, gridPosY, root, bool);

        this.base = new ImageView("/Images/magictower2.png");
        this.canon = new ImageView("/Images/magictower2_1.png");

        canonUp.add(new ImageView("/Images/magictower2_2.png"));
        canonUp.add(new ImageView("/Images/magictower2_3.png"));

        base_damage = 0.3;
        dammage = base_damage;

        range = 300;

        projectileColor = Color.AQUA;



        update();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        dammage*=2;
        range*=1.2;
        rate=0;
        if (upgraded==1){
            projectileColor=Color.BLUEVIOLET;
            max_pnj+=2;
        }
        else if (upgraded==2){
            projectileColor=Color.BLUE;
            rate_max=2;//augmente la fréquence de tire pour le dernier niveau
            max_pnj+=4;//augmente de 5 le nombre max de pnj qui se font tirer dessus
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
