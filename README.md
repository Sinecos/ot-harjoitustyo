# PINGPONG

Sovellus on klassinen Pong peli, jossa pelaaja voi pelata tietokonetta tai toista pelaajaa vastaan. Pelaaja ohjaa suorakaiteista muotoista mailaa, jossa kimmotellaan palloa. Peli päättyy kuin pallo päätyy vasempaan tai oikeaan laitaan.

## Javan versiosta

Tämä sovellus toimii ainoastaan Java versio 11. Koodi toimii laitoksen clubbi Gpu -Linuxeissa olevilla Java 11 koneilla.

## Dokumentaatio

[Käyttöohje](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/tuntikirjanpito.md)

[Testausdokumentti](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/testaus.md)


## Releaset

[Loppupalautus](https://github.com/nothros/ot-harjoitustyo/releases/tag/loppupalautus)

[Viikko 6](https://github.com/Sinecos/ot-harjoitustyo/releases/tag/Viikko6)

[Viikko 5](https://github.com/Sinecos/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

Huomautus! Kaikki *mvn* komennot pitää ajaa pingpong päähakemistossa, missä tiedosto pom.xml sijaitsee.

### Testaus

Testit voidaan suorittaa seuraavalla komennolla:

```
mvn test
```

Testikattavuusraportti voidaan suorittaa seuraavalla komennolla:

```
mvn test jacoco:report
```

Kattavuusraportin voi avata selaimella tiedoston *target/site/jacoco/index.html*

### Suoritettavan jarin generointi

Jar file voidaan generoida komennolla:

```
mvn package
```

generoitu tiedosto *pingpong-1.0-SNAPSHOT.jar* sijaitsee hakemistossa *target*

#### jar tiedoston käynnistäminen

Tiedosto *pingpong-1.0-SNAPSHOT.jar* voidaan käynnistää komennolla:

```
java -jar pingpong-1.0-SNAPSHOT.jar
```

tiedosto sijaitsee *target* kansiossa

### Compile

Peliä voidaan myös compile ja avata komentoriviä käyttäen:

```
mvn compile exec:java -Dexec.mainClass=pongapp.App
```

### JavaDoc

JavaDoc voidaan suorittaa seuraavalla komennolla

```
mvn javadoc:javadoc
```

JavaDoc gereneroima tiedot voidaan tarkistaa selaimella avaamalla tiedostoa *target/site/apidocs/index.html*

### Checkstyle

Checkstyle voidaan suorittaa seuraavalla komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virhe ilmoitukset voidaan tarkistaa selaimella tiedostoa *target/site/checkstyle.html*