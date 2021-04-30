package pongapp.domain;

public class GameLogic {

    /**
     * Player 1 Score
     */
    public int player1Score;

    /**
     * Player 2 Score
     */
    public int player2Score;

    /**
     * HorizontalBoost arvot käytetään paddlen x-suuntaisiin liikkeisiin
     */
    public boolean horBoostRight = true;
    public boolean horBoostStart = false;
    public int horBoostLimit = 4;

    /**
     * Alustaa GameLogic luokkaa
     *
     * @param   playerScore   Player 1 alustava score
     *
     * @param   player2Score  Player 2 alustava score
     */
    public GameLogic(int playerScore, int player2Score) {
        player1Score = playerScore;
        this.player2Score = player2Score;
    }

    /**
     * Antaa pallolle uusi paikka
     *
     * @param  ball ball entity
     *
     * @return palauttaa uusi position
     */
    public Vector2 ballNewPosition(Entity ball) {
        ball.getPos().x += ball.getSpeed().x;
        ball.getPos().y += ball.getSpeed().y;

        return ball.getPos();
    }

    /**
     * Kun pallo osuu joko pelaajaan tai vihollisen paddleen, niin pallon nopeus kasvaa ja suunta muuttuu.
     *
     * @param  ball ball entity
     *
     * @param  enemy enemy/player2 entity
     *
     * @param  player player 1 entity
     *
     * @param  paddleHeight paddlen korkeus
     *
     * @param  paddleWidth paddlen leveys
     *
     * @return palauttaa uusi nopeus
     */
    public Vector2 increaseBallSpeed(Entity ball, Entity enemy, Entity player, int paddleHeight, int paddleWidth) {
        //increase the ball getSpeed at hitting the paddle
        if (((ball.getPos().x + ball.getWidth() > enemy.getPos().x) && ball.getPos().y >= enemy.getPos().y
                && ball.getPos().y <= enemy.getPos().y + paddleHeight) ||
                ((ball.getPos().x < player.getPos().x + paddleWidth) && ball.getPos().y >=
                        player.getPos().y && ball.getPos().y <= player.getPos().y + paddleHeight)) {
            ball.getSpeed().y += 0.1f * Math.signum(ball.getSpeed().y);
            ball.getSpeed().x += 0.1f * Math.signum(ball.getSpeed().x);
            ball.getSpeed().x *= -1;
            ball.getSpeed().y *= -1;
        }

        return ball.getSpeed();
    }

    /**
     * Jos pallo menee ala tai yla rajaan, niin se saa uuden speed. Talloin pallo ei voi menna rajojen ulkopuolelle
     *
     * @param  ball ball entity
     *
     * @param  wHeight resoluution korkeus
     *
     * @return palauttaa true jos pallo osuu ala tai yla rajaan
     */
    public boolean ballBounce(Entity ball, int wHeight) {
        //Ball stays always inside canvas
        if (ball.getPos().y > wHeight || ball.getPos().y < 0) {
            ball.getSpeed().y *= -1;
            return true;
        }

        return false;
    }

    /**
     * Peli paattyy jos pallo menee pelaajan paddlen tai enemy/pelaaja2 paddle taakse
     *
     * @param  ball ball entity
     *
     * @param  enemy enemy/player2 entity
     *
     * @param  player player 1 entity
     *
     * @param  paddleWidth paddlen leveys
     *
     * @return palauttaa true jos peli paattyi
     */
    public boolean endTheGame(Entity ball, Entity enemy, Entity player, int paddleWidth) {
        if ((ball.getPos().x > enemy.getPos().x + paddleWidth) || (ball.getPos().x < player.getPos().x - paddleWidth)) {
            return true;
        }

        return false;
    }

    /**
     * enemy/player2 paddle ei voi menna ylos rajasta pois, seka ala rajasta pois.
     *
     * @param  enemy enemy/player2 entity
     *
     * @param  wHeight resoluution korkeus
     *
     * @param  paddleHeight paddlen korkeus
     *
     * @return palauttaa uusi position arvo
     */
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

    /**
     * Antaa enemy:lle uusi paikka
     *
     * @param  ball ball entity
     *
     * @param  enemy enemy entity
     *
     * @param  wWidth resoluution leveys
     *
     * @param  paddleHeight paddlen korkeus
     *
     * @return palauttaa uusi enemy position arvo
     */
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

