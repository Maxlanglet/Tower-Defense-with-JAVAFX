package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;


public abstract class magicTower extends Tower implements TowerInterface{//n'implemente pas l'interface car j'aimerais un comportement différent, surtout

    protected double base_damage;
    protected double dammage=base_damage;
    protected double rate_of_fire=100;//en milli secondes

    protected int rate=0;
    protected int rate_max=4;
    protected int max_pnj=5;

    protected double range;

    protected Color projectileColor;

    private ArrayList<PNJ> inRangePNJ;

    public magicTower(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool) {
        super(pnjs, positionX, positionY, gridPosX, gridPosY, root, bool);
        inRangePNJ = new ArrayList<PNJ>();

    }

    public void distanceEnemy(PNJ pnj) {
        if (!towerDead){
            distance=Math.sqrt((positionX-pnj.getX())*(positionX-pnj.getX())+(positionY-pnj.getY())*(positionY-pnj.getY()));
        }
    }

    public boolean isInRange(PNJ pnj){
        distanceEnemy(pnj);
        if (pnj.isDead() || pnj.isInvisible()){
            return false;
        }
        else if (distance<=range){
            return true;
        }
        return false;
    }

    public void fire(){
        if (pnjs.size()<max_pnj){//donne un maximum de pnj par tours "magique"
            for (PNJ pnj: pnjs){
                if (isInRange(pnj) && !isPause && rate==0){
                    pnj.getHealth().getdammage(dammage);
                    Projectile proj = new Bullet(positionX+36, positionY+3, pnj.getTranslateX(), pnj.getTranslateY(), root, this, projectileColor);
                    proj.setRadius(20);
                    projectiles.add(proj);
                }
            }
        }
        else{//on s'assure a toujours toucher un maximum d'ennemi
            inRangePNJ.clear();
            for (PNJ pnj : pnjs){
                if (isInRange(pnj)){
                    inRangePNJ.add(pnj);
                }
            }
            if (inRangePNJ.size()!=0 && inRangePNJ.size()>max_pnj){
                for (int i =0; i<max_pnj; i++){
                    if (isInRange(pnjs.get(i)) && !isPause && rate==0){
                        pnjs.get(i).getHealth().getdammage(dammage);
                        Projectile proj = new Bullet(positionX+36, positionY+3, pnjs.get(i).getTranslateX(), pnjs.get(i).getTranslateY(), root, this, projectileColor);
                        proj.setRadius(20);
                        projectiles.add(proj);
                    }
                }
            }
            else if (inRangePNJ.size()!=0 && inRangePNJ.size()<=max_pnj && !isPause && rate==0){
                for (PNJ pnj : inRangePNJ){
                    pnj.getHealth().getdammage(dammage);
                    Projectile proj = new Bullet(positionX+36, positionY+3, pnj.getTranslateX(), pnj.getTranslateY(), root, this, projectileColor);
                    proj.setRadius(20);
                    projectiles.add(proj);
                }
            }
        }
        rate++;//de sorte a avoir une animation de tour qui tourne fluide mais ne tire que les 400 milli-secondes
        if (rate==rate_max){
            rate=0;
        }
    }


    public void setRotation() {
        if (!isPause){
            if (upgraded==0){
                canon=canon;
            }
            else if (upgraded==1){
                canon=canonUp.get(0);
            }
            else{
                canon=canonUp.get(1);
            }
            rotation+=2;
            canon.setRotate(rotation);
        }
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    @Override
    public void towerDead(){
        super.towerDead();
    }


    public void update(){
        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) { {
                        setRotation();
                        fire();
                    }}}));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
}
