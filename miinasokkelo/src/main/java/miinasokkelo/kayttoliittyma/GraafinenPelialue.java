
package miinasokkelo.kayttoliittyma;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import miinasokkelo.logiikka.Pelialue;

public class GraafinenPelialue extends JPanel {
    private GrafiikkaRuutu grafiikkaRuudut[][];
    private Pelialue pelialue;
    private Nappaimistokuuntelija nappaimistokuuntelija;
    
    public GraafinenPelialue(Pelialue alue, int koko) {
        this.setSize(800, 800);
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(koko, koko, 1, 1));
        grafiikkaRuudut = new GrafiikkaRuutu[koko][koko];
        
        pelialue = alue;
        
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                grafiikkaRuudut[j][i] = new GrafiikkaRuutu();
                grafiikkaRuudut[j][i].setSize(800, 800);
                this.add(grafiikkaRuudut[j][i]);
            }
        }
    }
    
    public void paivitaGraafinenPelialue() {
        for (int i = 0; i < pelialue.getKoko(); i++) {
            for (int j = 0; j < pelialue.getKoko(); j++) {
                if (!pelialue.getOnkoAvattu(j, i)) {  //ruudussa ei ole käyty / "tuntematon"
                    grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("tuntematon");
                } else {
                    if (pelialue.getRuutu(j, i) == 0) {  //tyhjä ruutu
                        grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("tyhjä");
                    } else if (pelialue.getRuutu(j, i) == 2) {   //miina
                        grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("miina");
                    } else if (pelialue.getRuutu(j, i) == 1) {
                        grafiikkaRuudut[j][i].vaihdaRuudunTyyppi("pelaaja");
                    }
                }
            }
        }
    }

    public void lisaaNappaimistonkuuntelija() {
        nappaimistokuuntelija = new Nappaimistokuuntelija(pelialue.getPelaaja());
        addKeyListener(nappaimistokuuntelija);
        setFocusable(true);
    }
}