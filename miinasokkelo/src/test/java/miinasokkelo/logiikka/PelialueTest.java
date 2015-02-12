
package miinasokkelo.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PelialueTest {
    
    Pelaaja pelaaja;
    Pelaaja pelaaja2;
    Pelialue pelialue;
    Pelialue pelialue2;

    @Before
    public void setUp() {
        pelaaja = new Pelaaja(0, 0);
        pelaaja2 = new Pelaaja(0, 0);
        pelialue = new Pelialue(5, 0, pelaaja, false);
        pelialue2 = new Pelialue(20, 50, pelaaja2, false);
    }
    
    @Test
    public void pelialueLuodaanOikeanKokoisena() {
        assertEquals(5, pelialue.getKoko());
    }
    
    @Test
    public void avatutRuudutPaivittyvat() {
        pelaaja.liiku("oikea");
        pelaaja.liiku("alaoikea");
        
        assertEquals(true, pelialue.getOnkoAvattu(0, 0));
        assertEquals(true, pelialue.getOnkoAvattu(1, 0));
        assertEquals(true, pelialue.getOnkoAvattu(2, 1));
    }
    
    @Test
    public void miinojaLisataanOikeaMaara1() {
        assertEquals(0, laskeMiinat(pelialue));
    }
    
    @Test
    public void miinojaLisataanOikeaMaara2() {
        assertEquals(50, laskeMiinat(pelialue2));
    }
    
    private int laskeMiinat(Pelialue pelialue) {
        int miinat = 0;
        
        for (int i = 0; i < pelialue.getKoko(); i++) {
            for (int j = 0; j < pelialue.getKoko(); j++) {
                if (pelialue.getRuutu(j, i) == 2) {
                    miinat++;
                }                
            }
        }
        
        return miinat;
    }
    
    @Test
    public void eiMahdottomiaAsetusToimii() {
        pelialue = new Pelialue(2, 2, pelaaja, true);
        assertEquals(1, pelialue.getRuutu(0, 0));
        assertEquals(4, pelialue.getRuutu(1, 1));
    }
    
    @Test
    public void maaliinPaaseminenPoistaaPelaajanOhjauksen() {
        pelaaja.liiku("alaoikea");
        pelaaja.liiku("alaoikea");
        pelaaja.liiku("alaoikea");
        pelaaja.liiku("alaoikea");
        pelaaja.liiku("ylÃ¶s");
        
        assertEquals(4, pelaaja.getX());
        assertEquals(4, pelaaja.getY());
    } 
}
