
package miinasokkelo.logiikka;

/**
 * Sisältää pelialueen rajoihin 
 * liittyviä metodeita
 */
public class Aluetarkistaja {
    
    private Pelialue pelialue;
    
    /**
     * Luo aluetarkistajan
     * 
     * @param alue  Pelialue johon aluetakistaja kytketään 
     */
    public Aluetarkistaja(Pelialue alue) {
        pelialue = alue;
    }
    
    /**
     * Tarkistaa onko ruutu x, y pelialueeella
     * 
     * @param x     Tarkistettavan ruudun x-koordinaatti
     * @param y     Tarkistettavan ruudun y-koordinaatti
     * @return      Palauttaa true jos alueella, muuten false
     */
    public boolean onPelialueella(int x, int y) {
        if (x < 0 || y < 0 || x > pelialue.getKoko() - 1 || y > pelialue.getKoko() - 1) {
            return false;
        }
        return true;
    } 
    
    /**
     * Käytetään ruutujen avaamisen yhteydessä
     * 
     * @param x
     * @param y 
     */
    public void tarkistaEttaRuutuOnPelialueella(int x, int y) {
        if (onPelialueella(x, y + 1)) {
            pelialue.avaaRuutu(x, y + 1);
        }
        if (onPelialueella(x + 1, y)) {
            pelialue.avaaRuutu(x + 1, y);
        }
        if (onPelialueella(x, y - 1)) {
            pelialue.avaaRuutu(x, y - 1);
        }
        if (onPelialueella(x + 1, y)) {
            pelialue.avaaRuutu(x + 1, y);
        }
        if (onPelialueella(x + 1, y + 1)) {
            pelialue.avaaRuutu(x + 1, y + 1);
        }
        if (onPelialueella(x - 1, y - 1)) {
            pelialue.avaaRuutu(x - 1, y - 1);
        }
        if (onPelialueella(x - 1, y + 1)) {
            pelialue.avaaRuutu(x - 1, y + 1);
        }
        if (onPelialueella(x + 1, y - 1)) {
            pelialue.avaaRuutu(x + 1, y - 1);
        }
    }
}