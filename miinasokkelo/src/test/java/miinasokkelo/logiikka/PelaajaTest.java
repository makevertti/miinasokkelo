
package miinasokkelo.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PelaajaTest {
    Pelaaja pelaaja;
    Pelaaja pelaaja2;
    Pelaaja pelaaja3;
    Pelialue pelialue;
    Pelialue pelialue2;
    Pelialue pelialue3;
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja(2, 5);
        pelaaja2 = new Pelaaja(0, 0);
        pelaaja3 = new Pelaaja(0, 0);
        
        pelialue = new Pelialue(10, 0, pelaaja, false);
        pelialue2 = new Pelialue(10, 98, pelaaja2, false);
        pelialue3 = new Pelialue(1, 0, pelaaja3, false);
    }
    
    @Test
    public void pelaajaLuodaanOikeaanSijaintiin() {
        assertEquals(2, pelaaja.getX());
        assertEquals(5, pelaaja.getY());
    }
    
    @Test
    public void liikkuminenYlosToimii() {
        pelaaja.liiku(0, -1);
        
        assertEquals(4, pelaaja.getY());
    }
    
    @Test
    public void liikkuminenYlosToimiiOltaessaYlaReunassa() {
        pelaaja3.liiku(0, -1);
        
        assertEquals(0, pelaaja3.getY());
    }
    
    @Test
    public void liikkuminenYlaoikealleToimii() {
        pelaaja.liiku(1, -1);
        
        assertEquals(3, pelaaja.getX());
        assertEquals(4, pelaaja.getY());
    }
    
    @Test
    public void liikkuminenYlaoikealleToimiiOltaessaReunassa() {
        pelaaja3.liiku(1, -1);
        
        assertEquals(0, pelaaja3.getX());
        assertEquals(0, pelaaja3.getY());
    }
    
    @Test
    public void liikkuminenOikealleToimii() {
        pelaaja.liiku(1, 0);
        
        assertEquals(3, pelaaja.getX());
    }
    
    @Test
    public void liikkuminenOikealleToimiiOltaessaReunassa() {
        pelaaja3.liiku(1, 0);
        
        assertEquals(0, pelaaja3.getX());
    }
    
    @Test
    public void liikkuminenAlaoikealleToimii() {
        pelaaja.liiku(1, 1);
        
        assertEquals(3, pelaaja.getX());
        assertEquals(6, pelaaja.getY());
    }
    
    @Test
    public void liikkuminenAlaoikealleToimiiOltaessaReunassa() {
        pelaaja3.liiku(1, 1);
        
        assertEquals(0, pelaaja3.getX());
        assertEquals(0, pelaaja3.getY());
    }
    
    @Test
    public void liikkuminenAlasToimii() {
        pelaaja.liiku(0, 1);
        
        assertEquals(6, pelaaja.getY());
    }
    
    @Test
    public void liikkuminenAlasToimiiOltaessaReunassa() {
        pelaaja3.liiku(0, 1);
        
        assertEquals(0, pelaaja3.getY());
    }
    
    @Test
    public void liikkuminenAlavasemmalleToimii() {
        pelaaja.liiku(-1 , 1);
        
        assertEquals(1, pelaaja.getX());
        assertEquals(6, pelaaja.getY());
    }
    
    @Test
    public void liikkuminenAlavasemmalleToimiiOltaessaReunassa() {
        pelaaja3.liiku(-1, 1);
        
        assertEquals(0, pelaaja3.getY());
        assertEquals(0, pelaaja3.getX());
    }
    
    @Test
    public void liikkuminenVasemmalleToimii() {
        pelaaja.liiku(-1 ,0);
        
        assertEquals(1, pelaaja.getX());
    }
    
    @Test
    public void liikkuminenVasemmalleToimiiOltaessaReunassa() {
        pelaaja3.liiku(-1, 0);
        
        assertEquals(0, pelaaja3.getX());
    }
    
    @Test
    public void liikkuminenYlavasemmalleToimii() {
        pelaaja.liiku(-1, -1);
        
        assertEquals(1, pelaaja.getX());
        assertEquals(4, pelaaja.getY());
    }
    
    @Test
    public void liikkuminenYlavasemmalleToimiiOltaessaReunassa() {
        pelaaja3.liiku(-1, -1);
        
        assertEquals(0, pelaaja3.getX());
        assertEquals(0, pelaaja3.getY());
    }
    
    @Test
    public void miinaanTormaamisenJalkeenEiVoiLiikkua() {
        pelaaja2.liiku(1, 0);
        pelaaja2.liiku(1, 0);
        pelaaja2.liiku(0, 1);
        
        assertEquals(1, pelaaja2.getX());
    }
}