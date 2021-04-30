package pongapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Graphics {

    /**
     * Luokka GraphicsContext
     */
    public GraphicsContext gc;

    /**
     * Alustaa Graphics luokan
     * @param   gc   Luokka GraphicsContext
     * @return palauttaa true jos kaikki on kunnossa ja gc on maaritelty
     */
    public boolean init(GraphicsContext gc) {

        if (gc != null) {
            this.gc = gc;
            return true;
        }

        return false;
    }

    /**
     * Metodi pirtää suorakulmion
     *
     * @param   color   Käyttäjän antama väri
     *
     * @param   posx    Käyttäjän antama suorakulmion paikka x-koordinaatti
     *
     * @param   posy   Käyttäjän antama suorakulmion paikka y-koordinaatti
     *
     * @param   width   Käyttäjän antama suorakulmion leveys
     *
     * @param   height   Käyttäjän antama suorakulmion pituus
     *
     * @return palauttaa true jos kaikki on kunnossa ja gc on maaritelty
     */
    public boolean fillRect(Color color, float posx, float posy, float width, float height) {
        if (gc != null && color != null) {
            gc.setFill(color);
            gc.fillRect(posx, posy, width, height);
            return true;
        }

        return false;
    }

    /**
     * Metodi pirtää tekstin
     *
     * @param   color   Käyttäjän antama väri
     *
     * @param   text  Käyttäjän antama teksti jota piirretään
     *
     * @param   posX   Käyttäjän antama tekstin paikka x-koordinaatti
     *
     * @param   posY   Käyttäjän antama tekstin paikka y-koordinaatti
     *
     * @return palauttaa true jos kaikki on kunnossa ja gc on maaritelty seka Color on maaritelty
     */
    public boolean fillText(Color color, String text, float posX, float posY) {

        if (gc != null && color != null && text != null) {
            gc.setFont(Font.font("Tahoma", 30));
            gc.setFill(color);
            gc.fillText(text, posX, posY);
            return true;
        }

        return false;
    }

    /**
     * Metodi joka asettaa fontti värin ja koko
     *
     * @param   color   Käyttäjän antama väri
     *
     * @param   size  Käyttäjän antama fontti koko
     *
     * @return palauttaa true jos kaikki on kunnossa ja gc on maaritelty seka Color on maaritelty
     */
    public boolean setFont(Color color, float size) {
        if (gc != null && color != null) {
            gc.setFill(color);
            gc.setFont(Font.font(size));
            return true;
        }

        return false;
    }
}
