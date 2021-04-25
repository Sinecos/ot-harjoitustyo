package pongapp.dao;

import pongapp.domain.PlayerScore;

import java.io.File;
import java.io.FileWriter;
import java.util.*;


public class FileScoreDao implements PlayerScoreDao {

    final List<PlayerScore> playerScores;

    private String file;
    public boolean allowAddNewScore;

    public FileScoreDao(String file) throws Exception {
        playerScores = new ArrayList<>();
        this.file = file;

        try {
            Scanner reader = new Scanner(new File(file));

            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(";");
                int score = Integer.parseInt(data[1]);

                PlayerScore p = new PlayerScore(data[0], score);
                playerScores.add(p);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    private void saveData() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (PlayerScore player : playerScores) {
                writer.write(player.getName() + ";" + String.valueOf(player.getScore()) + "\n");
            }
        }
    }

    @Override
    public PlayerScore create(PlayerScore playerScore) throws Exception {
        playerScores.add(playerScore);
        saveData();
        return playerScore;
    }

    @Override
    public List<PlayerScore> getAllPlayers() {

        if (playerScores.size() == 0) {
            return null;
        }

        Collections.sort(playerScores, Collections.reverseOrder());
        return playerScores;
    }

    public void sortScores() {
        Collections.sort(playerScores, Collections.reverseOrder());
    }
}
