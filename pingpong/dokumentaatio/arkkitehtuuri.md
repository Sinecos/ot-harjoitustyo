# Arkkitehtuurikuvaus

## Rakenne

Pelin rakenne perustuu kolme tasoiseen kerrosarkkitehtuuriin ja sen pakkausrakenne näyttää alla mainitussa kuvassa. Pakkaus pongapp.ui sisältää pelin käyttöliittymän joka on toteutettu JavaFX:llä. Taas pongapp.domain sisältää pelin logiikka ja pongapp.dao sisältää topscore tallenus koodi.

<img src="https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/kuvat/uml_1.png" width="200">

## Käyttöliittymä

Pelin käyttöliittymässä sisältää kolmea erillaista näkymää
- Main Menu
- Kaksi eri peli modeja
- TopScore

pongapp.ui.PongUi sisältyy ohjelmallisesti alku menu, pelin varsinainen update, grafiikan piirtäminen sekä topscore koodit. Näihin kaikkeihin luodaan omat scene-oliot.

Käyttöliittymä on pyritty eristämään sovelluslokiigasta. pongapp.ui.PongUi:ssa kutsutaan ainoasti sovelluslogiikan GameLogic metodeja jotka toteuttaa pelin pelattavuuden.

## Sovelluslogiikka
