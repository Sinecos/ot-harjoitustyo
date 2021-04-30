package pongapp.domain;

public class Vector2 {

    /**
     * Vector2 x arvo
     */
    public float x;

    /**
     * Vector2 y arvo
     */
    public float y;

    /**
     * Metodi alustaa luokan Vector2
     *
     * @param   x   Vector2 x arvo
     *
     * @param   y    Vector2 y arvo
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metodi joka vertailee toisen Vector2 luokan kanssa ja palauttaa jos kummatkin Vector2 ovat yhtäsuuria
     *
     * @param   other   Vertailu Vector2 luokka
     *
     * @return jos kaksi Vector2 luokkaa ovat yhtäsuuria, palauttaa true, else false
     */
    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }
}
