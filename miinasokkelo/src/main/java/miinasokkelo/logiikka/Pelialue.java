
package miinasokkelo.logiikka;

import java.util.Random;

public class Pelialue {
    int[][] ruudukko;   // 0 = tyhjä, 1 = pelaaja, 2 = miina
    Random random;
    
    public Pelialue(int alueenKoko) {
        ruudukko = new int[alueenKoko][alueenKoko];
        lisaaMiinat(30);
    }
    
    public void paivitaPelaajanSijainti(int edellinenX, int edellinenY, int x, int y) {
        ruudukko[edellinenY][edellinenX] = 0;
        if (ruudukko[y][x] == 2) {
            pelaajaOsuiMiinaan();
        }
        ruudukko[y][x] = 1;
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
    
    public void tulosta() {
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                System.out.print(ruudukko[i][j]);                
            }
            System.out.println("");
        }
    }

    private void pelaajaOsuiMiinaan() {
        System.out.println("Miina!");   //Peli loppuu
    }
}