    /**
     * Antaa pelaaja2:lle uusi paikka
     *
     * @param  enemy enemy/player2 entity
     *
     * @param  downPressed kun pelaaja2 painaa ala nappia
     *
     * @param  upPressed kun pelaaja2 painaa yla nappia
     *
     * @return palauttaa uusi player2 position arvo
     */
    public Vector2 player2NewPosition(Entity enemy, boolean downPressed, boolean upPressed) {

        if (downPressed) {
            enemy.getPos().y += 12f;
        } else if (upPressed) {
            enemy.getPos().y -= 12f;
        }

        return enemy.getPos();
    }

    /**
     * pelaajan 1 paddle ei voi menna ylos rajasta pois, seka ala rajasta pois.
     *
     * @param  player player1 entity
     *
     * @param  wHeight resoluution korkeus
     *
     * @param  paddleHeight paddlen korkeus
     *
     * @return palauttaa uusi position arvo pelaajalle
     */
    public Vector2 limitPlayerPaddle(Entity player, int wHeight, int paddleHeight) {
        //Limit of player paddle
        if (player.getPos().y > wHeight - paddleHeight) {
            player.getPos().y = wHeight - paddleHeight;
        }

        return player.getPos();
    }

    /**
     * Kun pallo osuu pelaajan paddleen niin halutaan saada boolean arvo, jotta voidaan pisteyttaa pelaajaa.
     *
     * @param  ball ball entity
     *
     * @param  player player1 entity
     *
     * @param  paddleWidth paddlen leveys
     *
     * @param  paddleHeight paddlen korkeus
     *
     * @return palauttaa true jos pallo osuu pelaajan paddleen
     */
    public boolean p1HitPaddle(Entity ball, Entity player, int paddleWidth, int paddleHeight) {
        if (((ball.getPos().x < player.getPos().x + paddleWidth) && ball.getPos().y >=
                player.getPos().y && ball.getPos().y <= player.getPos().y + paddleHeight)) {

            return true;
        }

        return false;
    }

    /**
     * Kun pallo osuu pelaajan 2 paddleen niin halutaan saada boolean arvo, jotta voidaan pisteyttaa pelaajaa.
     *
     * @param  ball ball entity
     *
     * @param  enemy player2 entity
     *
     * @param  paddleWidth paddlen leveys
     *
     * @param  paddleHeight paddlen korkeus
     *
     * @return palauttaa true jos pallo osuu pelaajan 2 paddleen
     */
    public boolean p2HitPaddle(Entity ball, Entity enemy, int paddleWidth, int paddleHeight) {

        if (((ball.getPos().x + ball.getWidth() > enemy.getPos().x) && ball.getPos().y >= enemy.getPos().y
                && ball.getPos().y <= enemy.getPos().y + paddleHeight)) {

            return true;
        }
        return false;
    }

    /**
     * Antaa horizontal boos pelaaja 1 ja enemy:lle
     *
     * @param  player player 1 entity
     *
     * @param  enemy enemy entity
     *
     * @param  paddleWidth paddlen leveys
     *
     * @param  wHeight resoluution korkeus
     *
     * @param  wWidth resoluution leveys
     */
    public void giveHorizontalBoost(Entity player, Entity enemy, int paddleWidth, int wHeight, int wWidth) {

        Vector2 playerOrigin = new Vector2(paddleWidth, wHeight / 2);
        Vector2 enemyOrigin =  new Vector2(wWidth - (paddleWidth * 2), wHeight / 2);

        if (horBoostStart) {
            if (horBoostRight) {
                player.getPos().x += 0.4f;
                enemy.getPos().x -= 0.4f;
            } else if (!horBoostRight) {
                player.getPos().x -= 0.4f;
                enemy.getPos().x += 0.4f;
            }

            if (player.getPos().x > (wWidth / 2) - 200 && horBoostRight) {
                horBoostRight = false;
            }

            if (player.getPos().x < playerOrigin.x && !horBoostRight) {
                player.setPosition(new Vector2(playerOrigin.x, player.getPos().y));
                enemy.setPosition(new Vector2(enemyOrigin.x, enemy.getPos().y));
                horBoostStart = false;
                horBoostRight = true;
                horBoostLimit = player1Score + 4;
            }
        }
    }

}
