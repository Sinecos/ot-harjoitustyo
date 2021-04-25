package pongapp.dao;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.After;
import org.junit.Before;
import pongapp.domain.PlayerScore;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File userFile;
    ScoreDao dao;

    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("playerScoreTestingFile.txt");

        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            Integer jokuNumero = 10;
            file.write("Matti;"+ String.valueOf(jokuNumero)+"\n");

        }

        dao = new ScoreDao(userFile.getAbsolutePath());
    }

    @Test
    public void readPlayerScore() {
        List<PlayerScore> players = dao.getAllPlayers();
        assertEquals(1, players.size());
        PlayerScore player = players.get(0);
        assertEquals("Matti", player.getName());
        assertEquals("10", String.valueOf(player.getScore()));
    }

    @Test
    public void savedPlayerFound() throws Exception {

        List<PlayerScore> players = dao.getAllPlayers();

        PlayerScore newPlayer = new PlayerScore("Nuur", 12);
        dao.create(newPlayer);

        PlayerScore player = players.get(1);

        assertEquals("Nuur", player.getName());
        assertEquals("12", String.valueOf(player.getScore()));
    }


}