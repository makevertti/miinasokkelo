
package miinasokkelo;

import javax.swing.JFrame;
import miinasokkelo.kayttoliittyma.GraafinenPelialue;
import miinasokkelo.logiikka.*;

public class Miinasokkelo {
    private static int pelialueenKoko = 20;
    
    public static void main(String[] args) {
        luoGrafiikat();
        
        Pelialue pelialue = new Pelialue(pelialueenKoko);
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

    private static void luoGrafiikat() {
        GraafinenPelialue graafinenPelialue = new GraafinenPelialue(pelialueenKoko);
        JFrame ikkuna = new JFrame("miinasokkelo");
        ikkuna.add(graafinenPelialue);
        ikkuna.setSize(800, 800);
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true);    
    }
}
