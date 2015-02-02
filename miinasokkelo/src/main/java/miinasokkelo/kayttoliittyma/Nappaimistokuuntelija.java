
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
    
    public Nappaimistokuuntelija(Pelaaja p) {
        pelaaja = p;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent nappi) {
        switch(nappi.getKeyCode()) {
            case KeyEvent.VK_NUMPAD1:
                pelaaja.liiku("alavasen");
                break;
                
            case KeyEvent.VK_NUMPAD2:
                pelaaja.liiku("alas");
                break;
                
            case KeyEvent.VK_NUMPAD3:
                pelaaja.liiku("alaoikea");
                break;
                
            case KeyEvent.VK_NUMPAD4:
                pelaaja.liiku("vasen");
                break;
                
            case KeyEvent.VK_NUMPAD6:
                pelaaja.liiku("oikea");
                break;
                
            case KeyEvent.VK_NUMPAD7:
                pelaaja.liiku("ylävasen");
                break;
                
            case KeyEvent.VK_NUMPAD8:
                pelaaja.liiku("ylös");
                break;
                
            case KeyEvent.VK_NUMPAD9:
                pelaaja.liiku("yläoikea");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
