package pongapp.domain;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class EntityTest {


    @Test
    public void entityNotNull(){
        Entity player1 = new Entity(new Vector2(10, 10), new Vector2(1, 20), 14, 30);
        assertNotNull(player1);
    }

    @Test
    public void entityPositionNotNull(){
        Entity player1 = new Entity(new Vector2(10, 10), new Vector2(1, 20), 14, 30);
        assertNotNull(player1.Pos());
    }

    @Test
    public void entityPositionCorrect(){
        Entity player1 = new Entity(new Vector2(10f, 10f), new Vector2(1, 20), 14, 30);

        if(new Vector2(10f, 10f).equals(player1.Pos())){
            assertTrue(true);
        }
    }

    @Test
    public void enitySpeedCorrect(){
        Entity player1 = new Entity(new Vector2(10f, 10f), new Vector2(1f, 20f), 14, 30);

        if(new Vector2(1f, 20f).equals(player1.Speed())){
            assertTrue(true);
        }
    }

    @Test
    public void widthAndHeightCorrect(){
        Entity player1 = new Entity(new Vector2(10f, 10f), new Vector2(1f, 20f), 14, 30);

        if(14 == player1.Width() && 30 == player1.Height()){
            assertTrue(true);
        }
    }
}