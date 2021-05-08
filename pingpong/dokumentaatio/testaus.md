# Testausdokumentti

Ohjelman testausta varten on käytetty automatisoitu JUnit-kirjastoa sekä manuaalisesti tehtyjä testauksia.

## Sovelluslogiikka

Testauksessa on käytetty suurimmaksi osaksi sovelluslogiikan luokkija jotka seuraavat luokat testavat niitä:

[GameLogicTest]
(https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/src/test/java/pongapp/domain/GameLogicTest.java)

[EntityTest]
(https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/src/test/java/pongapp/domain/EntityTest.java)

[GraphicsTest]
(https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/src/test/java/pongapp/domain/GraphicsTest.java)

[PlayerScoreTest]
(https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/src/test/java/pongapp/domain/PlayerScoreTest.java)


- GameLogicTest testaa GameLogic olevat metodit ja pelin toimivuutta.
- EntityTest testaa luokan Entity ja Vector2 eri metodit.
- GraphicsTest testaa Graphics luokan metodeja.
- PlayerScoreTest testaa PlayerScore eri metodit

## DAO-Luokka

Dao luokka on testattu Junitin TemporaryFolder sääntöjä käyttäen.

# Testauskattavuus

Testauksien rivikattavuus on 97% ja haarautumakattavuus on 75%.

<img src="https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/kuvat/test.png" width="800">

Testaamatta jäi osa GameLogic haarojen osuudesta. 