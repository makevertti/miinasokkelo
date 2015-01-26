
package miinasokkelo;

import miinasokkelo.logiikka.*;

public class Miinasokkelo {
    private static int pelialueenKoko = 20;
    
    public static void main(String[] args) {
        Pelialue pelialue = new Pelialue(pelialueenKoko);
        Pelaaja pelaaja = new Pelaaja(pelialue, 0, 0);
        pelialue.lisaaPelaaja(pelaaja);
        pelialue.lisaaNappaimistonkuuntelija();
    }
}