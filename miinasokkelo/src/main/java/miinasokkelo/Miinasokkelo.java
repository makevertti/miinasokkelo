
package miinasokkelo;

import miinasokkelo.logiikka.*;

/**
 * Main metodin sisältävä luokka, joka käynnistää pelin 
 */
public class Miinasokkelo {
    private static int pelialueenKoko = 20;
    private static int miinat = 10;
    
    public static void main(String[] args) {
        Pelialue pelialue = new Pelialue(pelialueenKoko, miinat, new Pelaaja(0, 0), true);
        pelialue.lisaaNappaimistonkuuntelija();
    }
}