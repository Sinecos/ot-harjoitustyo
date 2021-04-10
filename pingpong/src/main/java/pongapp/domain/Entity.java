package pongapp.domain;

public class Entity {
    private Vector2 speed;
    private Vector2 pos;
    private int width;
    private int height;

    public Entity(Vector2 speed, Vector2 pos, int width, int height){
        this.speed = speed;
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public Vector2 Pos(){
        return pos;
    }

    public Vector2 Speed(){
        return speed;
    }

    public int Width(){
        return width;
    }

    public int Height(){
        return height;
    }
}
