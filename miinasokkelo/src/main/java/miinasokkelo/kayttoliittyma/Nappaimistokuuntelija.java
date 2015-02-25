
package miinasokkelo.kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import miinasokkelo.logiikka.Pelaaja;

/**
 * Antaa Pelaaja -luokalle liikkumiskomentoja
 * näppäimistösyötteen perusteella
 */
public class Nappaimistokuuntelija implements KeyListener {
    private Pelaaja pelaaja;
    
    /**
     * Kytkee näppäimistökuuntelijan pelaajaan
     * 
     * @param p     pelaaja jota halutaan ohjata
     */
    public Nappaimistokuuntelija(Pelaaja p) {
        pelaaja = p;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent nappi) {
        switch (nappi.getKeyCode()) {
            case KeyEvent.VK_NUMPAD1:
                pelaaja.liiku(-1, 1);
                break;
            case KeyEvent.VK_Z:
                pelaaja.liiku(-1, 1);
                break;
                
            case KeyEvent.VK_NUMPAD2:
                pelaaja.liiku(0, 1);
                break;
            case KeyEvent.VK_X:
                pelaaja.liiku(0, 1);
                break;
                
            case KeyEvent.VK_NUMPAD3:
                pelaaja.liiku(1, 1);
                break;
            case KeyEvent.VK_C:
                pelaaja.liiku(1, 1);
                break;
                
            case KeyEvent.VK_NUMPAD4:
                pelaaja.liiku(-1, 0);
                break;
            case KeyEvent.VK_A:
                pelaaja.liiku(-1, 0);
                break;
                
            case KeyEvent.VK_NUMPAD6:
                pelaaja.liiku(1, 0);
                break;
            case KeyEvent.VK_D:
                pelaaja.liiku(1, 0);
                break;
                
            case KeyEvent.VK_NUMPAD7:
                pelaaja.liiku(-1, -1);
                break;
            case KeyEvent.VK_Q:
                pelaaja.liiku(-1, -1);
                break;
                
            case KeyEvent.VK_NUMPAD8:
                pelaaja.liiku(0, -1);
                break;
            case KeyEvent.VK_W:
                pelaaja.liiku(0, -1);
                break;
                
            case KeyEvent.VK_NUMPAD9:
                pelaaja.liiku(1, -1);
                break;
            case KeyEvent.VK_E:
                pelaaja.liiku(1, -1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
