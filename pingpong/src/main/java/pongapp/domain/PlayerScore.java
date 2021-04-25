package pongapp.domain;

public class PlayerScore implements Comparable<PlayerScore> {

    private final String name;
    private final Integer score;

    public PlayerScore(final String name, final Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public int compareTo(final PlayerScore o) {
        return score.compareTo(o.score);
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof PlayerScore)) {
            return false;
        }

        PlayerScore other = (PlayerScore) obj;
        return name.equals(other.name) && score.equals(other.score);
    }
}
