package pongapp.ui;

import javafx.geometry.Insets;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import pongapp.dao.FileScoreDao;
import pongapp.domain.*;

import java.io.*;
import java.util.*;

import static javafx.application.Platform.exit;

public class PongUi extends Application {

    GameLogic gameLogic = new GameLogic(0, 0);
    FileScoreDao scoreDao;

    private static final int w_Width = 800;
    private static final int w_Height = 600;
    private static final int paddleWidth = 20;
    private static final int paddleHeight = 100;
    private static final Graphics graph = new Graphics();

    private StackPane stack;
    private GraphicsContext gc;
    private Canvas canvas;
    private Stage _stage;
    private Timeline tl;
    private Timeline tls;

    private Scene mainMenuScene;
    private Scene gameScene;
    private Scene highScoreScene;


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

        try {
            playerScoreInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        _stage = stage;
        stage.setTitle("Pong Game");
        canvas = new Canvas(w_Width, w_Height);
        stack = new StackPane(canvas);
        gc = canvas.getGraphicsContext2D();

        graph.init(gc);

        //Enemy, Player and Ball Init:
        Init();

        mainMenu(gc, stack);
        mainMenuScene = new Scene(stack);
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public void Init(){

        gameLogic.player1Score = 0;
        gameLogic.player2Score = 0;

        player = new Entity(new Vector2(1,1), new Vector2(paddleWidth, w_Height / 2), paddleWidth, paddleHeight);

        enemy = new Entity(new Vector2(1,1), new Vector2(w_Width - (paddleWidth * 2), w_Height / 2),
                paddleWidth, paddleHeight);


        ball = new Entity(new Vector2((new Random().nextInt(2) == 0 ? 2: -2),(new Random().nextInt(2) == 0 ? 2: -2)),
                new Vector2(w_Width / 2, w_Height / 2), 20, 20);

        //Set player input as mouse input
        canvas.setOnMouseMoved(e -> player.getPos().y = (float) e.getY());

    }

    public void playerScoreInit() throws Exception{
        Properties properties = new Properties();
        File file = new File("config.properties");
        File file2 = new File("playerScore.txt");

        if (file.length() == 0){
            try {
                FileWriter myWriter = new FileWriter(file);
                myWriter.write("playerScoreFile=playerScore.txt");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        if (file2.length() == 0){
            try {
                FileWriter myWriter = new FileWriter(file2);
                myWriter.write("Matti;1" + "\n" + "Nuur;6");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }


        file.createNewFile();
        file2.createNewFile();

        var oFile  = new FileInputStream(file);

        properties.load(oFile);
        String playerScoreFile = properties.getProperty("playerScoreFile");
        scoreDao = new FileScoreDao(playerScoreFile);
    }

    public void mainMenu(GraphicsContext gc, StackPane stackPane){
        graph.fillRect(Color.BLACK, 0, 0,  w_Width, w_Height);
        Font font = Font.font(20);

        Text t = new Text(10, 50, "PONG GAME");
        t.setFont(new Font(30));
        t.setFill(Color.YELLOW);

        Button newGameButton = new Button("NEW GAME (COMPUTER)");
        newGameButton.setFont(font);
        newGameButton.setOnAction(e -> startGame());

        Button highScoreButton = new Button("HIGH SCORE");
        highScoreButton.setFont(font);
        highScoreButton.setOnAction(e -> startScoreTable(true));

        Button exitGameButton = new Button("EXIT GAME");
        exitGameButton.setFont(font);

        exitGameButton.setOnAction(e->{
           exit();
        });

        VBox box = new VBox(20, t , newGameButton, highScoreButton, exitGameButton);

        stackPane.getChildren().addAll(box);
        box.setAlignment(Pos.CENTER);
    }

    protected void startScoreTable(boolean state){
        gameState = GameState.ScoreTable;

        stack = new StackPane(canvas);
        tl = new Timeline(new KeyFrame(Duration.millis(10), e -> scoreTableStart(stack, state)));
        tl.setCycleCount(Timeline.INDEFINITE);
        highScoreScene = new Scene(stack);
        _stage.setScene(highScoreScene);
        _stage.show();
        tl.play();
    }

    protected void startGame(){
        Init();
        gameState = GameState.GameWithAIStart;
        stack = new StackPane(canvas);
        tl = new Timeline(new KeyFrame(Duration.millis(10), e -> update(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        gameScene = new Scene(stack);
        _stage.setScene(gameScene);
        _stage.show();
        tl.play();
    }

    protected void backToMainMenu(){

        if(tl != null)
            tl.stop();

        stack = new StackPane(canvas);
        gc = canvas.getGraphicsContext2D();

        gameState = GameState.MainMenu;
        stack = new StackPane(canvas);
        mainMenuScene = new Scene(stack);

        _stage.setScene(mainMenuScene);
        _stage.show();
        mainMenu(gc,stack);
    }

    protected void scoreTableStart(StackPane stackPane, boolean fromMainMenu){
        gameState = GameState.ScoreTable;
        graph.fillRect(Color.BLUE, 0, 0,  w_Width, w_Height);

        Text t = new Text(10, 50, "HIGH SCORE TABLE");
        Text yourScore = new Text(10, 50, "Your Score: " + String.valueOf(gameLogic.player1Score));
        Text playerScores = new Text(12, 50, "");

        t.setFont(new Font(20));
        yourScore.setFont(new Font(20));
        playerScores.setFont(new Font(13));

        t.setFill(Color.YELLOW);
        yourScore.setFill(Color.WHITE);
        playerScores.setFill(Color.YELLOW);

        TextField userdata = new TextField("Name");

        var button = new Button("Add");

        var mainMenuButton = new Button("MAIN MENU");

        //Show the names and scores
        ShowScores(playerScores);

        if(scoreDao.allowAddNewScore) {
            button.setOnAction(e -> {
                try {
                    scoreDao.create(new PlayerScore(userdata.getText(), gameLogic.player1Score));
                    scoreDao.allowAddNewScore = false;

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));

        if(!fromMainMenu)
            layout.getChildren().addAll(t, yourScore, userdata, button, playerScores, mainMenuButton);
        else{
            graph.fillRect(Color.BLUE, 0, 0,  w_Width, w_Height);
            layout.getChildren().addAll(t, playerScores, mainMenuButton);
        }


        stackPane.getChildren().addAll(layout);
        layout.setAlignment(Pos.TOP_CENTER);

        if(!fromMainMenu){
            tls = new Timeline(new KeyFrame(Duration.millis(10), e -> scoreSceneAfterGame(gc, playerScores, button)));
            tls.setCycleCount(Timeline.INDEFINITE);
            tls.play();
        }


        mainMenuButton.setOnAction(e -> {
            tl.stop();
            gameState = GameState.MainMenu;
            backToMainMenu();
            return;
        });

        tl.stop();
    }

    public void scoreSceneAfterGame(GraphicsContext gc, Text playerScores, Button button){

        //Show the names and scores
        ShowScores(playerScores);

        if(scoreDao.allowAddNewScore == false){
            button.setOnAction(null);
            tls.stop();
        }
    }


    public void update(GraphicsContext gc){

        graph.fillRect(Color.BLACK, 0, 0, w_Width, w_Height);

        if(gameState == GameState.GameWithAIStart){
            //End Game Go to Score Table
            if(gameLogic.endTheGame(ball, enemy, player, paddleWidth)) {

                if(scoreDao != null)
                    scoreDao.allowAddNewScore = true;

                tl.stop();

                startScoreTable(false);
                return;
            }

            //Ball logic
            gameLogic.ballNewPosition(ball);
            gameLogic.ballBounce(ball, w_Height);
            gameLogic.increaseBallSpeed(ball, enemy, player, paddleHeight, paddleWidth);

            //Enemy logic
            gameLogic.enemyNewPosition(ball, enemy, w_Width, paddleHeight);
            gameLogic.limitEnemyPaddle(enemy,w_Height,paddleHeight);

            //Player logic
            gameLogic.limitPlayerPaddle(player,w_Height,paddleHeight);
            gameLogic.getScore(ball,player,paddleWidth,paddleHeight);

            //DrawEntities:
            DrawEntities(ball, enemy, player);
        }
    }

    private void DrawEntities(Entity ball, Entity enemy, Entity player){

        //Draw Ball
        graph.fillRect(Color.WHITE, ball.getPos().x, ball.getPos().y, ball.getWidth(), ball.getHeight());

        // draw player and enemy
        graph.fillRect(Color.WHITE, player.getPos().x, player.getPos().y , paddleWidth, paddleHeight);
        graph.fillRect(Color.WHITE, enemy.getPos().x, enemy.getPos().y, paddleWidth, paddleHeight);

        //draw player score
        graph.fillText(Color.YELLOW, String.valueOf(gameLogic.player1Score), w_Width/ 2, 100);
    }

    public void ShowScores(Text playerScores){

        String status = "";

        if(scoreDao.getAllPlayers().size() == 0){
            status = "Empty Table";
            playerScores.setText(status);
            return;
        }

        scoreDao.sortScores();

        int max = 10;
        int current = 0;
        status = "";
        String viiva = "--------------------------------------------------------" + "\n";
        status += viiva;
        //Show only 10:
        for (PlayerScore player : scoreDao.getAllPlayers()){

            if(current == max)
                break;

            status += (player.getName() + " " + player.getScore() + "\n");
            status += viiva;
            current++;
        }

        playerScores.setText(status);
    }

    public static void main(String[] args) {
        launch();
    }

}
