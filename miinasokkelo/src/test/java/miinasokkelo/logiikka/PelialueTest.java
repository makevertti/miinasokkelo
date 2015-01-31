
package miinasokkelo.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PelialueTest {
    
    Pelialue pelialue;
    Pelaaja pelaaja;
    
    @Before
    public void setUp() {
        pelialue = new Pelialue(5, 0, false);
        pelaaja = new Pelaaja(pelialue, 0, 0);
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
}
