# Käyttöohje

Lataa ensin [pingpong-1.0-SNAPSHOT.jar](https://github.com/Sinecos/ot-harjoitustyo/releases/tag/Viikko6)

## Konfigurointi

Sovelluksen avattaessa se generoi konfiguraatiotiedosto _config.properties_ ja playerScore.txt tiedosto. _config.properties_ sisältää sen tiedosto path, mihin player name ja score tulee tallentamaan. Kyseinen tiedosto näyttää seuraavalta:

```
playerScoreFile=playerScore.txt
```

## Ohjelman käynnistäminen

Sovellus käynistetään seuraavasti

```
java -jar pingpong-1.0-SNAPSHOT.jar
```

## Menu

Pelaaja voi valita neljä vaihtoehtoa:

<img src="https://github.com/Sinecos/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/menu.png" width="400">

1. New Game (Computer), pelaajaa pelaa AI vastaan. 
2. New Game (Player2) Pelaaja pelaa toista pelaajaa vastaan.
3. Pelaaja voi katsoa Top Score Table
4. Pelaaja voi poistua pelistä.

## P1 VS Computer

Jos pelaaja saa aina 5 pistettä lisää, niin peli vaikentuu siten, että paddlet alkavat liikkua x-akselia suuntaan eteen- ja taaksepäin.

Peli päättyy, kun pallo menee jommankumman pelaajan taakse. Pelaaja 1 voittaa jos pallo menee AI paddlen taakse.

## P1 VS P2

Pelaaja 1 ohjaa hiirtä ja pelaaja 2 ohjaa näppäimistön ylös ja alas nappeja.

## Score Table

Kun peli päättyy, pelaaja siirtyy Score table sceneen. Pelaaja voi kirjoittaa nimensä ja painamalla add nappia, pelaajan nimi ja score tallentuu Score Tableen ja playerScore.txt tiedostoon.

<img src="https://github.com/Sinecos/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/score.png" width="400">

Pelaaja voi palata takaisin main menu nappia painamalla.

## Kesken peli takaisin päävalikkoon

Painamalla 'Esc' nappia pelaaja voi palata takaisin päävalikkoon.
