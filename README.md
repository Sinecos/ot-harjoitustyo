# PINGPONG

Sovellus on klassinen Pong peli, jossa pelaaja voi pelata tietokonetta tai toista pelaajaa vastaan. Pelaaja ohjaa suorakaiteista muotoista mailaa, jossa kimmotellaan palloa. Peli päättyy kuin pallo päätyy vasempaan tai oikeaan laitaan.

## Javan versiosta

Tämä sovellus toimii ainoastaan Java versio 11. Koodi toimii laitoksen clubbi Gpu -Linuxeissa olevilla Java 11 koneilla.

## Dokumentaatio

[Käyttöohje](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 6](https://github.com/Sinecos/ot-harjoitustyo/releases/tag/Viikko6)

[Viikko 5](https://github.com/Sinecos/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit voidaan suorittaa seuraavalla komennolla

```
mvn test
```

Testikattavuusraportti voidaan suorittaa seuraavalla komennolla

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

### jar tiedoston käynnistäminen

Tiedosto pingpong-1.0-SNAPSHOT.jar voidaan käynnistää komennolla

```
java -jar pingpong-1.0-SNAPSHOT.jar
```

### JavaDoc

JavaDoc voidaan suorittaa seuraavalla komennolla

```
mvn javadoc:javadoc
```

### Checkstyle

Checkstyle voidaan suorittaa seuraavalla komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virhe ilmoitukset voidaan tarkistaa selaimella tiedostoa _target/site/checkstyle.html_