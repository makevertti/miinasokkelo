
package miinasokkelo.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PelialueTest {
    
    Pelaaja pelaaja;
    Pelaaja pelaaja2;
    Pelaaja pelaaja3;
    Pelialue pelialue;
    Pelialue pelialue2;
    Pelialue pelialue3;

    @Before
    public void setUp() {
        pelaaja = new Pelaaja(0, 0);
        pelaaja2 = new Pelaaja(0, 0);
        pelaaja3 = new Pelaaja(0, 0);
        pelialue = new Pelialue(5, 0, pelaaja, false);
        pelialue2 = new Pelialue(20, 50, pelaaja2, false);
        pelialue3 = new Pelialue(2, 2, pelaaja3, true);
    }
    
    @Test
    public void pelialueLuodaanOikeanKokoisena() {
        assertEquals(5, pelialue.getKoko());
    }
    
    @Test
    public void avatutRuudutPaivittyvat() {
        pelaaja.liiku(1, 0);
        pelaaja.liiku(1, 1);
        
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
        pelaaja.liiku(1, 1);
        pelaaja.liiku(1, 1);
        pelaaja.liiku(1, 1);
        pelaaja.liiku(1, 1);
        pelaaja.liiku(0, -1);
        
        assertEquals(4, pelaaja.getX());
        assertEquals(4, pelaaja.getY());
    }
    
    @Test
    public void onkoPelaajaAlueellaToimii() {
        assertEquals(true, pelialue.onPelialueella(1, 3));
    }
    
    @Test
    public void onkoPelaajaAlueellaToimii2() {
        assertEquals(false, pelialue.onPelialueella(9, 3));
    }
    
    @Test
    public void getMiinatRuudunYmparillaToimii() {
        assertEquals(0, pelialue.getMiinatRuudunYmparilla(2, 4));
    }
    
    @Test
    public void getMiinatRuudunYmparillaToimii2() {
        assertEquals(2, pelialue3.getMiinatRuudunYmparilla(0, 0));
    }
    
    @Test
    public void getPelaajaToimii() {
        assertEquals(pelaaja, pelialue.getPelaaja());
    }
}