package sample;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Random;

public class LightEnemy extends PNJ {

    public LightEnemy(StackPane stackPane, grid grid, Waves wave, Ressources labels){
        super( stackPane, grid, wave, labels);

        hitbox = new ImageView("/Images/enemi3.png");

        ressources=3;

        sante=15;
        dammage=80;

        Random r = new Random();
        int random = r.nextInt(10)-5;

        duration = Duration.seconds(30+random);

        spawnx = hitbox.getTranslateX()-38;
        spawny = hitbox.getTranslateY();
        x = spawnx;
        y = spawny;

        path = new path(duration, labels);

        healthpath = new path(path.getRepositionX(), path.getRepositionY(), path.getPath12(), duration, labels);//egalisation des 2 chemins pour que la barre de vie suive le meme chemin que le PNJ
        health = new HealthBar(healthpath, sante);


        healthpath.setNode(health.rectInt);
        path.setNode(hitbox);


        this.stackPane.getChildren().addAll(hitbox);
        this.stackPane.getChildren().addAll(health.rectInt);
        this.stackPane.setAlignment(Pos.CENTER);

        update();
    }
}
