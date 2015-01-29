
package miinasokkelo.logiikka;

import java.util.Random;
import javax.swing.JFrame;
import miinasokkelo.kayttoliittyma.GraafinenPelialue;

public class Pelialue {
    private int[][] ruudukko;   // 0 = tyhjä, 1 = pelaaja, 2 = miina, 3 = miina, johon pelaaja osui, 4 = maali
    private Random random;
    private boolean[][] avatutRuudut;   // false = ruudussa ei käyty, true = ruudussa käyty
    private GraafinenPelialue graafinenPelialue;
    private Pelaaja pelaaja;
    private int[][] miinojaVieressa;
    
    public Pelialue(int alueenKoko, int miinat) {
        ruudukko = new int[alueenKoko][alueenKoko];
        ruudukko[alueenKoko - 1][alueenKoko - 1] = 4;
        avatutRuudut = new boolean[alueenKoko][alueenKoko];
        avatutRuudut[alueenKoko - 1][alueenKoko - 1] = true;
        lisaaMiinat(miinat);
        miinojaVieressa = new int[alueenKoko][alueenKoko];
        luoMiinojaVieressaTaulukko();
        
        graafinenPelialue = new GraafinenPelialue(this, alueenKoko);
        luoGrafiikat();
    }
    
    public void paivitaPelaajanSijainti(int edellinenX, int edellinenY, int x, int y) {
        ruudukko[edellinenY][edellinenX] = 0;
        if (ruudukko[y][x] == 2) {
            ruudukko[y][x] = 3;
            pelaajaOsuiMiinaan();
        } else {
            ruudukko[y][x] = 1;
            paivitaAvatutRuudut(x, y);
            graafinenPelialue.paivitaGraafinenPelialue();
        }
        System.out.println(miinojaVieressa[y][x]);
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
    
    private void pelaajaOsuiMiinaan() { //Peli loppuu
        System.out.println("Miina!");
        pelaaja.poistaOhjaus();
        graafinenPelialue.naytaKaikkiMiinat();
    }
    
//    public void tulosta() {
//        for (int i = 0; i < ruudukko.length; i++) {
//            for (int j = 0; j < ruudukko.length; j++) {
//                System.out.print(ruudukko[i][j]);                
//            }
//            System.out.println("");
//        }
//    }
    
//    public void tulostaAvatutRuudut() {
//        for (int i = 0; i < avatutRuudut.length; i++) {
//            for (int j = 0; j < avatutRuudut.length; j++) {
//                System.out.print(avatutRuudut[i][j]);
//            }
//            System.out.println("");            
//        }
//    }

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

    public void lisaaPelaaja(Pelaaja p) {
        pelaaja = p;
    }

    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }

    public void lisaaNappaimistonkuuntelija() {
        graafinenPelialue.lisaaNappaimistonkuuntelija();
    }

    private void luoMiinojaVieressaTaulukko() {
        for (int y = 0; y < ruudukko.length; y++) {
            for (int x = 0; x < ruudukko.length; x++) {
                int maara = 0;
                
                for (int y2 = -1; y2 <= 1; y2++) {
                    if ((y + y2 < 0) || (y + y2 >= ruudukko.length)) {
                        continue;
                    }
                    for (int x2 = -1; x2 <= 1; x2++) {
                        if ((x + x2 < 0) || (x + x2 >= ruudukko.length)) {
                            continue;
                        }
                        if (ruudukko[y + y2][x + x2] == 2) {
                            maara++;
                        }
                    }
                }
                miinojaVieressa[y][x] = maara;
            }
        }
    }
    
    public int getMiinatRuudunYmparilla(int y, int x) {
        return miinojaVieressa[y][x];
    }
}