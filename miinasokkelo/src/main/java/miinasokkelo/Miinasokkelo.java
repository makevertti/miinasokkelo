
package miinasokkelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import miinasokkelo.logiikka.Pelialue;
import miinasokkelo.logiikka.Pelaaja;

/**
 * Main metodin sisältävä luokka, joka käynnistää pelin 
 */
public class Miinasokkelo {
    private static int pelialueenKoko = 30;
    private static int miinat = 80;
    private static String[] vaikeustasot = {"Helppo", "Normaali", "Vaikea"};
    private static String vaikeustaso;
    
    public static void main(String[] args) {
        vaikeustaso = (String)JOptionPane.showInputDialog(new JFrame(), "Valitse vaikeustaso", "miinasokkelo", JOptionPane.QUESTION_MESSAGE, null, vaikeustasot, vaikeustasot[0]);
        if (vaikeustaso == null) {
            System.exit(0);
        } else if (vaikeustaso.equals("Helppo")) {
            pelialueenKoko = 20;
            miinat = 32;
        } else if (vaikeustaso.equals("Normaali")) {
            pelialueenKoko = 25;
            miinat = 75;
        } else if (vaikeustaso.equals("Vaikea")) {
            pelialueenKoko = 30;
            miinat = 100;
        }
        Pelialue pelialue = new Pelialue(pelialueenKoko, miinat, new Pelaaja(0, 0), true);
        pelialue.setNappaimistonkuuntelija();
    }
}