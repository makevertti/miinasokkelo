package miinasokkelo.kayttoliittyma;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Graafisen pelialueen jokainen ruutu on tämän luokan ilmentymä
 */
public class GrafiikkaRuutu extends JPanel {
    
    /**
     * Vaihtaa ruudun tyypin
     * 
     * @param tyyppi Ruudun tyyppi. Vaihtoehdot: 
     *               "tuntematon", "tyhjä", "miina", 
     *               "pelaaja", "törmätty miina", "maali"
     */
    public void vaihdaRuudunTyyppi(String tyyppi) {
        switch (tyyppi) {
            case "tuntematon":
                this.setBackground(Color.LIGHT_GRAY);
                break;

            case "tyhjä":
                this.setBackground(Color.WHITE);
                break;

            case "miina":
                this.setBackground(Color.PINK);
                break;

            case "pelaaja":
                this.setBackground(Color.CYAN);
                break;

            case "törmätty miina":
                this.setBackground(Color.RED);
                break;

            case "maali":
                this.setBackground(Color.GREEN);
                break;
        }
    }

    /**
     * Lisää ruutuun numeron, mikä kertoo kuinka
     * monta miinaa ruudun ympärillä on
     * 
     * @param miinatRuudunYmparilla     Lisättävä numero
     */
    public void lisaaMiinaNumero(int miinatRuudunYmparilla) {
        if (this.getComponentCount() < 1 && miinatRuudunYmparilla > 0) {
            this.add(new JLabel(miinatRuudunYmparilla + ""));
            this.revalidate();
            this.repaint();
        }
    }
}
