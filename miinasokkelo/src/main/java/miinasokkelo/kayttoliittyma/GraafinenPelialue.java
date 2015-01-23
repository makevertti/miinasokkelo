
package miinasokkelo.kayttoliittyma;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class GraafinenPelialue extends JPanel {
    private Ruutu ruudut[][];
    
    public GraafinenPelialue(int koko) {        
        this.setSize(800, 800);
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(koko, koko, 1, 1));
        ruudut = new Ruutu[koko][koko];
        
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                ruudut[j][i] = new Ruutu();
                ruudut[j][i].setSize(800, 800);
                this.add(ruudut[j][i]);
            }
        }
    }
    
}