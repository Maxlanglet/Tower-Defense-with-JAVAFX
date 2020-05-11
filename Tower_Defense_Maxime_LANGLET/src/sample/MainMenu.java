package sample;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainMenu {

    private Button newGame;
    private Button credits;
    private Button Score;
    private VBox layout;
    private Button backToMenu;
    private TextField pseudota;
    private music music;
    private Pause pause;

    private Button enter;

    private String pseudo;


    private Label creds;

    private StackPane root;

    public MainMenu(StackPane root, boolean gameover){ //Pour GameOver
        this.root = root;

        this.music = new music();

        newGame = new Button("New Game");
        credits = new Button("Credits");
        Score = new Button("Scores");
        backToMenu = new Button("Back to Menu");

        creds = new Label("All rights resereved to Maxime Langlet, music made by Maxime Langlet using MIDI-Generator and Ableton. \n"+
                "Thanks to Mara Villa for the design of some towers and the artistic guidance.");
        creds.setTextFill(Color.BLACK);
        creds.setStyle("-fx-border-color: black;" +
                "-fx-background-color: white;");


        layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(newGame,Score,credits);

        setActions(newGame);
        setActions(credits);
        setActions(Score);
        setActions(backToMenu);
        setBackToMenu();

        newGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                layout.getChildren().remove(newGame);
                layout.getChildren().remove(credits);
                layout.getChildren().remove(Score);


                enter = new Button("Enter");
                setActions(enter);
                pseudota = new TextField();
                Label labelpseudo = new Label("Pseudo :");
                labelpseudo.setStyle("-fx-text-fill: black;");

                pseudota.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)){
                            launchGame();
                        }
                    }
                });


                enter.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        launchGame();
                    }
                });

                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);
                hBox.getChildren().addAll(labelpseudo, pseudota, enter, backToMenu);

                layout.getChildren().add(hBox);
            }
        });


        Score.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setScore();
            }
        });

        credits.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCredits();
            }
        });
    }


    public MainMenu(StackPane root){//surcharge du constructeur car lors d'un game over la StackPane root possède deja layout
        this(root, true);
        this.root.getChildren().addAll(layout);
        this.root.setAlignment(layout, Pos.CENTER);
    }


    private void setScore(){
        layout.getChildren().remove(newGame);
        layout.getChildren().remove(credits);
        layout.getChildren().remove(Score);


        Sauvegarde sauvegarde = new Sauvegarde();
        ScrollPane scroll = new ScrollPane();

        scroll.setPrefViewportWidth(100);
        scroll.setPrefViewportHeight(400);
        scroll.setStyle("-fx-background-color: transparent;");//apparement ne fonctionne pas

        String scoresLabel = "";
        Label Scores = new Label();
        int i=1;
        for (String string : sauvegarde.getListComplete()){
            scoresLabel = scoresLabel + i + ") " +string + " \n";
            i++;
        }

        Scores.setText(scoresLabel);
        scroll.setContent(Scores);

        layout.getChildren().add(scroll);
        layout.getChildren().add(backToMenu);
    }

    private void setCredits(){
        layout.getChildren().remove(newGame);
        layout.getChildren().remove(credits);
        layout.getChildren().remove(Score);


        layout.getChildren().addAll(creds);
        layout.getChildren().add(backToMenu);
    }

    private void setBackToMenu(){
        backToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                root.getChildren().clear();
                layout.getChildren().clear();
                layout.getChildren().addAll(newGame,Score,credits);
                root.getChildren().add(layout);
            }
        });
    }


    private void setActions(Button button){
        button.setStyle("-fx-border-color: black;\n" +
                "    -fx-border-width: 1;\n" +
                "    -fx-background-color: transparent;\n" +
                "    -fx-pref-height: 40px;\n" +
                "    -fx-pref-width: 100px;\n" +
                "    -fx-text-fill: black;");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setStyle("-fx-border-color: black;\n" +
                        "    -fx-border-width: 1;\n" +
                        "    -fx-background-color: rgba(232, 236, 241, 0.5);" +
                        "    -fx-pref-height: 40px;\n" +
                        "    -fx-pref-width: 100px;\n" +
                        "    -fx-text-fill: black;");
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setStyle("-fx-border-color: black;\n" +
                        "    -fx-border-width: 1;\n" +
                        "    -fx-background-color: transparent;\n" +
                        "    -fx-pref-height: 40px;\n" +
                        "    -fx-pref-width: 100px;\n" +
                        "    -fx-text-fill: black;");
            }
        });
    }

    private void launchGame(){
        pseudo = pseudota.getText();
        System.out.println(pseudo);

        root.getChildren().clear();

        Ressources labels = new Ressources();
        labels.setPseudo(pseudo);
        //------------ Menu Bar
        menuBar menuBar = new menuBar();
        //------------- Grid
        grid grid = new grid(menuBar, labels, root);
        //---------- Enemy spawn

        VBox vBox = new VBox(menuBar.getmenuBar());

        this.pause = new Pause(grid);

        VBox buttons = new VBox(music.getMute(), pause.getPause());


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(grid.getGrid());
        borderPane.setRight(buttons);
        borderPane.setLeft(labels.getVBox());

        System.out.println(labels.getVBox().getBoundsInParent().getMinX());

        vBox.getChildren().add(borderPane);


        root.getChildren().addAll(vBox);
        root.setAlignment(vBox, Pos.CENTER);

        Waves wave = new Waves(root, grid, labels);

        addPause(root, this);
    }

    public VBox getLayout() {
        return layout;
    }

    public Button getBackToMenu() {
        return backToMenu;
    }

    public Pause getPause(){
        return pause;
    }

    private void addPause(StackPane root, MainMenu mainMenu) {
        try{
            root.addEventFilter(KeyEvent.KEY_PRESSED, e ->{
                if (e.getCode()== KeyCode.SPACE){
                    mainMenu.getPause().makePause(mainMenu.getPause().getPause());
                }
            });
        }catch (NullPointerException e){
        }
    }
}
