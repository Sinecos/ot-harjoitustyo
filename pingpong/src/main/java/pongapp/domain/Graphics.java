package pongapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Graphics {

    private GraphicsContext gc;

    public void init(GraphicsContext gc) {
        this.gc = gc;
    }

    public void fillRect(Color color, float posx, float posy, float width, float height) {
        gc.setFill(color);
        gc.fillRect(posx, posy, width, height);
    }

    public void fillText(Color color, String text, float posX, float posY) {
        gc.setFont(Font.font("Tahoma", 30));
        gc.setFill(color);
        gc.fillText(text, posX, posY);
    }

    public void setFont(Color color, float size) {
        gc.setFill(color);
        gc.setFont(Font.font(size));
    }
}
