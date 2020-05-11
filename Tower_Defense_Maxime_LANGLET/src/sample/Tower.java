package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;



public abstract class Tower {

    protected double distance;//TODO: ajouter ce qu'il faut en final, pos et gridpos

    protected double dammage;

    protected double positionX;
    protected double positionY;
    protected int gridPosX;
    protected int gridPosY;

    protected ImageView base;
    protected ImageView canon;
    protected ArrayList<ImageView> canonUp;
    protected int upgraded=0;

    protected ArrayList<PNJ> pnjs;

    protected double rotation=0;
    protected boolean towerDead = false;

    protected boolean isPause=false;

    protected StackPane root;

    protected ArrayList<Projectile> projectiles;

    protected PNJ closestPnj=null;

    protected ArrayList<PNJ> pnjsInRadius;//placé pour tour a missile

    public Tower(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool){

        this.root = root;

        this.positionX = (positionX-942)/2;
        this.positionY = (positionY-768.5)/2;//deplacement car la stackpane et la gridpane n'ont pas la meme origine

        this.gridPosX = gridPosX;
        this.gridPosY = gridPosY;

        this.pnjs = pnjs;

        this.isPause=bool;

        this.projectiles = new ArrayList<Projectile>();

        this.canonUp = new ArrayList<ImageView>();

    }

    public int getGridPosX() {
        return gridPosX;
    }

    public int getGridPosY() {
        return gridPosY;
    }

    public ArrayList<PNJ> getPnjs() {
        return pnjs;
    }

    public void towerDead() {
        this.towerDead = true;
        this.pnjs.clear();
    }

    public ImageView getBase() {
        return base;
    }

    public ImageView getCanon() {
        return canon;
    }

    public void setPause(boolean value) {
        isPause=value;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void clearProjectile(){
        projectiles.clear();
    }

    public void clearPNJ(){
        if (pnjs.size()!=0){
            pnjs.clear();
        }
    }

    public ArrayList<ImageView> getCanonUp() {
        return canonUp;
    }

    public void upgrade(){
        upgraded++;
    }

    public int costUpgrade(){return 0;}

    public double getDammage() {
        return dammage;
    }
}
