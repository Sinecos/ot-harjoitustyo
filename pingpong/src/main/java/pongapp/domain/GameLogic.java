package pongapp.domain;

public class GameLogic {

    public int player1Score;
    public int player2Score;

    public GameLogic(int playerScore, int player2Score) {
        player1Score = playerScore;
        this.player2Score = player2Score;
    }

    public Vector2 ballNewPosition(Entity ball) {
        ball.getPos().x += ball.getSpeed().x;
        ball.getPos().y += ball.getSpeed().y;

        return ball.getPos();
    }

    public Vector2 increaseBallSpeed(Entity ball, Entity enemy, Entity player, int paddleHeight, int paddleWidth) {
        //increase the ball getSpeed at hitting the paddle
        if (((ball.getPos().x + ball.getWidth() > enemy.getPos().x) && ball.getPos().y >= enemy.getPos().y
                && ball.getPos().y <= enemy.getPos().y + paddleHeight) ||
                ((ball.getPos().x < player.getPos().x + paddleWidth) && ball.getPos().y >=
                        player.getPos().y && ball.getPos().y <= player.getPos().y + paddleHeight)) {
            ball.getSpeed().y += 0.5f * Math.signum(ball.getSpeed().y);
            ball.getSpeed().x += 0.5f * Math.signum(ball.getSpeed().x);
            ball.getSpeed().x *= -1;
            ball.getSpeed().y *= -1;
            //_hitCount = _hitCount + 1;
        }

        return ball.getSpeed();
    }

    public boolean ballBounce(Entity ball, int wHeight) {
        //Ball stays always inside canvas
        if (ball.getPos().y > wHeight || ball.getPos().y < 0) {
            ball.getSpeed().y *= -1;
            return true;
        }

        return false;
    }

    public boolean endTheGame(Entity ball, Entity enemy, Entity player, int paddleWidth) {
        if ((ball.getPos().x > enemy.getPos().x + paddleWidth) || (ball.getPos().x < player.getPos().x - paddleWidth)) {
            return true;
        }

        return false;
    }

    public Vector2 limitEnemyPaddle(Entity enemy, int wHeight, int paddleHeight) {
        //Limit of enemy paddle
        if (enemy.getPos().y > wHeight - paddleHeight) {
            enemy.getPos().y = wHeight - paddleHeight;
        }

        if (enemy.getPos().y < 0) {
            enemy.getPos().y = 0;
        }

        return enemy.getPos();
    }

    public Vector2 enemyNewPosition(Entity ball, Entity enemy, int wWidth, int paddleHeight) {

        //Enemy AI Movement:
        if (ball.getPos().x < wWidth - wWidth / 4) {

            enemy.getPos().y = ball.getPos().y - paddleHeight / 2;
        } else {
            enemy.getPos().y = (ball.getPos().y > enemy.getPos().y + paddleHeight / 2) ?
                    enemy.getPos().y += 1 : enemy.getPos().y - 1;
        }

        return enemy.getPos();
    }

    public Vector2 limitPlayerPaddle(Entity player, int wHeight, int paddleHeight) {
        //Limit of player paddle
        if (player.getPos().y > wHeight - paddleHeight) {
            player.getPos().y = wHeight - paddleHeight;
        }

        return player.getPos();
    }

    public boolean getScore(Entity ball, Entity player, int paddleWidth, int paddleHeight) {
        if (((ball.getPos().x < player.getPos().x + paddleWidth) && ball.getPos().y >=
                player.getPos().y && ball.getPos().y <= player.getPos().y + paddleHeight)) {

            player1Score += 1;
            return true;
        }

        return false;
    }
}
