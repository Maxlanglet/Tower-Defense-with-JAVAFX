package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class menuBar {
    private int towerSelected = 1;
    private int towerType = 1;
    private int towercost=10;

    private MenuBar menuBar;

    public menuBar(){
        //------------ Menu Bar
        menuBar = new MenuBar();


        Menu basicTower = new Menu("Basic Tower");

        MenuItem tower1 = new MenuItem("Tower 1 (10 coins)");
        tower1.setGraphic(new ImageView("/Images/tour1.png"));
        tower1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                towerType=1;
                towerSelected = 1;
                towercost = 10;
            }
        });
        MenuItem tower2 = new MenuItem("Tower 2 (25 coins)");
        tower2.setGraphic(new ImageView("/Images/tower2.png"));
        tower2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                towerType=1;
                towerSelected = 2;
                towercost = 25;
            }
        });

        basicTower.getItems().addAll(tower1, tower2);


        Menu missileTower = new Menu("Missile Tower");

        MenuItem missile_tower1 = new MenuItem("Missile Tower 1 (25 coins)");
        missile_tower1.setGraphic(new ImageView("/Images/missile_tower1.png"));
        missile_tower1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                towerType=2;
                towerSelected = 1;
                towercost = 25;
            }
        });

        MenuItem missile_tower2 = new MenuItem("Missile Tower 2 (30 coins)");
        missile_tower2.setGraphic(new ImageView("/Images/missiletower2.png"));
        missile_tower2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                towerType=2;
                towerSelected = 2;
                towercost = 30;
            }
        });

        missileTower.getItems().addAll(missile_tower1, missile_tower2);


        Menu magicTower = new Menu("Magic Tower");

        MenuItem magic_tower1 = new MenuItem("Magic Tower 1 (30 coins)");
        magic_tower1.setGraphic(new ImageView("/Images/magic_tower1.png"));
        magic_tower1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                towerType=3;
                towerSelected = 1;
                towercost = 30;
            }
        });

        MenuItem magic_tower2 = new MenuItem("Magic Tower 2 (40 coins)");
        magic_tower2.setGraphic(new ImageView("/Images/magictower2.png"));
        magic_tower2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                towerType=3;
                towerSelected = 2;
                towercost = 40;
            }
        });

        magicTower.getItems().addAll(magic_tower1, magic_tower2);


        menuBar.getMenus().addAll(basicTower, missileTower, magicTower);
    }
    public MenuBar getmenuBar(){
        return this.menuBar;
    }


    public int getTowerSelected() {
        return towerSelected;
    }

    public int getTowerType() {
        return towerType;
    }

    public int getTowercost() {
        return towercost;
    }
}