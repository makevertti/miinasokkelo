
package miinasokkelo;

import javax.swing.SwingUtilities;
import miinasokkelo.kayttoliittyma.GraafinenPelialue;
import miinasokkelo.logiikka.*;

public class Miinasokkelo {
    public static void main(String[] args) {
        GraafinenPelialue graafinenPelialue = new GraafinenPelialue();
        SwingUtilities.invokeLater(graafinenPelialue);
        
        Pelialue pelialue = new Pelialue(5);
        Pelaaja pelaaja = new Pelaaja(pelialue, 0, 0);
        
        System.out.println("Pelialue");
        pelialue.tulosta();
        System.out.println("");
        
        System.out.println("Avatut Ruudut");
        pelialue.tulostaAvatutRuudut();
        System.out.println("");
        
        pelaaja.liiku("oikea");
        System.out.println("Pelialue");
        pelialue.tulosta();
        System.out.println("");
        
        System.out.println("Avatut Ruudut");
        pelialue.tulostaAvatutRuudut();
        System.out.println("");
        
        pelaaja.liiku("oikea");
        System.out.println("Pelialue");
        pelialue.tulosta();
        System.out.println("");
        
        System.out.println("Avatut Ruudut");
        pelialue.tulostaAvatutRuudut();
        System.out.println("");
    }
}
