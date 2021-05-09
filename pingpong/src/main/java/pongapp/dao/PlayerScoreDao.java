package pongapp.dao;
import pongapp.domain.PlayerScore;
import java.util.List;

public interface PlayerScoreDao {

    PlayerScore create(PlayerScore playerScore) throws Exception;
    List<PlayerScore> getAllPlayers();
}