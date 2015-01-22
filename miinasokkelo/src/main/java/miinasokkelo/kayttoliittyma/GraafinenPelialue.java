
package miinasokkelo.kayttoliittyma;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GraafinenPelialue implements Runnable {

    private JFrame ikkuna;
    private PaaIkkuna paaIkkuna;
    
    @Override
    public void run() {
        ikkuna = new JFrame("miinasokkelo");
        ikkuna.setPreferredSize(new Dimension(400, 400));
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paaIkkuna = new PaaIkkuna();
        ikkuna.getContentPane().add(paaIkkuna);
        ikkuna.pack();
        ikkuna.setVisible(true);
    }
    
}