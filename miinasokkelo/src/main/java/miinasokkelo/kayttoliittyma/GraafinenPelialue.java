
package miinasokkelo.kayttoliittyma;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import miinasokkelo.logiikka.Pelialue;

/**
 * Luo Pelialue -luokan tietojen perusteella graafisen
 * esityksen pelialueesta
 */
public class GraafinenPelialue extends JPanel {
    private GrafiikkaRuutu grafiikkaRuudut[][];
    private Pelialue pelialue;
    private Nappaimistokuuntelija nappaimistokuuntelija;
    
    /**
     * Luo graafisen pelialueen pohjan
     * 
     * @param alue  pelialue josta graafinen versio halutaan tehdä
     */
    public GraafinenPelialue(Pelialue alue) {
        pelialue = alue;
        
        this.setSize(800, 800);
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(pelialue.getKoko(), pelialue.getKoko(), 1, 1));
        grafiikkaRuudut = new GrafiikkaRuutu[pelialue.getKoko()][pelialue.getKoko()];
        
        for (int i = 0; i < pelialue.getKoko(); i++) {
            for (int j = 0; j < pelialue.getKoko(); j++) {
                grafiikkaRuudut[j][i] = new GrafiikkaRuutu();
                grafiikkaRuudut[j][i].setSize(800, 800);
                this.add(grafiikkaRuudut[j][i]);
            }
        }
    }
    
    /**
     * Päivittää graafisen pelialueen 
     * Pelialue -luokan ruudukkoa vastaavaksi
     */
    public void paivitaGraafinenPelialue() {
        for (int i = 0; i < pelialue.getKoko(); i++) {
            for (int j = 0; j < pelialue.getKoko(); j++) {
                if (!pelialue.getOnkoAvattu(j, i)) {  //ruudussa ei ole käyty / "tuntematon"
                    grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("tuntematon");
                } else {
                    if (pelialue.getRuutu(j, i) == 0) {                     //tyhjä ruutu
                        grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("tyhjä");
                        grafiikkaRuudut[j][i].lisaaMiinaNumero(pelialue.getMiinatRuudunYmparilla(i, j));
                    } else if (pelialue.getRuutu(j, i) == 2) {              //miina
                        grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("miina");
                    } else if (pelialue.getRuutu(j, i) == 1) {              //pelaaja
                        grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("pelaaja");
                        grafiikkaRuudut[j][i].lisaaMiinaNumero(pelialue.getMiinatRuudunYmparilla(i, j));
                    } else if (pelialue.getRuutu(j, i) == 4) {              //maali
                        grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("maali");
                    }
                }
            }
        }
    }

    public void setNappaimistonkuuntelija() {
        nappaimistokuuntelija = new Nappaimistokuuntelija(pelialue.getPelaaja());
        addKeyListener(nappaimistokuuntelija);
        setFocusable(true);
    }
    
    /**
     * Näyttää kaikkien miinojen sijainnit
     */
    public void naytaKaikkiMiinat() {
        for (int i = 0; i < pelialue.getKoko(); i++) {
            for (int j = 0; j < pelialue.getKoko(); j++) {
                if (pelialue.getRuutu(j, i) == 2) {
                    grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("miina");
                } else if (pelialue.getRuutu(j, i) == 3) {
                    grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("törmätty miina");
                }
            }
        }
    }
}