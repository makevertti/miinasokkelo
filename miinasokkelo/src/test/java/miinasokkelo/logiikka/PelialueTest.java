
package miinasokkelo.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;

public class PelialueTest {
    
    @Test
    public void pelialueLuodaanOikeanKokoisena() {
        Pelialue pelialue = new Pelialue(5);
        assertEquals(5, pelialue.getKoko());
    }
}
