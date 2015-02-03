
package miinasokkelo.logiikka;

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
                if (sijaintiY - 1 < 0) {
                    break;
                }
                paivitaEdellinenSijainti();
                sijaintiY--;
                break;

            case "yläoikea":
                if (sijaintiY - 1 < 0 || sijaintiX + 1 > pelialue.getKoko() - 1) {
                    break;
                }
                paivitaEdellinenSijainti();
                sijaintiY--;
                sijaintiX++;
                break;

            case "oikea":
                if (sijaintiX + 1 > pelialue.getKoko() - 1) {
                    break;
                }
                paivitaEdellinenSijainti();
                sijaintiX++;
                break;

            case "alaoikea":
                if (sijaintiY + 1 > pelialue.getKoko() - 1 || sijaintiX + 1 > pelialue.getKoko() - 1) {
                    break;
                }
                paivitaEdellinenSijainti();
                sijaintiY++;
                sijaintiX++;
                break;

            case "alas":
                if (sijaintiY + 1 > pelialue.getKoko() - 1) {
                    break;
                }
                paivitaEdellinenSijainti();
                sijaintiY++;
                break;

            case "alavasen":
                if (sijaintiY + 1 > pelialue.getKoko() - 1 || sijaintiX - 1 < 0) {
                    break;
                }
                paivitaEdellinenSijainti();
                sijaintiY++;
                sijaintiX--;
                break;

            case "vasen":
                if (sijaintiX - 1 < 0) {
                    break;
                }
                paivitaEdellinenSijainti();
                sijaintiX--;
                break;

            case "ylävasen":
                if (sijaintiY - 1 < 0 || sijaintiX - 1 < 0) {
                    break;
                }
                paivitaEdellinenSijainti();
                sijaintiY--;
                sijaintiX--;
                break;
        }
        paivitaPelaajanSijainti();
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
}
