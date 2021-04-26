# PINGPONG

Sovellus on klassinen Pong peli, jossa pelaaja voi pelata tietokonetta tai toista pelaajaa vastaan. Pelaaja ohjaa suorakaiteista muotoista mailaa, jossa kimmotellaan palloa. Peli päättyy kuin pallo päätyy vasempaan tai oikeaan laitaan.


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/tuntikirjanpito.md)

## Releaset

## Komentorivitoiminnot

### Testaus

Testit voidaan suorittaa seuraavalla kommennolla

```
mvn test
```

Testikattavuusraportti voidaan suorittaa seuraavalla kommennolla

```
mvn test jacoco:report
```

Kattavuusraportin voi avata selaimella tiedoston _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Jar file voidaan generoida komennolla

```
mvn package
```

jar generoi hakemistoon _target_ ja se suoritetaan jar-tiedoston _pingpong-1.0-SNAPSHOT.jar_

### JavaDoc

### Checkstyle

Checkstyle voidaan suorittaa seuraavalla komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virhe ilmoitukset voidaan tarkistaa selaimella tiedostoa _target/site/checkstyle.html_