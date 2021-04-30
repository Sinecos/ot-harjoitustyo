package pongapp.domain;

public class Entity {
    private Vector2 speed;
    private Vector2 pos;
    private int width;
    private int height;

    /**
     * Alustaa Entity luokka
     * @param   speed   Vector2 kuvaava Entity nopeus
     *
     * @param   pos   Vector2 kuvaava Entity paikka
     *
     * @param   width   int kuvaava Entity leveys
     *
     * @param   height   int kuvaava Entity korkeus
     */
    public Entity(Vector2 speed, Vector2 pos, int width, int height) {
        this.speed = speed;
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    /**
     * Metodi joka antaa Entity paikan Vector2 tyyppinä
     *
     * @return palauttaa position
     */
    public Vector2 getPos() {
        return pos;
    }

    /**
     * Metodi joka antaa Entity nopeuden Vector2 tyyppinä
     *
     * @return palauttaa nopeuden
     */
    public Vector2 getSpeed() {
        return speed;
    }

    /**
     * Metodi joka asettaa uuden nopeuden
     *
     * @param   newSpeed kuvaa uusi nopeus
     */
    public void setSpeed(Vector2 newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * Metodi joka asettaa uuden paikan
     *
     * @param newPosition kuvaa uusi paikka
     */
    public void setPosition(Vector2 newPosition) {
        this.pos = newPosition;
    }

    /**
     * Metodi joka antaa Entity leveys
     *
     * @return palauttaa leveyden
     */
    public int getWidth() {
        return width;
    }

    /**
     * Metodi joka antaa Entity pituus
     *
     * @return palauttaa pituuden
     */
    public int getHeight() {
        return height;
    }
}
