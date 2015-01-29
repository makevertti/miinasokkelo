
package miinasokkelo;

import miinasokkelo.logiikka.*;

public class Miinasokkelo {
    private static int pelialueenKoko = 20;
    private static int miinat = 10;
    
    public static void main(String[] args) {
        Pelialue pelialue = new Pelialue(pelialueenKoko, miinat);
        Pelaaja pelaaja = new Pelaaja(pelialue, 0, 0);
        pelialue.lisaaPelaaja(pelaaja);
        pelialue.lisaaNappaimistonkuuntelija();
    }
}