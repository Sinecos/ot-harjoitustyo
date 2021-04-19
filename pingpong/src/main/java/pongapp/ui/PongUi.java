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
import pongapp.domain.Entity;
import pongapp.domain.GameLogic;
import pongapp.domain.Graphics;
import pongapp.domain.Vector2;

import java.util.Random;

import static javafx.application.Platform.exit;

public class PongUi extends Application {

    GameLogic gameLogic = new GameLogic(0);

    private static final int w_Width = 800;
    private static final int w_Height = 600;
    private static final int paddleWidth = 20;
    private static final int paddleHeight = 100;
    private static final Graphics graph = new Graphics();

    private GraphicsContext gc;
    private Canvas canvas;
    private Stage _stage;

    private Entity player;
    private Entity enemy;
    private Entity ball;

    private static enum GameState{
        MainMenu,
        ScoreTable,
        GameWithAIStart,
        GameWithPlayerStart
    }

    private GameState gameState = GameState.MainMenu;

    @Override
    public void start(Stage stage) {

        _stage = stage;
        stage.setTitle("Pong Game");
        canvas = new Canvas(w_Width, w_Height);
        StackPane stackPane = new StackPane(canvas);
        gc = canvas.getGraphicsContext2D();

        graph.init(gc);

        //Enemy, Player and Ball Init:
        Init();

        mainMenu(gc, stackPane);
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    public void Init(){


        player = new Entity(new Vector2(1,1), new Vector2(paddleWidth, w_Height / 2), paddleWidth, paddleHeight);

        enemy = new Entity(new Vector2(1,1), new Vector2(w_Width - (paddleWidth * 2), w_Height / 2),
                paddleWidth, paddleHeight);

        ball = new Entity(new Vector2((new Random().nextInt(2) == 0 ? 2: -2),(new Random().nextInt(2) == 0 ? 2: -2)),
                new Vector2(w_Width / 2, w_Height / 2), 20, 20);

        //Set player input as mouse input
        canvas.setOnMouseMoved(e -> player.getPos().y = (float) e.getY());

    }

    public void mainMenu(GraphicsContext gc, StackPane stackPane){
        graph.fillRect(Color.BLACK, 0, 0,  w_Width, w_Height);

        Font font = Font.font(20);

        Button newGameButton = new Button("NEW GAME (AI)");
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
        gameState = GameState.GameWithAIStart;

        StackPane stackPane = new StackPane(canvas);
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> update(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        _stage.setScene(new Scene(stackPane));
        _stage.show();
        tl.play();
    }

    public void update(GraphicsContext gc){

        graph.fillRect(Color.BLACK, 0, 0, w_Width, w_Height);

        if(gameState == GameState.GameWithAIStart){
            if(gameLogic.ballEndTheGame(ball, enemy, player, paddleWidth))
                gameState = gameState.MainMenu;

            //Ball logic
            gameLogic.ballNewPosition(ball);
            gameLogic.ballBounceFromSides(ball, w_Height);
            gameLogic.increaseBallSpeedWhileHittingPaddle(ball, enemy, player, paddleHeight, paddleWidth);

            //Enemy logic
            gameLogic.enemyNewPosition(ball, enemy, w_Width, paddleHeight);
            gameLogic.limitEnemyPaddle(enemy,w_Height,paddleHeight);

            //Player logic
            gameLogic.limitPlayerPaddle(player,w_Height,paddleHeight);
            gameLogic.hitPlayerPaddleAndGetScore(ball,player,paddleWidth,paddleHeight);

            //DrawEntities:
            DrawEntities(ball, enemy, player);
        }
    }

    private void DrawEntities(Entity ball, Entity enemy, Entity player){

        //Draw Ball
        graph.fillRect(Color.WHITE, ball.getPos().x, ball.getPos().y, ball.getWidth(), ball.getHeightHeight());

        // draw player and enemy
        graph.fillRect(Color.WHITE, player.getPos().x, player.getPos().y , paddleWidth, paddleHeight);
        graph.fillRect(Color.WHITE, enemy.getPos().x, enemy.getPos().y, paddleWidth, paddleHeight);

        //draw player score
        graph.fillText(Color.YELLOW, String.valueOf(gameLogic.player1Score), w_Width/ 2, 100);
    }

    public static void main(String[] args) {
        launch();
    }

}
