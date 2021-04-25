package pongapp.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerScoreTest {

    @Test
    public void eqPlayerNameAndScr() {
        PlayerScore ps1 = new PlayerScore("Kalle", 10);
        PlayerScore ps2 = new PlayerScore("Kalle", 10);

        assertTrue(ps1.equals(ps2));
    }

    @Test
    public void nonEqlPlayerScore() {
        PlayerScore ps1 = new PlayerScore("Kalle", 10);
        PlayerScore ps2 = new PlayerScore("Kalle", 5);

        assertFalse(ps1.equals(ps2));
    }

    @Test
    public void differentTypeTest(){
        PlayerScore ps1 = new PlayerScore("Kalle", 10);
        Object o = new Object();
        assertFalse(ps1.equals(o));
    }

    @Test
    public void getNameTest(){
        PlayerScore ps1 = new PlayerScore("Kalle", 10);
        assertTrue(ps1.getName() == "Kalle");
    }

    @Test
    public void getScoreTest(){
        PlayerScore ps1 = new PlayerScore("Kalle", 10);
        assertTrue(ps1.getScore() == 10);
    }

    @Test
    public void compareScore(){
        PlayerScore ps1 = new PlayerScore("Matti", 10);
        PlayerScore ps2 = new PlayerScore("Matti", 10);
        //if same score then 0
        assertTrue(ps1.compareTo(ps2) == 0);

    }
}