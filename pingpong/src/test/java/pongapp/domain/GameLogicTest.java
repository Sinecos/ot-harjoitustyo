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
        var speedY = (ball.getPos().y >= 0) ? -1 : 1;

        ball.setSpeed(new Vector2(1,speedY));
        ball.setPosition(new Vector2(121, 10));
        Vector2 newSpeed = new Vector2(-1.1f,-1.1f);

        assertTrue(gl.increaseBallSpeed(ball, enemy, player, paddleHeight, paddleWidth).equals(newSpeed));
    }

    @Test
    public void increaseBallSpeedWhileHittingPaddleOfPlayer() {
        var speedY = (ball.getPos().y >= 0) ? 1 : -1;

        ball.setSpeed(new Vector2(-1,speedY));
        ball.setPosition(new Vector2(5, 5));
        Vector2 newSpeed = new Vector2(1.1f,1.1f);
        assertTrue(gl.increaseBallSpeed(ball, enemy, player, paddleHeight, paddleWidth).equals(newSpeed));
    }

    @Test
    public void ballBounceFromSides() {
        ball.setPosition(new Vector2(100, w_Height + 1));
        assertTrue(gl.ballBounce(ball, w_Height));
    }

    @Test
    public void ballDoesNotBounceFromSides() {
        ball.setPosition(new Vector2(100, w_Height - 10));
        assertFalse(gl.ballBounce(ball, w_Height));
    }

    @Test
    public void ballEndTheGame() {
        ball.setPosition(new Vector2(enemy.getPos().x + paddleWidth + 1, w_Height - 10));
        assertTrue(gl.endTheGame(ball, enemy, player, paddleWidth));
    }

    @Test
    public void ballDoesNotEndTheGame() {
        ball.setPosition(new Vector2(enemy.getPos().x + paddleWidth - 10, w_Height - 10));
        assertFalse(gl.endTheGame(ball, enemy, player, paddleWidth));
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
        var testPosY = enemy.getPos().y + 4;
        assertTrue(gl.enemyNewPosition(ball, enemy, w_Width, paddleHeight).y == testPosY);
    }

    @Test
    public void enemyNewPositionNegative() {
        ball.setPosition(new Vector2((w_Width - w_Width / 4) + 1, enemy.getPos().y + paddleHeight / 2 - 1));
        var testPosY = enemy.getPos().y - 4;
        assertTrue(gl.enemyNewPosition(ball, enemy, w_Width, paddleHeight).y == testPosY);
    }

    @Test
    public void limitPlayerPaddle() {
        player.setPosition(new Vector2(player.getPos().x, w_Height - paddleHeight + 1));
        var newPos = new Vector2(player.getPos().x, w_Height - paddleHeight);
        assertTrue(gl.limitPlayerPaddle(player, w_Height, paddleHeight).equals(newPos));
    }

    @Test
    public void p2NewPosDownPressed(){
        var testPosY = enemy.getPos().y + 12;
        assertTrue(gl.player2NewPosition(enemy, true, false).y == testPosY);
    }

    @Test
    public void p2NewPosUpPressed(){
        var testPosY = enemy.getPos().y - 12;
        assertTrue(gl.player2NewPosition(enemy, false, true).y == testPosY);
    }


    @Test
    public void hitPlayerPaddle() {
        ball.setPosition(new Vector2(player.getPos().x + paddleWidth - 1, player.getPos().y + 1));
        assertTrue(gl.p1HitPaddle(ball, player, paddleWidth, paddleHeight));
    }

    @Test
    public void notHitPlayerPaddle() {
        ball.setPosition(new Vector2(player.getPos().x + paddleWidth + 1, player.getPos().y + 1));
        assertFalse(gl.p1HitPaddle(ball, player, paddleWidth, paddleHeight));
    }

    @Test
    public void hitPlayer2PaddleAndGetScore() {
        ball.setPosition(new Vector2(enemy.getPos().x + (ball.getPos().x - ball.getWidth()), enemy.getPos().y + paddleHeight));
        assertTrue(gl.p2HitPaddle(ball, enemy, paddleWidth, paddleHeight));
    }

    @Test
    public void notHitPlayer2PaddleAndGetScore() {
        ball.setPosition(new Vector2(enemy.getPos().x + (ball.getPos().x - ball.getWidth()), enemy.getPos().y - paddleHeight));
        assertFalse(gl.p2HitPaddle(ball, enemy, paddleWidth, paddleHeight));
    }

    @Test
    public void horBoostMoveToRight(){
        gl.horBoostStart = true;
        gl.horBoostRight = true;
        var newPlayerPost = player.getPos().x + 0.4f;
        gl.giveHorizontalBoost(player, enemy, paddleWidth, w_Height, w_Width);

        assertTrue(player.getPos().x == newPlayerPost);

    }

    @Test
    public void horBoostRightFalse(){
        gl.horBoostStart = true;
        gl.horBoostRight = true;
        player.setPosition(new Vector2((w_Width / 2) - 199, player.getPos().y));
        gl.giveHorizontalBoost(player, enemy, paddleWidth, w_Height, w_Width);

        assertFalse(gl.horBoostRight);

    }

    @Test
    public void horBoostMoveToLeft(){
        gl.horBoostStart = true;
        gl.horBoostRight = false;
        var newPlayerPost = player.getPos().x;
        gl.giveHorizontalBoost(player, enemy, paddleWidth, w_Height, w_Width);

        assertTrue(player.getPos().x > newPlayerPost);

    }
}