
package miinasokkelo;

import miinasokkelo.logiikka.*;

public class Miinasokkelo {
    private static int pelialueenKoko = 20;
    
    public static void main(String[] args) throws InterruptedException {
        Pelialue pelialue = new Pelialue(pelialueenKoko);
        Pelaaja pelaaja = new Pelaaja(pelialue, 0, 0);
        
        for (int i = 0; i < 15; i++) {
            Thread.sleep(500);
            pelaaja.liiku("oikea");
        }
    }
}