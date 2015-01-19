
package miinasokkelo.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PelaajaTest {
    Pelialue pelialue;
    Pelaaja pelaaja;
    Pelaaja pelaaja2;
    Pelaaja pelaaja3;
    
    @Before
    public void setUp() {
        pelialue = new Pelialue(10);
        pelaaja = new Pelaaja(pelialue, 2, 5);
        pelaaja2 = new Pelaaja(pelialue, 0, 0);
        pelaaja3 = new Pelaaja(pelialue, 9, 9);
    }
    
    @Test
    public void pelaajaLuodaanOikeaanSijaintiin() {
        assertEquals(2, pelaaja.sijaintiX);
        assertEquals(5, pelaaja.sijaintiY);
    }
    
    @Test
    public void liikkuminenYlosToimii() {
        pelaaja.liiku("ylös");
        
        assertEquals(4, pelaaja.sijaintiY);
    }
    
    @Test
    public void liikkuminenYlosToimiiOltaessaYlaReunassa() {
        pelaaja2.liiku("ylös");
        
        assertEquals(0, pelaaja2.sijaintiY);
    }
    
    @Test
    public void liikkuminenYlaoikealleToimii() {
        pelaaja.liiku("yläoikea");
        
        assertEquals(3, pelaaja.sijaintiX);
        assertEquals(4, pelaaja.sijaintiY);
    }
    
    @Test
    public void liikkuminenYlaoikealleToimiiOltaessaReunassa() {
        pelaaja3.liiku("yläoikea");
        
        assertEquals(9, pelaaja3.sijaintiX);
        assertEquals(9, pelaaja3.sijaintiY);
    }
    
    @Test
    public void liikkuminenOikealleToimii() {
        pelaaja.liiku("oikea");
        
        assertEquals(3, pelaaja.sijaintiX);
    }
    
    @Test
    public void liikkuminenOikealleToimiiOltaessaReunassa() {
        pelaaja3.liiku("oikea");
        
        assertEquals(9, pelaaja3.sijaintiX);
    }
    
    @Test
    public void liikkuminenAlaoikealleToimii() {
        pelaaja.liiku("alaoikea");
        
        assertEquals(3, pelaaja.sijaintiX);
        assertEquals(6, pelaaja.sijaintiY);
    }
    
    @Test
    public void liikkuminenAlaoikealleToimiiOltaessaReunassa() {
        pelaaja3.liiku("alaoikea");
        
        assertEquals(9, pelaaja3.sijaintiX);
        assertEquals(9, pelaaja3.sijaintiY);
    }
    
    @Test
    public void liikkuminenAlasToimii() {
        pelaaja.liiku("alas");
        
        assertEquals(6, pelaaja.sijaintiY);
    }
    
    @Test
    public void liikkuminenAlasToimiiOltaessaReunassa() {
        pelaaja3.liiku("alas");
        
        assertEquals(9, pelaaja3.sijaintiY);
    }
    
    @Test
    public void liikkuminenAlavasemmalleToimii() {
        pelaaja.liiku("alavasen");
        
        assertEquals(1, pelaaja.sijaintiX);
        assertEquals(6, pelaaja.sijaintiY);
    }
    
    @Test
    public void liikkuminenAlavasemmalleToimiiOltaessaReunassa() {
        pelaaja3.liiku("alavasen");
        
        assertEquals(9, pelaaja3.sijaintiY);
        assertEquals(9, pelaaja3.sijaintiX);
    }
    
    @Test
    public void liikkuminenVasemmalleToimii() {
        pelaaja.liiku("vasen");
        
        assertEquals(1, pelaaja.sijaintiX);
    }
    
    @Test
    public void liikkuminenVasemmalleToimiiOltaessaReunassa() {
        pelaaja2.liiku("vasen");
        
        assertEquals(0, pelaaja2.sijaintiX);
    }
    
    @Test
    public void liikkuminenYlavasemmalleToimii() {
        pelaaja.liiku("ylävasen");
        
        assertEquals(1, pelaaja.sijaintiX);
        assertEquals(4, pelaaja.sijaintiY);
    }
    
    @Test
    public void liikkuminenYlavasemmalleToimiiOltaessaReunassa() {
        pelaaja2.liiku("ylävasen");
        
        assertEquals(0, pelaaja2.sijaintiX);
        assertEquals(0, pelaaja2.sijaintiY);
    }
}
