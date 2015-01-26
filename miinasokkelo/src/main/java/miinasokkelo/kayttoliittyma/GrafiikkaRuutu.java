
package miinasokkelo.kayttoliittyma;

import java.awt.Color;
import javax.swing.JPanel;

public class GrafiikkaRuutu extends JPanel {
    public GrafiikkaRuutu() {
        this.setBackground(Color.LIGHT_GRAY);
    }
    
    public void vaihdaRuudunTyyppi(String tyyppi) {
        switch(tyyppi) {
            case "tuntematon":
                this.setBackground(Color.LIGHT_GRAY);
                break;
                
            case "tyhjä":
                this.setBackground(Color.WHITE);
                break;
                
            case "miina":
                this.setBackground(Color.RED);
                break;
                
            case "pelaaja":
                this.setBackground(Color.GREEN);
        }
    }
}
