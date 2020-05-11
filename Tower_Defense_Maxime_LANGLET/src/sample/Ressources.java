package sample;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Ressources {
    private VBox vbox;
    private int kills=0;
    private int resources=300;
    private int score=0;
    private int wave=0;

    private int level=0;

    private int CastleHealth = 1000;

    private String pseudo;


    private Label killsLable;
    private Label resourcesLable;
    private Label scoreLable;
    private Label castleLabel;
    private Label waveLabel;
    private Label livesLabel;



    public Ressources(){
        killsLable = new Label();
        resourcesLable = new Label();
        scoreLable = new Label();
        castleLabel = new Label();
        waveLabel = new Label();
        livesLabel = new Label();
        vbox = new VBox();


        killsLable.setText("Kills :"+ this.kills);
        killsLable.setTextFill(Color.LIMEGREEN);
        resourcesLable.setText("Coins :"+ this.resources);
        resourcesLable.setTextFill(Color.LIMEGREEN);
        scoreLable.setText("Score :"+ this.score);
        scoreLable.setTextFill(Color.LIMEGREEN);
        waveLabel.setText("Wave :"+ this.wave);
        waveLabel.setTextFill(Color.LIMEGREEN);
        castleLabel.setText("Ship Health :"+ this.CastleHealth);
        castleLabel.setTextFill(Color.LIMEGREEN);
        livesLabel.setText("Lives :"+ (1-this.level));
        livesLabel.setTextFill(Color.LIMEGREEN);

        vbox.getChildren().addAll(killsLable, waveLabel, resourcesLable, scoreLable,livesLabel, castleLabel);
        vbox.setBackground(Background.EMPTY);
        vbox.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 1);\n"+
                "-fx-border-color: grey;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n"
                );
        vbox.setMaxWidth(110);
        vbox.setMinWidth(110);
        vbox.setMaxHeight(110);
        vbox.setMaxHeight(110);
    }

    public void update() {
        killsLable.setText("Kills :" + this.kills);
        resourcesLable.setText("Coins :"+ this.resources);
        scoreLable.setText("Score :"+ this.score);
        castleLabel.setText("Ship Health :"+ this.CastleHealth);
        waveLabel.setText("Wave :"+ this.wave);
        livesLabel.setText("Lives :"+ (1-this.level));
    }

    public int getScore() {
        return score;
    }

    public int getResources() {
        return resources;
    }

    public Node getVBox() {
        return vbox;
    }

    public void setKills(int ressources) {
        kills++;
        this.resources+=ressources;
        score+=3;
        update();
    }

    public void setWave() {
        wave++;
        resources+=20;
        update();
    }

    public void setWave(int wave_num) {
        wave=wave_num;
        resources+=20;
        update();
    }

    public void setResources(int value) {
        resources+=value;
        update();
    }

    public void setCastleDammage(int castleHealth) {
        CastleHealth -= castleHealth;
        update();
    }

    public int getCastleHealth() {
        return CastleHealth;
    }

    public void restoreCastle(){
        CastleHealth=1000;
        update();
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        this.level++;
    }
}
