package pongapp.domain;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class GameLogicTest {

    Entity ball;
    Entity player;
    Entity enemy;
    GameLogic gl;

    private int w_Width = 800;
    private int w_Height = 600;
    private int paddleWidth = 20;
    private static final int paddleHeight = 100;

    @Before
    public void setUp() throws Exception {
        ball = new Entity(new Vector2(0, 0), new Vector2(10, 10), 20, 20);
        player = new Entity(new Vector2(0, 0), new Vector2(5, 5), 20, 100);
        enemy = new Entity(new Vector2(0, 0), new Vector2(120, 10), 20, 100);
        gl = new GameLogic(0, 0);
    }

    @Test
    public void ballNewPosition() {
        ball.setSpeed(new Vector2(1,1));
        Vector2 newPosition = new Vector2(11,11);

        assertTrue(gl.ballNewPosition(ball).equals(newPosition));

    }

    @Test
    public void increaseBallSpeedWhileHittingPaddleOfEnemy() {
        ball.setSpeed(new Vector2(1,1));
        ball.setPosition(new Vector2(121, 10));
        Vector2 newSpeed = new Vector2(-1.5f,-1.5f);

        assertTrue(gl.increaseBallSpeedWhileHittingPaddle(ball, enemy, player, paddleHeight, paddleWidth).equals(newSpeed));
    }

    @Test
    public void increaseBallSpeedWhileHittingPaddleOfPlayer() {
        ball.setSpeed(new Vector2(-1,-1));
        ball.setPosition(new Vector2(5, 5));
        Vector2 newSpeed = new Vector2(1.5f,1.5f);

        assertTrue(gl.increaseBallSpeedWhileHittingPaddle(ball, enemy, player, paddleHeight, paddleWidth).equals(newSpeed));
    }

    @Test
    public void ballBounceFromSides() {
        ball.setPosition(new Vector2(100, w_Height + 1));
        assertTrue(gl.ballBounceFromSides(ball, w_Height));
    }

    @Test
    public void ballDoesNotBounceFromSides() {
        ball.setPosition(new Vector2(100, w_Height - 10));
        assertFalse(gl.ballBounceFromSides(ball, w_Height));
    }

    @Test
    public void ballEndTheGame() {
        ball.setPosition(new Vector2(enemy.getPos().x + paddleWidth + 1, w_Height - 10));
        assertTrue(gl.ballEndTheGame(ball, enemy, player, paddleWidth));
    }

    @Test
    public void ballDoesNotEndTheGame() {
        ball.setPosition(new Vector2(enemy.getPos().x + paddleWidth - 10, w_Height - 10));
        assertFalse(gl.ballEndTheGame(ball, enemy, player, paddleWidth));
    }

    @Test
    public void limitEnemyPaddle() {
        enemy.setPosition(new Vector2(enemy.getPos().x, (w_Height - paddleHeight) + 1));
        var ypos = w_Height - paddleHeight;
        var newposy = gl.limitEnemyPaddle(enemy,w_Height, paddleHeight).y;
        assertTrue(ypos == newposy);
    }

    @Test
    public void limitEnemyPaddleSet_Y_IsZero() {
        enemy.setPosition(new Vector2(enemy.getPos().x, -1));
        var newposy = gl.limitEnemyPaddle(enemy,w_Height, paddleHeight).y;
        assertTrue(0 == newposy);
    }

    @Test
    public void enemyNewPosition() {
        ball.setPosition(new Vector2((w_Width - w_Width / 4) - 1, w_Height - 10));
        var testPosY = ball.getPos().y - paddleHeight / 2;
        assertTrue(gl.enemyNewPosition(ball, enemy, w_Width, paddleHeight).y == testPosY);
    }

    @Test
    public void enemyNewPositionPositive() {
        ball.setPosition(new Vector2((w_Width - w_Width / 4) + 1, enemy.getPos().y + paddleHeight / 2 + 1));
        var testPosY = enemy.getPos().y + 1;
        assertTrue(gl.enemyNewPosition(ball, enemy, w_Width, paddleHeight).y == testPosY);
    }

    @Test
    public void enemyNewPositionNegative() {
        ball.setPosition(new Vector2((w_Width - w_Width / 4) + 1, enemy.getPos().y + paddleHeight / 2 - 1));
        var testPosY = enemy.getPos().y - 1;
        assertTrue(gl.enemyNewPosition(ball, enemy, w_Width, paddleHeight).y == testPosY);
    }

    @Test
    public void limitPlayerPaddle() {
        player.setPosition(new Vector2(player.getPos().x, w_Height - paddleHeight + 1));
        var newPos = new Vector2(player.getPos().x, w_Height - paddleHeight);
        assertTrue(gl.limitPlayerPaddle(player, w_Height, paddleHeight).equals(newPos));
    }

    @Test
    public void hitPlayerPaddleAndGetScoreGetScore() {
        ball.setPosition(new Vector2(player.getPos().x + paddleWidth - 1, player.getPos().y + 1));
        assertTrue(gl.hitPlayerPaddleAndGetScore(ball, player, paddleWidth, paddleHeight));
    }

    @Test
    public void notHittedPlayerPaddleAndGetScoreGetScore() {
        ball.setPosition(new Vector2(player.getPos().x + paddleWidth + 1, player.getPos().y + 1));
        assertFalse(gl.hitPlayerPaddleAndGetScore(ball, player, paddleWidth, paddleHeight));
    }
}