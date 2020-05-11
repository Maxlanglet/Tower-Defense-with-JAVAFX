package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class missileTower extends Tower implements TowerInterfaceWithClosest {

    protected double base_damage;
    protected double dammage=base_damage;
    protected int rate_of_fire;//en milli secondes
    protected int indicator=0;

    protected double range;

    protected double blastRadius;

    private PNJ closestPnj=null;

    protected ArrayList<PNJ> pnjsInRadius;

    protected ArrayList<ImageView> missiles;

    public missileTower(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool){
        super(pnjs, positionX, positionY,  gridPosX,  gridPosY,  root,  bool);

        pnjsInRadius = new ArrayList<PNJ>();

        missiles = new ArrayList<ImageView>();

        missiles.add(new ImageView("/Images/missile1.png"));//bizarrement ne fonctionnne pas avec ceux-ci
        missiles.add(new ImageView("/Images/missile2.png"));
        missiles.add(new ImageView("/Images/missile3.png"));
    }


    @Override
    public boolean isInRange() {
        if (closestPnj!=null){
            if (!closestPnj.isInvisible() && !closestPnj.isDead()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void findClosestEnemy() {
        if (!towerDead){
            double distanceClosest=10000;

            double temp;

            for (PNJ pnj : pnjs){
                temp=Math.sqrt((positionX-pnj.getX())*(positionX-pnj.getX())+(positionY-pnj.getY())*(positionY-pnj.getY()));
                if (temp<=distanceClosest){
                    distanceClosest=temp;
                    closestPnj=pnj;
                }
            }
        }
        else {
            closestPnj=null;
        }
    }

    @Override
    public void setRotation() {
        findClosestEnemy();
        if (upgraded==0){
            canon=canon;
        }
        else if (upgraded==1){
            canon=canonUp.get(0);
        }
        else{
            canon=canonUp.get(1);
        }
        if (closestPnj==null || closestPnj.isDead() || closestPnj.isInvisible()){
            canon.setRotate(rotation);
        }
        else {
            double relativeX = closestPnj.getX()-positionX;
            double relativeY = closestPnj.getY()-positionY;

            distance = Math.sqrt(relativeX*relativeX + relativeY*relativeY);
            double angle;
            if (distance != 0) {
                angle = Math.toDegrees(Math.acos(relativeX/distance));
                if (relativeY<0){
                    angle = -angle;
                }
            } else {
                angle=rotation;
            }
            rotation=angle;
            canon.setRotate(rotation);
        }
    }

    @Override
    public void towerDead(){
        super.towerDead();
        closestPnj=null;
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    @Override
    public void update() {
        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) { {
                        setRotation();
                        fire();
                    }}}));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void fire(){
        if (isInRange() && !isPause && indicator==0 && !towerDead){

            inBlastRadius();
            Missile missile1 = new Missile(positionX+36, positionY+3, closestPnj.getTranslateX(), closestPnj.getTranslateY(), root, this, missiles.get(upgraded));
            projectiles.add(missile1);

            for (PNJ pnj : pnjsInRadius){
                pnj.getHealth().getdammage(dammage);
            }
            clearBlastRadius();
        }
        indicator++;
        if (indicator>=rate_of_fire){
            indicator=0;
        }
    }

    public void inBlastRadius(){
        if (closestPnj!=null){
            pnjsInRadius.add(closestPnj);
            for (PNJ pnj : pnjs){
                if (Math.abs(pnj.getTranslateX()-closestPnj.getTranslateX())<blastRadius &&
                        Math.abs(pnj.getTranslateY()-closestPnj.getTranslateY())<blastRadius){
                    pnjsInRadius.add(pnj);
                }
            }
        }
    }

    private void clearBlastRadius(){
        pnjsInRadius.clear();
    }

    @Override
    public double getDammage() {
        return dammage;
    }
}
