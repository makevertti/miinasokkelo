
package miinasokkelo.logiikka;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kuvaa pelaajan sijaintia pelialueella.
 * Sisältää metodeja pelaajan liikkumiseen liittyen 
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
     * @param x     Pelaajan x-koordinaatti 
     * @param y     Pelaajan y-koordinaatti
     */
    public Pelaaja(int x, int y) {
        sijaintiX = x;
        sijaintiY = y;
        edellinenX = x;
        edellinenY = y;
        osunutMiinaan = false;
    }

    /**
     * Siirtää pelaajaa yhden ruudun annettuun suuntaan
     * 
     * @param   Suunta johon pelaajaa liikutetaan 
     *          vaihtoehdot: "ylös", "yläoikea", "oikea",
     *          "alaoikea", "alas", "alavasen", "vasen", "ylävasen"
     */
    public void liiku(String suunta) {
        if (osunutMiinaan) {
            return;
        }
        switch (suunta) {
            case "ylös":
                liikuYlos();
                break;
            case "yläoikea":
                liikuYlaOikealle();
                break;
            case "oikea":
                liikuOikealle();
                break;
            case "alaoikea":
                liikuAlaOikealle();
                break;
            case "alas":
                liikuAlas();
                break;
            case "alavasen":
                liikuAlaVasemmalle();
                break;
            case "vasen":
                liikuVasemmalle();
                break;
            case "ylävasen":
                liikuYlaVasemmalle();
                break;
        }
        paivitaPelaajanSijainti();
    }

    private void liikuYlaVasemmalle() {
        if (sijaintiY - 1 < 0 || sijaintiX - 1 < 0) {
            return;
        }
        paivitaEdellinenSijainti();
        sijaintiY--;
        sijaintiX--;
    }

    private void liikuVasemmalle() {
        if (sijaintiX - 1 < 0) {
            return;
        }
        paivitaEdellinenSijainti();
        sijaintiX--;
    }

    private void liikuAlaVasemmalle() {
        if (sijaintiY + 1 > pelialue.getKoko() - 1 || sijaintiX - 1 < 0) {
            return;
        }
        paivitaEdellinenSijainti();
        sijaintiY++;
        sijaintiX--;
    }

    private void liikuAlas() {
        if (sijaintiY + 1 > pelialue.getKoko() - 1) {
            return;
        }
        paivitaEdellinenSijainti();
        sijaintiY++;
    }

    private void liikuAlaOikealle() {
        if (sijaintiY + 1 > pelialue.getKoko() - 1 || sijaintiX + 1 > pelialue.getKoko() - 1) {
            return;
        }
        paivitaEdellinenSijainti();
        sijaintiY++;
        sijaintiX++;
    }

    private void liikuOikealle() {
        if (sijaintiX + 1 > pelialue.getKoko() - 1) {
            return;
        }
        paivitaEdellinenSijainti();
        sijaintiX++;
    }

    private void liikuYlaOikealle() {
        if (sijaintiY - 1 < 0 || sijaintiX + 1 > pelialue.getKoko() - 1) {
            return;
        }
        paivitaEdellinenSijainti();
        sijaintiY--;
        sijaintiX++;
    }

    private void liikuYlos() {
        if (sijaintiY - 1 < 0) {
            return;
        }
        paivitaEdellinenSijainti();
        sijaintiY--;
    }

    private void paivitaPelaajanSijainti() {
        pelialue.paivitaPelaajanSijainti(edellinenX, edellinenY, sijaintiX, sijaintiY);
    }

    private void paivitaEdellinenSijainti() {
        edellinenY = sijaintiY;
        edellinenX = sijaintiX;
    }

    public void poistaOhjaus() {
        osunutMiinaan = true;
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

    public void uusiPeli() {
        try {
            Runtime.getRuntime().exec("java -jar miinasokkelo-1.0.jar");
        } catch (IOException ex) {
            Logger.getLogger(Pelaaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
}
