
package miinasokkelo.logiikka;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kuvaa pelaajan sijaintia pelialueella. Sisältää metodeja pelaajan liikkumiseen liittyen
 */
public class Pelaaja {

    private Pelialue pelialue;
    private int sijaintiX;
    private int sijaintiY;
    private int edellinenX;
    private int edellinenY;
    private boolean osunutMiinaan;

    /**
     * Luo pelaajan määriteltyyn sijaintiin
     *
     * @param x Pelaajan x-koordinaatti
     * @param y Pelaajan y-koordinaatti
     */
    public Pelaaja(int x, int y) {
        sijaintiX = x;
        sijaintiY = y;
        edellinenX = x;
        edellinenY = y;
        osunutMiinaan = false;
    }

    /**
     * Siirtää pelaajaa annettuun suuntaan
     *
     * @param xMuutos Pelaajan sijainnin muutos x-suunnassa
     * @param yMuutos Pelaajan sijainnin muutos y-suunnassa
     */
    public void liiku(int xMuutos, int yMuutos) {
        if (osunutMiinaan) {
            return;
        }
        if (pelialue.onPelialueella(getX() + xMuutos, getY() + yMuutos)) {
            paivitaEdellinenSijainti();
            sijaintiX += xMuutos;
            sijaintiY += yMuutos;
            pelialue.paivitaPelaajanSijainti(edellinenX, edellinenY, sijaintiX, sijaintiY);
        }
    }

    private void paivitaEdellinenSijainti() {
        edellinenY = sijaintiY;
        edellinenX = sijaintiX;
    }

    /**
     * Poistaa näppäimistöohjauksen käytöstä
     */
    public void poistaOhjaus() {
        osunutMiinaan = true;
    }

    /**
     * Aloittaa pelin uudelleen
     */
    public void uusiPeli() {
        try {
            Runtime.getRuntime().exec("java -jar miinasokkelo-1.0.jar");
        } catch (IOException ex) {
            Logger.getLogger(Pelaaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public int getX() {
        return this.sijaintiX;
    }

    public int getY() {
        return this.sijaintiY;
    }

    public void setPelialue(Pelialue pelialue) {
        this.pelialue = pelialue;
    }
}
