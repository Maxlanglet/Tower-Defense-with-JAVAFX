package sample;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class grid {

    private int columns = 19;
    private int rows = 19;
    private GridPane grid;

    private path path;

    private List<Integer> X;//les 2 chemins alternatifs pour les PNJs
    private List<Integer> Y;
    private List<Integer> X2;
    private List<Integer> Y2;

    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<PNJ> pnjs = new ArrayList<PNJ>();

    private ArrayList<StackPane> stackPanes = new ArrayList<StackPane>();
    private ArrayList<Pane> panes = new ArrayList<Pane>();

    private menuBar menuBar;
    private Ressources ressources;

    private boolean isPause=false;

    private StackPane root;



    public grid(menuBar menuBar, Ressources ressources, StackPane root){

        this.root = root;
        this.menuBar = menuBar;
        this.path = new path(Duration.seconds(1), ressources);//Duration n'influence rien en particulier, juste pour tracer le chemin
        this.ressources = ressources;


        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);


        setConstraints();

        for (int i = 0 ; i < columns ; i++) {
            for (int j = 0; j < rows; j++) {
                addPane(grid, i, j, menuBar);
            }
        }
        setPath();
    }

    private void setConstraints(){
        for (int i = 0; i < columns; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setMinWidth(40);
            colConst.setMaxWidth(40);
            grid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < rows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMinHeight(40);
            rowConst.setMaxHeight(40);
            grid.getRowConstraints().add(rowConst);
        }
    }

    private void initializePath(){
        this.X = path.getXcomplete();
        this.X2 = path.getX2complete();
        this.Y = path.getYcomplete();
        this.Y2 = path.getY2complete();
    }


    private void setPath(){
        initializePath();
        for (int i =0; i<path.size();i++){
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: rgba(20, 20, 20, 0.2);");
            grid.add(pane, X.get(i),Y.get(i));
            panes.add(pane);
        }
        for (int i =0; i<path.size();i++){
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: rgba(20, 20, 20, 0.2);");
            grid.add(pane, X2.get(i),Y2.get(i));
            panes.add(pane);
        }
    }

    public void newLevel(){
        clearArrays();

        for (StackPane pane : stackPanes){
            grid.getChildren().remove(pane);
        }
        for (Pane pane : panes){
            grid.getChildren().remove(pane);
        }

        for (int i = 0 ; i < columns ; i++) {
            for (int j = 0; j < rows; j++) {
                addPane(grid, i, j, menuBar);
            }
        }
        grid.setGridLinesVisible(true);

        path.newLevel();
        this.initializePath();
        setPath();
    }



    public void addPane(GridPane grid, int i, int j, menuBar menuBar) {
        StackPane pane = new StackPane();
        pane.setOnMouseEntered(e -> {
            pane.setStyle("-fx-background-color: rgba(232, 236, 241, 0.5);");
        });
        pane.setOnMouseExited(e -> {
            pane.setStyle("-fx-background-color: transparent;");
        });
        pane.setOnMouseClicked(e ->{//TODO: mettre ca en mouse released absolument et donc ajouter drag and drop
            if (e.getButton() == MouseButton.PRIMARY){
                if (ressources.getResources()-menuBar.getTowercost()>=0){
                    addTower(pane, i, j);
                }
            }
        });
        grid.add(pane, i, j);
        stackPanes.add(pane);
    }

    private void makeContextMenu(StackPane pane, Tower tower){
        contextMenu contextMenu = new contextMenu(this, tower, ressources, pane);
        pane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent contextMenuEvent) {
                contextMenu.getContextMenu().show(pane, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
            }
        });
    }

    private void addTower(StackPane pane, int x, int y){

        Bounds boundsInScene = pane.localToScene(pane.getBoundsInParent());//Pour la position de la tour sur la grid pane

        int k=0;
        if (towers.size()!=0){
            for (Tower tow : towers){
                if(tow.getGridPosX() == x && tow.getGridPosY() == y){
                    k++;
                }
            }
            if (k==0){
                Tower tower = TowerFactory.getInstance(menuBar, pnjs, boundsInScene.getCenterX(), boundsInScene.getCenterY(), x, y, root, isPause);
                makeContextMenu(pane, tower);
                pane.getChildren().addAll(tower.getBase(), tower.getCanon());
                addTower(tower);

                ressources.setResources(-menuBar.getTowercost());
                ressources.update();
            }
        }
        else {
            Tower tower = TowerFactory.getInstance(menuBar, pnjs, boundsInScene.getCenterX(), boundsInScene.getCenterY(), x, y, root, isPause);
            makeContextMenu(pane, tower);
            pane.getChildren().addAll(tower.getBase(), tower.getCanon());
            addTower(tower);

            ressources.setResources(-menuBar.getTowercost());
            ressources.update();
        }

    }

    public void addPNJ(PNJ pnj){
        this.pnjs.add(pnj);
        this.path=pnj.getPath();
        for (Tower tower : towers){
            tower.getPnjs().add(pnj);
        }
    }

    public void clearArrays(){
        this.pnjs.clear();
        for (Tower tower : towers){
            tower.getPnjs().clear();
        }
    }
    public void addTower(Tower tower){
        this.towers.add(tower);
    }

    public void removeTower(Tower tower){
        this.towers.remove(tower);
    }

    public GridPane getGrid() {
        return grid;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public ArrayList<PNJ> getPnjs() {
        return pnjs;
    }

    public void setPnjs(ArrayList<PNJ> pnjs) {
        this.pnjs = pnjs;
    }

    public void setPause(boolean value) {
        isPause=value;
    }
}