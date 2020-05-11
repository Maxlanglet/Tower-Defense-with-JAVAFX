package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class baseTower extends Tower implements TowerInterfaceWithClosest{

    protected double base_damage;
    protected double dammage=base_damage;
    protected double rate_of_fire=50;//pareil dans le timer, bizarement ne peut pas mettre un int dans le timer

    protected double range;

    protected Color projectileColor;

    private PNJ closestPnj=null;


    public baseTower(ArrayList<PNJ> pnjs, double positionX, double positionY, int gridPosX, int gridPosY, StackPane root, boolean bool) {
        super(pnjs, positionX, positionY, gridPosX, gridPosY, root, bool);
    }

    public void findClosestEnemy(){
        if (!towerDead){
            double distanceClosest=10000;

            double temp;

            for (PNJ pnj : pnjs){
                temp=Math.sqrt((positionX-pnj.getX())*(positionX-pnj.getX())+(positionY-pnj.getY())*(positionY-pnj.getY()));
                if (temp<=distanceClosest && temp<=range){
                    distanceClosest=temp;
                    closestPnj=pnj;
                }
            }
        }
        else {
            closestPnj=null;
        }
    }

    public void setRotation(){
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
            if (distance != 0 && distance<=range) {
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

    public ImageView getImage(){ return canon;}//peut etre ajouter la base

    public void setCanon(ImageView canon) {
        this.canon = canon;
    }

    public void setPnjs(ArrayList<PNJ> pnjs){
        this.pnjs = pnjs;
    }

    public boolean isInRange(){
        if (closestPnj!=null){
            if (closestPnj.isDead() || closestPnj.isInvisible()){
                return false;
            }
            else if (distance<=range){
                return true;
            }
        }
        return false;
    }
    public void fire(){
        if (isInRange() && !isPause){
            closestPnj.getHealth().getdammage(dammage);
            projectiles.add(new Bullet(positionX+36, positionY+3, closestPnj.getTranslateX(), closestPnj.getTranslateY(), root, this, projectileColor));
        }
    }


    public void update(){
        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {//en mettant la variable rateoffire ici on a un bug, très lent
                    @Override
                    public void handle(ActionEvent event) { {
                        setRotation();
                        fire();
                    }}}));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    @Override
    public void towerDead(){
        super.towerDead();
        closestPnj=null;
    }
}
