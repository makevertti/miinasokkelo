Miinasokkelo -luokka on main -metodin sisältävä luokka, joka luo vaikeustasovalikon ja käynnistää pelin. Pelaaja -luokka kuvaa pelaajan sijaintia pelialueella. Pelaajaan liittyy yksi pelialue, jossa pelaaja liikkuu. 

Pelialue -luokka kuvaa miinojen sijainnit ja avatut ruudut taulukoiden avulla. Miinahallinta ja Aluetarkistaja -luokat sisältävät metodeja, joita Pelialue käyttää.

Miinahallinta sisältää toimintoja liittyen miinojen hallinnointiin. Aluetarkistaja -luokan vastuuna on tehdä tarkistuksia, jotka estävät Pelialueen ulkopuolisten koordinaattien käsittelyn.

GraafinenPelialue -luokka esittää pelitilanteen graafisesti. GraafinenPelialue koostuu GrafiikkaRuutu -luokan ilmentymistä. Jokaista pelialueen ruutua vastaa yksi GrafiikkaRuutu. 

Nappaimistokuuntelija -luokka mahdollistaa pelaajan ohjaamisen näppäimistöllä.