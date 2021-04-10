package pongapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Graphics {

    public void FillRect(GraphicsContext gc, Color color, float posx, float posy, float Width, float Height){
        gc.setFill(color);
        gc.fillRect(posx, posy, Width, Height);
    }

    public void FillText(GraphicsContext gc, Color color, String text, float posX, float posY){
        gc.setFill(color);
        gc.fillText(text, posX, posY);
    }

    public void SetFont(GraphicsContext gc, Color color, float size){
        gc.setFill(color);
        gc.setFont(Font.font(size));
    }
}
