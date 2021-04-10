package pongapp.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import pongapp.domain.Graphics;

import static javafx.application.Platform.exit;

public class PongUi extends Application {

    private static final int w_Width = 800;
    private static final int w_Height = 600;
    private static final Graphics graph = new Graphics();

    private GraphicsContext gc;
    private Canvas canvas;
    private Stage _stage;

    private static enum GameState{
        MainMenu,
        ScoreTable,
        GameStart
    }

    private GameState gameState = GameState.MainMenu;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage stage) {

        _stage = stage;
        stage.setTitle("Pong Game");
        canvas = new Canvas(w_Width, w_Height);
        StackPane stackPane = new StackPane(canvas);

        gc = canvas.getGraphicsContext2D();
        mainMenu(gc, stackPane);
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    public void mainMenu(GraphicsContext gc, StackPane stackPane){
        graph.FillRect(gc, Color.BLACK, 0, 0,  w_Width, w_Height);

        Font font = Font.font(20);

        Button newGameButton = new Button("NEW GAME");
        newGameButton.setFont(font);
        newGameButton.setOnAction(e -> startGame());

        Button exitGameButton = new Button("EXIT GAME");
        exitGameButton.setFont(font);

        exitGameButton.setOnAction(e->{
           exit();
        });


        VBox box = new VBox(20, newGameButton, exitGameButton);

        stackPane.getChildren().addAll(box);
        box.setAlignment(Pos.CENTER);
    }

    protected void startGame(){
        gameState = GameState.GameStart;

        StackPane stackPane = new StackPane(canvas);
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> update(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        _stage.setScene(new Scene(stackPane));
        _stage.show();
        tl.play();

    }

    public void update(GraphicsContext gc){

        graph.FillRect(gc, Color.BLACK, 0, 0, w_Width, w_Height);

        // draw players
        graph.FillRect(gc, Color.WHITE, 20, w_Height / 2 - 50 , 20, 100);
        graph.FillRect(gc, Color.WHITE, w_Width - (20 * 2), w_Height / 2 - 50, 20, 100);
    }

    public static void main(String[] args) {
        launch();
    }

}
