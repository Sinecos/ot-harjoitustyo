package pongapp.domain;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import java.awt.*;

public class GraphicsTest {

    Graphics graph;
    Canvas canvas;
    StackPane stack;
    GraphicsContext gc;
    Color color;
    float a = 1f;
    float b = 1f;
    float c = 1f;
    float d = 1f;

    @Before
    public void setUp() throws Exception {
        graph = new Graphics();
        canvas = new Canvas(640, 480);
        stack = new StackPane(canvas);
        gc = canvas.getGraphicsContext2D();
        color = Color.CORAL;
        graph.gc = gc;
    }

    @Test
    public void initTest(){
        assertTrue(graph.init(gc));
    }
    @Test
    public void initTestFail(){
        assertFalse(graph.init(null));
    }

    @Test
    public void fillRectTest(){
        assertTrue(graph.fillRect(color, a, b,c,d));
    }

    @Test
    public void fillRectTestFail() {
        assertFalse(graph.fillRect(null, a, b,c,d));
    }

    @Test
    public void fillTextTest(){
        assertTrue(graph.fillText(color, "testing", a, b));
    }

    @Test
    public void fillTextTestFail() {
        graph.gc = null;
        assertFalse(graph.fillText(color, "null", a, b));
    }

    @Test
    public void setFontTest(){
        assertTrue(graph.setFont(color, 10));
    }

    @Test
    public void setFontTestFail(){
        assertFalse(graph.setFont(null, 10));
    }


}