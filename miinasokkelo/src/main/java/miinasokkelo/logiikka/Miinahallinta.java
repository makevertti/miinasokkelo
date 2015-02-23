
package miinasokkelo.logiikka;

import java.util.Random;

/**
 * Hallinnoi pelialueen miinoja
 */
public class Miinahallinta {

    private Pelialue pelialue;
    private Random random;
    private int[][] miinojaVieressa;

    /**
     * Luo miinahallinnan
     * 
     * @param alue  Pelialue johon miinahallinta kytketään 
     */
    public Miinahallinta(Pelialue alue) {
        pelialue = alue;
        miinojaVieressa = new int[pelialue.getKoko()][pelialue.getKoko()];
    }

    /**
     * Lisää pelialueelle miinat
     * 
     * @param maara         Lisättävien miinojen määrä
     * @param alueenKoko    Pelialueen koko
     * @param eiMahdottomia Määrittää halutaanko aina mahdollinen miina-asettelu
     */
    public void lisaaMiinat(int maara, int alueenKoko, boolean eiMahdottomia) {
        random = new Random();
        int miinaRuudut[][] = new int[alueenKoko][alueenKoko];
        int miinojaLisatty = 0;

        lisaaMiinatValiaikaistaulukkoon(miinojaLisatty, maara, alueenKoko, miinaRuudut);

        if (eiMahdottomia) {
            if (mahdollisetReitit(miinaRuudut, 0, 0, new boolean[alueenKoko][alueenKoko]) == 0) {
                lisaaMiinat(maara, alueenKoko, eiMahdottomia);
            }
        }
        lisaaMiinatValiaikaistaulukostaVarsinaiseenTaulukkoon(miinaRuudut);
    }

    private void lisaaMiinatValiaikaistaulukostaVarsinaiseenTaulukkoon(int[][] miinaRuudut) {
        for (int i = 0; i < miinaRuudut.length; i++) {
            for (int j = 0; j < miinaRuudut.length; j++) {
                pelialue.getRuudukko()[j][i] = miinaRuudut[j][i];
            }
        }
    }

    private void lisaaMiinatValiaikaistaulukkoon(int miinojaLisatty, int maara, int alueenKoko, int[][] miinaRuudut) {
        while (miinojaLisatty < maara) {
            int randomX = random.nextInt(alueenKoko);
            int randomY = random.nextInt(alueenKoko);

            if (miinaRuudut[randomY][randomX] != 2 && !(randomX == 0 && randomY == 0) && !(randomX == alueenKoko - 1 && randomY == alueenKoko - 1)) {
                miinaRuudut[randomY][randomX] = 2;
                miinojaLisatty++;
            }
        }
    }

    private int mahdollisetReitit(int miinaRuudut[][], int x, int y, boolean kayty[][]) {
        if (x < 0 || y < 0 || x > miinaRuudut.length - 1 || y > miinaRuudut.length - 1 || kayty[y][x] || miinaRuudut[y][x] == 2) {
            return 0;
        }

        kayty[y][x] = true;

        if (y == miinaRuudut.length - 1 && x == miinaRuudut.length - 1) {
            return 1;
        }

        int reitit = 0;
        reitit = kayReititLapiRekursiivisesti(reitit, miinaRuudut, x, y, kayty);
        return reitit;
    }

    private int kayReititLapiRekursiivisesti(int reitit, int[][] miinaRuudut, int x, int y, boolean[][] kayty) {
        reitit += mahdollisetReitit(miinaRuudut, x + 1, y, kayty);
        reitit += mahdollisetReitit(miinaRuudut, x - 1, y, kayty);
        reitit += mahdollisetReitit(miinaRuudut, x, y + 1, kayty);
        reitit += mahdollisetReitit(miinaRuudut, x, y - 1, kayty);
        reitit += mahdollisetReitit(miinaRuudut, x + 1, y - 1, kayty);
        reitit += mahdollisetReitit(miinaRuudut, x + 1, y + 1, kayty);
        reitit += mahdollisetReitit(miinaRuudut, x - 1, y + 1, kayty);
        reitit += mahdollisetReitit(miinaRuudut, x - 1, y - 1, kayty);
        return reitit;
    }

    /**
     * Luo taulukon joka kertoo jokaisen
     * ruudun vieressä olevien miinojen määrän
     */
    public void luoMiinojaVieressaTaulukko() {
        for (int y = 0; y < pelialue.getRuudukko().length; y++) {
            for (int x = 0; x < pelialue.getRuudukko().length; x++) {
                laskeRuudunYmparillaOlevatMiinat(y, x);
            }
        }
    }

    private void laskeRuudunYmparillaOlevatMiinat(int y, int x) {
        int maara = 0;

        for (int y2 = -1; y2 <= 1; y2++) {
            if ((y + y2 < 0) || (y + y2 >= pelialue.getRuudukko().length)) {
                continue;
            }
            for (int x2 = -1; x2 <= 1; x2++) {
                if ((x + x2 < 0) || (x + x2 >= pelialue.getRuudukko().length)) {
                    continue;
                }
                if (pelialue.getRuudukko()[y + y2][x + x2] == 2) {
                    maara++;
                }
            }
        }
        miinojaVieressa[y][x] = maara;
    }

    /**
     * Kertoo kuinka monta miinaa
     * ruudun ympärillä on
     * 
     * @param x     Ruudun x-koordinaatti
     * @param y     Ruudun y-koordinaatti
     * @return      Ympärillä olevien miinojen määrä
     */
    public int getMiinojaRuudunYmparilla(int x, int y) {
        return miinojaVieressa[y][x];
    }
}