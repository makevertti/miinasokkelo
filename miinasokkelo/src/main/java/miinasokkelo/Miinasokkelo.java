
package miinasokkelo;

import miinasokkelo.logiikka.*;

public class Miinasokkelo {
    public static void main(String[] args) {
        Pelialue pelialue = new Pelialue(5);
        Pelaaja pelaaja = new Pelaaja(pelialue, 0, 0);
        pelialue.tulosta();
        pelaaja.liiku("oikea");
        pelialue.tulosta();
        pelaaja.liiku("oikea");
        pelialue.tulosta();
    }
}