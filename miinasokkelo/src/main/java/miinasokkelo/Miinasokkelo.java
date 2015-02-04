
package miinasokkelo;

import miinasokkelo.logiikka.Pelialue;
import miinasokkelo.logiikka.Pelaaja;

/**
 * Main metodin sisältävä luokka, joka käynnistää pelin 
 */
public class Miinasokkelo {
    private static int pelialueenKoko = 30;
    private static int miinat = 80;
    
    public static void main(String[] args) {
        Pelialue pelialue = new Pelialue(pelialueenKoko, miinat, new Pelaaja(0, 0), true);
        pelialue.setNappaimistonkuuntelija();
    }
}