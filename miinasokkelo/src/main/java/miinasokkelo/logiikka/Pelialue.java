
package miinasokkelo.logiikka;

public class Pelialue {
    int[][] ruudukko;
    
    public Pelialue(int alueenKoko) {
        ruudukko = new int[alueenKoko + 1][alueenKoko + 1];
    }
    
    public void paivitaPelaajanSijainti(int edellinenX, int edellinenY, int x, int y) {
        ruudukko[edellinenY][edellinenX] = 0;
        ruudukko[y][x] = 1;
    }
    
    public int getKoko() {
        return ruudukko.length - 1;
    }
    
//    public void tulosta() {
//        for (int i = 0; i < ruudukko.length; i++) {
//            for (int j = 0; j < ruudukko.length; j++) {
//                System.out.print(ruudukko[i][j]);                
//            }
//            System.out.println("");
//        }
//    }
}