
package miinasokkelo.logiikka;

public class Pelaaja {
    Pelialue pelialue;
    int sijaintiX;
    int sijaintiY;
    
    int edellinenX;
    int edellinenY;
    
    public Pelaaja(Pelialue p, int x, int y) {
        pelialue = p;
        sijaintiX = x;
        sijaintiY = y;
        
        edellinenX = x;
        edellinenY = y;
        
        paivitaPelaajanSijainti();
    }
    
    public void liiku(String suunta) {
        switch(suunta) {
            case "ylös":
                if (sijaintiY - 1 < 0) break;
                paivitaEdellinenSijainti();
                sijaintiY--;
                break;
                
            case "yläoikea":
                if (sijaintiY - 1 < 0 || sijaintiX + 1 > pelialue.getKoko() - 1) break;
                paivitaEdellinenSijainti();
                sijaintiY--;
                sijaintiX++;
                break;
                
            case "oikea":
                if (sijaintiX + 1 > pelialue.getKoko() - 1) break;
                paivitaEdellinenSijainti();
                sijaintiX++;
                break;
                
            case "alaoikea":
                if (sijaintiY + 1 > pelialue.getKoko() - 1 || sijaintiX + 1 > pelialue.getKoko() - 1) break;
                paivitaEdellinenSijainti();
                sijaintiY++;
                sijaintiX++;
                break;
                
            case "alas":
                if (sijaintiY + 1 > pelialue.getKoko() - 1) break;
                paivitaEdellinenSijainti();
                sijaintiY++;
                break;
                
            case "alavasen":
                if (sijaintiY + 1 > pelialue.getKoko() - 1 || sijaintiX - 1 < 0) break;
                paivitaEdellinenSijainti();
                sijaintiY++;
                sijaintiX--;
                break;
                
            case "vasen":
                if (sijaintiX - 1 < 0) break;
                paivitaEdellinenSijainti();
                sijaintiX--;
                break;
                
            case "ylävasen":
                if (sijaintiY - 1 < 0 || sijaintiX - 1 < 0) break;
                paivitaEdellinenSijainti();
                sijaintiY--;
                sijaintiX--;
                break;
        }
        paivitaPelaajanSijainti();
    }
    
    private void paivitaPelaajanSijainti() {
        pelialue.paivitaPelaajanSijainti(edellinenX, edellinenY, sijaintiX, sijaintiY);
    }

    private void paivitaEdellinenSijainti() {
        edellinenY = sijaintiY;
        edellinenX = sijaintiX;
    }
}