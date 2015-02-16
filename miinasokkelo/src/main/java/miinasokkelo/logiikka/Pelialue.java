package miinasokkelo.logiikka;

import java.util.Random;
import miinasokkelo.kayttoliittyma.GraafinenPelialue;

/**
 * Kuvaa pelialueen tilannetta taulukoiden avulla
 */
public class Pelialue {

    private int[][] ruudukko;   // 0 = tyhjä, 1 = pelaaja, 2 = miina, 3 = miina, johon pelaaja osui, 4 = maali
    private Random random;
    private boolean[][] avatutRuudut;   // false = ruudussa ei käyty, true = ruudussa käyty
    private GraafinenPelialue graafinenPelialue;
    private Pelaaja pelaaja;
    private int[][] miinojaVieressa;

    /**
     * Luo pelialueen annettujen parametrien mukaan
     * 
     * @param alueenKoko    Ruutujen määrä korkeus- ja leveyssuunnassa
     * @param miinat        Miinojen määrä
     * @param pelaaja       Pelialueelle sijoitettava pelaaja
     * @param eiMahdottomia Halutaanko ruudukosta aina mahdollinen
     */
    public Pelialue(int alueenKoko, int miinat, Pelaaja pelaaja, boolean eiMahdottomia) {
        this.pelaaja = pelaaja;
        pelaaja.setPelialue(this);
        ruudukko = new int[alueenKoko][alueenKoko];
        avatutRuudut = new boolean[alueenKoko][alueenKoko];
        avatutRuudut[alueenKoko - 1][alueenKoko - 1] = true;
        lisaaMiinat(miinat, alueenKoko, eiMahdottomia);
        miinojaVieressa = new int[alueenKoko][alueenKoko];
        ruudukko[alueenKoko - 1][alueenKoko - 1] = 4;
        luoMiinojaVieressaTaulukko();

        graafinenPelialue = new GraafinenPelialue(this);
        paivitaPelaajanSijainti(pelaaja.getX(), pelaaja.getY(), pelaaja.getX(), pelaaja.getY());
        paivitaAvatutRuudut();
        graafinenPelialue.luoPeliIkkuna();

    }
    
    /**
     * Päivittää pelaajan sijainnin taulukossa
     * 
     * @param edellinenX    x-koordinaatti, mistä pelaaja tuli
     * @param edellinenY    y-koordinaatti, mistä pelaaja tuli
     * @param x             x-koordinaatti, mihin pelaaja siirtyi
     * @param y             y-koordinaatti, mihin pelaaja siirtyi
     */
    public void paivitaPelaajanSijainti(int edellinenX, int edellinenY, int x, int y) {
        ruudukko[edellinenY][edellinenX] = 0;
        if (ruudukko[y][x] == 4) {
            tasoSuoritettu();
        } else if (ruudukko[y][x] == 2) {
            ruudukko[y][x] = 3;
            pelaajaOsuiMiinaan();
        } else {
            ruudukko[y][x] = 1;
            paivitaAvatutRuudut();
            graafinenPelialue.paivitaGraafinenPelialue();
        }
    }

    /**
     * Avaa ruudut jotka pelaaja "näkee"
     */
    public void paivitaAvatutRuudut() {
        avaaRuudutJoidenVieressaEiOleMiinoja(pelaaja.getX(), pelaaja.getY(), new boolean[getKoko()][getKoko()]);
        avatutRuudut[pelaaja.getY()][pelaaja.getX()] = true;
    }

    public int getKoko() {
        return ruudukko.length;
    }

    private void lisaaMiinat(int maara, int alueenKoko, boolean eiMahdottomia) {
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
                ruudukko[j][i] = miinaRuudut[j][i];
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

    private void pelaajaOsuiMiinaan() {
        pelaaja.poistaOhjaus();
        graafinenPelialue.naytaKaikkiMiinat();
        graafinenPelialue.naytaUusiPeliKysymys("Miina!");
    }

    public int getRuutu(int x, int y) {
        return ruudukko[y][x];
    }

    public boolean getOnkoAvattu(int x, int y) {
        return avatutRuudut[y][x];
    }

    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }

    public void setNappaimistonkuuntelija() {
        graafinenPelialue.setNappaimistonkuuntelija();
    }

    private void luoMiinojaVieressaTaulukko() {
        for (int y = 0; y < ruudukko.length; y++) {
            for (int x = 0; x < ruudukko.length; x++) {
                laskeRuudunYmparillaOlevatMiinat(y, x);
            }
        }
    }

    private void laskeRuudunYmparillaOlevatMiinat(int y, int x) {
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

    public int getMiinatRuudunYmparilla(int y, int x) {
        return miinojaVieressa[y][x];
    }

    private void tasoSuoritettu() {
        pelaaja.poistaOhjaus();
        graafinenPelialue.naytaKaikkiMiinat();
        graafinenPelialue.naytaUusiPeliKysymys("Taso Suoritettu!");
    }

    private void avaaRuudutJoidenVieressaEiOleMiinoja(int x, int y, boolean kayty[][]) {
        if (x < 0 || y < 0 || x > getKoko() - 1 || y > getKoko() - 1 || kayty[y][x] == true || miinojaVieressa[y][x] != 0) {
            return;
        }
        kayty[y][x] = true;
        avatutRuudut[y][x] = true;
        tarkistaEttaRuutuOnPelialueella(x, y);
        avaaRuudutRekursiivisesti(x, y, kayty);
    }

    private void avaaRuudutRekursiivisesti(int x, int y, boolean[][] kayty) {
        avaaRuudutJoidenVieressaEiOleMiinoja(x + 1, y, kayty);
        avaaRuudutJoidenVieressaEiOleMiinoja(x - 1, y, kayty);
        avaaRuudutJoidenVieressaEiOleMiinoja(x, y + 1, kayty);
        avaaRuudutJoidenVieressaEiOleMiinoja(x, y - 1, kayty);
        avaaRuudutJoidenVieressaEiOleMiinoja(x + 1, y + 1, kayty);
        avaaRuudutJoidenVieressaEiOleMiinoja(x - 1, y - 1, kayty);
        avaaRuudutJoidenVieressaEiOleMiinoja(x + 1, y - 1, kayty);
        avaaRuudutJoidenVieressaEiOleMiinoja(x - 1, y + 1, kayty);
    }

    private void tarkistaEttaRuutuOnPelialueella(int x, int y) {
        if (onkoAlueella(x, y + 1)) {
            avatutRuudut[y + 1][x] = true;
        }
        if (onkoAlueella(x + 1, y)) {
            avatutRuudut[y][x + 1] = true;
        }
        if (onkoAlueella(x, y - 1)) {
            avatutRuudut[y - 1][x] = true;
        }
        if (onkoAlueella(x + 1, y)) {
            avatutRuudut[y][x + 1] = true;
        }
        if (onkoAlueella(x + 1, y + 1)) {
            avatutRuudut[y + 1][x + 1] = true;
        }
        if (onkoAlueella(x - 1, y - 1)) {
            avatutRuudut[y - 1][x - 1] = true;
        }
        if (onkoAlueella(x - 1, y + 1)) {
            avatutRuudut[y + 1][x - 1] = true;
        }
        if (onkoAlueella(x + 1, y - 1)) {
            avatutRuudut[y - 1][x + 1] = true;
        }
    }
    
    private boolean onkoAlueella(int x, int y) {
        if (x < 0 || y < 0 || x > getKoko() - 1 || y > getKoko() - 1) {
            return false;
        }
        return true;
    }
    
    public void uusiPeli() {
        pelaaja.uusiPeli();
    }
}