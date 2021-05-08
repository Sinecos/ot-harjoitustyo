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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    private static final int WWIDTH = 800;
    private static final int WHEIGHT = 600;
    private static final int PADDLE_W = 20;
    private static final int PADDLE_H = 100;
    private static final Graphics GRAPH = new Graphics();

    private StackPane stack;
    private GraphicsContext gc;
    private Canvas canvas;
    private Stage stage;
    private Timeline tl;
    private Timeline tls;

    private Scene mainMenuScene;
    private Scene gameScene;
    private Scene highScoreScene;

    private Entity player;
    private Entity enemy;
    private Entity ball;

    //Player2 boolean input
    boolean p2DownPressed = false;
    boolean p2UpPressed = false;

    private static enum GameState {
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

        this.stage = stage;
        stage.setTitle("Pong Game");
        canvas = new Canvas(WWIDTH, WHEIGHT);
        stack = new StackPane(canvas);
        gc = canvas.getGraphicsContext2D();

        GRAPH.init(gc);

        //Enemy, Player and Ball initialize:
        initialize();

        mainMenu(gc, stack);
        mainMenuScene = new Scene(stack);
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public void initialize() {

        gameLogic.player1Score = 0;
        gameLogic.player2Score = 0;

        player = new Entity(new Vector2(1, 1), new Vector2(PADDLE_W, WHEIGHT / 2), PADDLE_W, PADDLE_H);

        enemy = new Entity(new Vector2(1, 1), new Vector2(WWIDTH - (PADDLE_W * 2), WHEIGHT / 2),
                PADDLE_W, PADDLE_H);

        ball = new Entity(new Vector2((new Random().nextInt(2) == 0 ? 2 : -2), (new Random().nextInt(2) == 0 ? 2 : -2)),
                new Vector2(WWIDTH / 2, WHEIGHT / 2), 20, 20);

        //Set player input as mouse input
        canvas.setOnMouseMoved(e -> player.getPos().y = (float) e.getY());

        gameLogic.horBoostStart = false;
        gameLogic.horBoostRight = true;
        gameLogic.horBoostLimit = 4;
    }

    public void playerScoreInit() throws Exception {
        Properties properties = new Properties();
        File file = new File("config.properties");
        File file2 = new File("playerScore.txt");

        if (file.length() == 0) {
            try {
                FileWriter myWriter = new FileWriter(file);
                myWriter.write("playerScoreFile=playerScore.txt");
                myWriter.close();
                System.out.println("Successfully wrote playerScoreFile=playerScore.txt to the file config.properties.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        if (file2.length() == 0) {
            try {
                FileWriter myWriter = new FileWriter(file2);
                myWriter.write("Matti;1" + "\n" + "Nuur;6");
                myWriter.close();
                System.out.println("Successfully wrote to the file playerScore.txt.");
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

    public void mainMenu(GraphicsContext gc, StackPane stackPane) {
        GRAPH.fillRect(Color.BLACK, 0, 0, WWIDTH, WHEIGHT);
        Font font = Font.font(20);

        Text t = new Text(10, 50, "PONG GAME");
        t.setFont(new Font(30));
        t.setFill(Color.YELLOW);

        Button newGameButton = new Button("NEW GAME (Computer)");
        newGameButton.setFont(font);
        newGameButton.setOnAction(e -> startGame(false));

        Button newGameP2Button = new Button("NEW GAME (Player2)");
        newGameP2Button.setFont(font);
        newGameP2Button.setOnAction(e -> startGame(true));

        Button highScoreButton = new Button("HIGH SCORE");
        highScoreButton.setFont(font);
        highScoreButton.setOnAction(e -> toScoreTable(true, false, true));

        Button exitGameButton = new Button("EXIT GAME");
        exitGameButton.setFont(font);

        exitGameButton.setOnAction(e-> {
            exit();
        });

        VBox box = new VBox(20, t , newGameButton, newGameP2Button, highScoreButton, exitGameButton);

        stackPane.getChildren().addAll(box);
        box.setAlignment(Pos.CENTER);
    }

    protected void toScoreTable(boolean state, boolean twoPlayerMode, boolean winTheGame) {
        gameState = GameState.ScoreTable;
        stack = new StackPane(canvas);
        tl = new Timeline(new KeyFrame(Duration.millis(10), e -> scoreTableScene(stack, state, twoPlayerMode, winTheGame)));
        tl.setCycleCount(Timeline.INDEFINITE);
        highScoreScene = new Scene(stack);
        stage.setScene(highScoreScene);
        stage.show();
        tl.play();
    }

    protected void startGame(boolean playerMode) {
        initialize();

        if (playerMode) {
            gameState = GameState.GameWithPlayerStart;
        } else {
            gameState = GameState.GameWithAIStart;
        }

        stack = new StackPane(canvas);
        tl = new Timeline(new KeyFrame(Duration.millis(10), e -> update(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        gameScene = new Scene(stack);
        stage.setScene(gameScene);
        stage.show();
        tl.play();
    }

    protected void backToMainMenu() {

        if (tl != null) {
            tl.stop();
        }

        stack = new StackPane(canvas);
        gc = canvas.getGraphicsContext2D();

        gameState = GameState.MainMenu;
        stack = new StackPane(canvas);
        mainMenuScene = new Scene(stack);

        stage.setScene(mainMenuScene);
        stage.show();
        mainMenu(gc, stack);
    }

    protected void scoreTableScene(StackPane stackPane, boolean fromMainMenu, boolean twoPlayerMode, boolean winTheGame) {
        gameState = GameState.ScoreTable;

        String finalScore = "Player 1 Wins! Your Score: " + String.valueOf(gameLogic.player1Score);

        if (twoPlayerMode) {
            if (gameLogic.player2Score > gameLogic.player1Score) {
                finalScore = "Player 2 Wins! Your Score: " + String.valueOf(gameLogic.player2Score);
            }
        }

        if (!twoPlayerMode && !winTheGame) {
            finalScore = "Player 1 Loses!";
        }

        GRAPH.fillRect(Color.BLUE, 0, 0, WWIDTH, WHEIGHT);
        Text t = new Text(10, 50, "HIGH SCORE TABLE");
        Text yourScore = new Text(10, 50, finalScore);
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
        showScores(playerScores);

        if (scoreDao.allowAddNewScore) {
            button.setOnAction(e -> {
                try {

                    if (twoPlayerMode) {
                        if (gameLogic.player2Score > gameLogic.player1Score) {
                            scoreDao.create(new PlayerScore(userdata.getText(), gameLogic.player2Score));
                        } else {
                            scoreDao.create(new PlayerScore(userdata.getText(), gameLogic.player1Score));
                        }
                    } else {
                        scoreDao.create(new PlayerScore(userdata.getText(), gameLogic.player1Score));
                    }

                    scoreDao.allowAddNewScore = false;

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        if (!fromMainMenu) {
            if (!winTheGame) {
                layout.getChildren().addAll(t, yourScore, playerScores, mainMenuButton);
            } else {
                layout.getChildren().addAll(t, yourScore, userdata, button, playerScores, mainMenuButton);
            }

        } else {
            GRAPH.fillRect(Color.BLUE, 0, 0, WWIDTH, WHEIGHT);
            layout.getChildren().addAll(t, playerScores, mainMenuButton);
        }

        stackPane.getChildren().addAll(layout);
        layout.setAlignment(Pos.TOP_CENTER);

        if (!fromMainMenu) {
            tls = new Timeline(new KeyFrame(Duration.millis(10), e -> updateScoreScene(gc, playerScores, button)));
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

    public void updateScoreScene(GraphicsContext gc, Text playerScores, Button button) {

        //Show the names and scores
        showScores(playerScores);

        if (scoreDao.allowAddNewScore == false) {
            button.setOnAction(null);
            tls.stop();
        }
    }

    public void update(GraphicsContext gc) {

        exitToMainMenu();

        GRAPH.fillRect(Color.BLACK, 0, 0, WWIDTH, WHEIGHT);

        //End Game Go to Score Table
        if (gameLogic.endTheGame(ball, enemy, player, PADDLE_W)) {

            if (scoreDao != null) {
                scoreDao.allowAddNewScore = true;
            }

            tl.stop();

            if (gameState == GameState.GameWithAIStart) {
                if ((ball.getPos().x > enemy.getPos().x + PADDLE_W)) {
                    toScoreTable(false, false, true);
                } else {
                    toScoreTable(false, false, false);
                }
            } else {
                toScoreTable(false, true, true);
            }
            return;
        }

        //Ball logic
        gameLogic.ballNewPosition(ball);
        gameLogic.ballBounce(ball, WHEIGHT);
        gameLogic.increaseBallSpeed(ball, enemy, player, PADDLE_H, PADDLE_W);

        //Enemy logic
        if (gameState == GameState.GameWithAIStart) {
            gameLogic.enemyNewPosition(ball, enemy, WWIDTH, PADDLE_H);
        } else if (gameState == GameState.GameWithPlayerStart) {

            //Player2 Movement using keyboard:
            gameScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if (key.getCode() == KeyCode.DOWN) {
                    p2DownPressed = true;
                } else if (key.getCode() == KeyCode.UP) {
                    p2UpPressed = true;
                }
            });

            gameScene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
                if (key.getCode() == KeyCode.DOWN) {
                    p2DownPressed = false;
                } else if (key.getCode() == KeyCode.UP) {
                    p2UpPressed = false;
                }
            });

            gameLogic.player2NewPosition(enemy, p2DownPressed, p2UpPressed);
        }

        gameLogic.limitEnemyPaddle(enemy, WHEIGHT, PADDLE_H);

        //Player logic
        gameLogic.limitPlayerPaddle(player, WHEIGHT, PADDLE_H);

        if (gameState == GameState.GameWithAIStart) {
            if (gameLogic.player1Score > gameLogic.horBoostLimit) {
                gameLogic.horBoostStart = true;
            }

            gameLogic.giveHorizontalBoost(player, enemy, PADDLE_W, WHEIGHT, WWIDTH);
        }

        //Score for player 1 and player 2
        if (gameLogic.p1HitPaddle(ball, player, PADDLE_W, PADDLE_H)) {
            gameLogic.player1Score += 1;
        }

        if (gameLogic.p2HitPaddle(ball, enemy, PADDLE_W, PADDLE_H)) {
            gameLogic.player2Score += 1;
        }

        //drawEntities:
        drawEntities(ball, enemy, player);

    }

    private void drawEntities(Entity ball, Entity enemy, Entity player) {

        //Draw Ball
        GRAPH.fillRect(Color.WHITE, ball.getPos().x, ball.getPos().y, ball.getWidth(), ball.getHeight());

        // draw player and enemy
        GRAPH.fillRect(Color.WHITE, player.getPos().x, player.getPos().y , PADDLE_W, PADDLE_H);
        GRAPH.fillRect(Color.WHITE, enemy.getPos().x, enemy.getPos().y, PADDLE_W, PADDLE_H);

        //draw player score
        if (gameState == GameState.GameWithAIStart) {
            GRAPH.fillText(Color.YELLOW, String.valueOf(gameLogic.player1Score), WWIDTH / 2, 100);
        } else if (gameState == GameState.GameWithPlayerStart) {
            GRAPH.fillText(Color.YELLOW, "Player 1", 50, 50);
            GRAPH.fillText(Color.YELLOW, String.valueOf(gameLogic.player1Score), 100, 100);

            GRAPH.fillText(Color.YELLOW, "Player 2", WWIDTH - 150, 50);
            GRAPH.fillText(Color.YELLOW, String.valueOf(gameLogic.player2Score), WWIDTH - 100, 100);
        }

    }

    public void showScores(Text playerScores) {

        String status = "";

        if (scoreDao.getAllPlayers().size() == 0) {
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
        for (PlayerScore player : scoreDao.getAllPlayers()) {

            if (current == max) {
                break;
            }

            status += (player.getName() + " " + player.getScore() + "\n");
            status += viiva;
            current++;
        }

        playerScores.setText(status);
    }

    private void exitToMainMenu() {
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                backToMainMenu();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}
