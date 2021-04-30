package pongapp.domain;

public class PlayerScore implements Comparable<PlayerScore> {

    private final String name;
    private final Integer score;

    /**
     * Alustaa PlayerScore luokkaa
     *
     * @param name Pelaajn nimi
     *
     * @param score Pelaajn score
     */
    public PlayerScore(final String name, final Integer score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Metodi jolla kutsutaan pelaajan nimeä
     *
     * @return palauttaa nimen
     */
    public String getName() {
        return name;
    }

    /**
     * Metodi jolla kutsutaan pelaajan score
     *
     * @return palauttaa score
     */
    public Integer getScore() {

        return score;
    }

    /**
     * Metodi jota käytetään toisen PlayerScore luokan vertailussa.
     *
     * Tätä käytetään kun halutaan vertaila kaksi eri PlayerScore luokan score arvoa ja valita se joka on suurempi.
     *
     * @param o PlayerScore luokka jota verrataan toiseen PlayerScore luokkaan
     *
     * @return palauttaa suurempi score arvon
     */
    @Override
    public int compareTo(final PlayerScore o) {
        return score.compareTo(o.score);
    }

    /**
     * Metodi käytetään kahden PlayerScore luokan vertailussa.
     * *
     * @param obj Verrattava luokka
     *
     * @return palauttaa true arvon jos verrattava luokka on saman arvoinen, muuten false
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof PlayerScore)) {
            return false;
        }

        PlayerScore other = (PlayerScore) obj;
        return name.equals(other.name) && score.equals(other.score);
    }
}
