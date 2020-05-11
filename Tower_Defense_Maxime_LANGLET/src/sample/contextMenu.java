package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class contextMenu {

    private ContextMenu contextMenu;
    private Ressources ressources;
    private ImageView canonUp;
    private ImageView canon;
    private ImageView base;
    private grid grid;
    private Tower tower=null;
    private int upgraded=0;
    private String upgradeString;

    public contextMenu(grid grid, Tower tower, Ressources ressources, StackPane pane){

        contextMenu = new ContextMenu();
        this.ressources = ressources;
        this.grid = grid;

        this.canon = tower.getCanon();
        this.base = tower.getBase();
        this.tower=tower;

        this.upgradeString = "Upgrade "+"( -"+Integer.toString(tower.costUpgrade())+" coins)";

        MenuItem upgrade = new MenuItem(upgradeString);
        upgrade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tower!=null){
                    if (ressources.getResources()-tower.costUpgrade()>=0){

                        if (upgraded==0){
                            canonUp = tower.getCanonUp().get(0);
                            pane.getChildren().remove(canon);
                            pane.getChildren().add(canonUp);
                            ressources.setResources(-tower.costUpgrade());
                            tower.upgrade();
                            upgraded++;
                            upgradeString = "Upgrade "+"( -"+Integer.toString(tower.costUpgrade())+" coins)";
                            upgrade.setText(upgradeString);
                        }
                        else{
                            pane.getChildren().remove(canonUp);
                            canonUp = tower.getCanonUp().get(1);
                            pane.getChildren().add(canonUp);
                            ressources.setResources(-tower.costUpgrade());
                            tower.upgrade();
                            upgraded++;
                            if (tower.costUpgrade()==0){
                                upgradeString = "No upgrades";
                            }
                            else{
                                upgradeString = "Upgrade "+"( -"+Integer.toString(tower.costUpgrade())+" coins)";
                            }
                            upgrade.setText(upgradeString);
                        }
                    }
                }
            }
        });
        MenuItem remove = new MenuItem("Remove (+10 coins)");
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tower!=null){
                    ressources.setResources(10);
                    for (Projectile projectile : tower.getProjectiles()){
                        projectile.setOpacity(0);
                    }
                    tower.towerDead();
                    pane.getChildren().clear();
                    grid.removeTower(tower);
                }
            }
        });

        MenuItem restoreCastle = new MenuItem("Restore Castle (-300 coins)");
        restoreCastle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (ressources.getResources()-300>=0){
                    ressources.restoreCastle();
                    ressources.setResources(-300);
                }
            }
        });

        contextMenu.getItems().addAll(upgrade, remove, restoreCastle);

    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
