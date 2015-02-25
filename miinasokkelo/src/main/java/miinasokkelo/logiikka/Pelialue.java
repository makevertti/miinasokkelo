
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
    private Aluetarkistaja aluetarkistaja;
    private Miinahallinta miinahallinta;

    /**
     * Luo pelialueen annettujen parametrien mukaan
     *
     * @param alueenKoko Ruutujen määrä korkeus- ja leveyssuunnassa
     * @param miinat Miinojen määrä
     * @param pelaaja Pelialueelle sijoitettava pelaaja
     * @param eiMahdottomia Halutaanko ruudukosta aina mahdollinen
     */
    public Pelialue(int alueenKoko, int miinat, Pelaaja pelaaja, boolean eiMahdottomia) {
        this.pelaaja = pelaaja;
        pelaaja.setPelialue(this);
        ruudukko = new int[alueenKoko][alueenKoko];
        avatutRuudut = new boolean[alueenKoko][alueenKoko];
        avatutRuudut[alueenKoko - 1][alueenKoko - 1] = true;
        miinahallinta = new Miinahallinta(this);
        miinahallinta.lisaaMiinat(miinat, alueenKoko, eiMahdottomia);
        ruudukko[alueenKoko - 1][alueenKoko - 1] = 4;
        miinahallinta.luoMiinojaVieressaTaulukko();
        aluetarkistaja = new Aluetarkistaja(this);

        graafinenPelialue = new GraafinenPelialue(this);
        paivitaPelaajanSijainti(pelaaja.getX(), pelaaja.getY(), pelaaja.getX(), pelaaja.getY());
        graafinenPelialue.luoPeliIkkuna();
    }

    /**
     * Päivittää pelaajan sijainnin taulukossa
     *
     * @param edellinenX x-koordinaatti, mistä pelaaja tuli
     * @param edellinenY y-koordinaatti, mistä pelaaja tuli
     * @param x          x-koordinaatti, mihin pelaaja siirtyi
     * @param y          y-koordinaatti, mihin pelaaja siirtyi
     */
    public void paivitaPelaajanSijainti(int edellinenX, int edellinenY, int x, int y) {
        ruudukko[edellinenY][edellinenX] = 0;
        if (ruudukko[y][x] == 4) {
            peliPaattyi("Taso suoritettu!");
        } else if (ruudukko[y][x] == 2) {
            ruudukko[y][x] = 3;
            peliPaattyi("Miina!");
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

    private void peliPaattyi(String otsikko) {
        pelaaja.poistaOhjaus();
        graafinenPelialue.naytaKaikkiMiinat();
        graafinenPelialue.naytaUusiPeliKysymys(otsikko);
    }

    private void avaaRuudutJoidenVieressaEiOleMiinoja(int x, int y, boolean kayty[][]) {
        if (x < 0 || y < 0 || x > getKoko() - 1 || y > getKoko() - 1 || kayty[y][x] == true || getMiinatRuudunYmparilla(x, y) != 0) {
            return;
        }
        kayty[y][x] = true;
        avatutRuudut[y][x] = true;
        aluetarkistaja.tarkistaEttaRuutuOnPelialueella(x, y);
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

    /**
     * Tarkistaa onko ruutu pelialueen sisällä
     * 
     * @param x     Ruudun x-koordinaatti
     * @param y     Ruudun y-koordinaatti
     * @return      True: on alueella, false: ei ole alueella
     */
    public boolean onPelialueella(int x, int y) {
        return aluetarkistaja.onPelialueella(x, y);
    }

    /**
     * Aloittaa uuden pelin
     */
    public void uusiPeli() {
        pelaaja.uusiPeli();
    }

    public int getRuutu(int x, int y) {
        return ruudukko[y][x];
    }

    public boolean getOnkoAvattu(int x, int y) {
        return avatutRuudut[y][x];
    }

    public int getMiinatRuudunYmparilla(int x, int y) {
        return miinahallinta.getMiinojaRuudunYmparilla(x, y);
    }

    public void avaaRuutu(int x, int y) {
        avatutRuudut[y][x] = true;
    }

    public int[][] getRuudukko() {
        return ruudukko;
    }

    public int getKoko() {
        return ruudukko.length;
    }

    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }

    public void setNappaimistonkuuntelija() {
        graafinenPelialue.setNappaimistonkuuntelija();
    }
}
