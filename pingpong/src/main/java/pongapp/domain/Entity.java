package pongapp.domain;

public class Entity {
    private Vector2 speed;
    private Vector2 pos;
    private int width;
    private int height;

    public Entity(Vector2 speed, Vector2 pos, int width, int height) {
        this.speed = speed;
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 newSpeed) {
        this.speed = newSpeed;
    }

    public void setPosition(Vector2 newPosition) {
        this.pos = newPosition;
    }

    public int getWidth() {
        return width;
    }

    public int getHeightHeight() {
        return height;
    }
}
