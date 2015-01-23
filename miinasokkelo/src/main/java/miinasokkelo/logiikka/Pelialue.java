
package miinasokkelo.logiikka;

import java.util.Random;
import javax.swing.JFrame;
import miinasokkelo.kayttoliittyma.GraafinenPelialue;

public class Pelialue {
    int[][] ruudukko;   // 0 = tyhjä, 1 = pelaaja, 2 = miina
    Random random;
    boolean[][] avatutRuudut;   // false = ruudussa ei käyty, true = ruudussa käyty
    GraafinenPelialue graafinenPelialue;
    
    public Pelialue(int alueenKoko) {
        ruudukko = new int[alueenKoko][alueenKoko];
        avatutRuudut = new boolean[alueenKoko][alueenKoko];
        lisaaMiinat(40);
        graafinenPelialue = new GraafinenPelialue(this, alueenKoko);
        luoGrafiikat();
    }
    
    public void paivitaPelaajanSijainti(int edellinenX, int edellinenY, int x, int y) {
        ruudukko[edellinenY][edellinenX] = 0;
        if (ruudukko[y][x] == 2) {
            pelaajaOsuiMiinaan();
        }
        ruudukko[y][x] = 1;
        paivitaAvatutRuudut(x, y);
        
        graafinenPelialue.paivitaGraafinenPelialue();
    }
    
    public void paivitaAvatutRuudut(int x, int y) {
        avatutRuudut[y][x] = true;
    }
    
    public int getKoko() {
        return ruudukko.length;
    }
    
    private void lisaaMiinat(int maara) { // TODO: mahdottomien ruudukoiden karsiminen
        random = new Random();
        
        for (int y = 0; y < ruudukko.length; y++) {
            for (int x = 0; x < ruudukko.length; x++) {
                if (random.nextInt(100) < maara) {
                    ruudukko[y][x] = 2;
                }
            }
        }
    }
    
    private void pelaajaOsuiMiinaan() {
        System.out.println("Miina!");   //Peli loppuu
    }
    
    public void tulosta() {
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                System.out.print(ruudukko[i][j]);                
            }
            System.out.println("");
        }
    }
    
    public void tulostaAvatutRuudut() {
        for (int i = 0; i < avatutRuudut.length; i++) {
            for (int j = 0; j < avatutRuudut.length; j++) {
                System.out.print(avatutRuudut[i][j]);
            }
            System.out.println("");            
        }
    }

    public int getRuutu(int x, int y) {
        return ruudukko[y][x];
    }
    
    public boolean getOnkoAvattu(int x, int y) {
        return avatutRuudut[y][x];
    }
    
    private void luoGrafiikat() {
        JFrame ikkuna = new JFrame("miinasokkelo");
        ikkuna.add(graafinenPelialue);
        ikkuna.setSize(800, 800);
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true);    
    }
}