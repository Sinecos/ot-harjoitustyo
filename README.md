# PINGPONG

Sovellus on klassinen Pong peli, jossa pelaaja voi pelata tietokonetta tai toista pelaajaa vastaan. Pelaaja ohjaa suorakaiteista muotoista mailaa, jossa kimmotellaan palloa. Peli päättyy kuin pallo päätyy vasempaan tai oikeaan laitaan.

## Javan versiosta
Tämä sovellus toimii ainoastaan Java versio 11. Koodi toimii laitoksen clubbi-Linuxeissa olevilla Java 11 koneilla.


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/Sinecos/ot-harjoitustyo/blob/master/pingpong/releases/tag/viikko5)

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

## Jar tiedoston käynnistys

Tiedosto pingpong-1.0-SNAPSHOT.jar voidaan kaynistää kommennolla

```
java -jar pingpong-1.0-SNAPSHOT.jar
```

### JavaDoc

### Checkstyle

Checkstyle voidaan suorittaa seuraavalla komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virhe ilmoitukset voidaan tarkistaa selaimella tiedostoa _target/site/checkstyle.html_

## Javan ja Mavenin asennusohjeita Macille Homebrew'n kautta

Macilla voi käyttää Homebrew pakettimanageria Javan ja Maven asennuksissa. [Asenna Homebrew.](https://brew.sh/index_fi) ohjeiden mukaan.

### Javan asennus

Homebrew asennuksen jälkein voit seuraavalla kommennolla asentaa Java

```
brew install adoptopenjdk
```

### Mavenin asennus ja paluu Javan versioon 11

Mavenin saa asennettua komennolla, tämä asentaa Java 15

```
brew install maven
```

Koska ohjelma toimii ainoastaan Java 11, niin sen voi asentaa seuraavalla kommennolla

```
brew install java11
```

Seuraavaksi osoitetaan Mavenille Java Versio 11. Mavenin versiolla 3.6.3_1 tämä tapahtuu muokkaamalla tiedostoa /usr/local/Cellar/maven/3.6.3_1/bin/mvn esim. nanolla kommennolla

```
sudo nano /usr/local/Cellar/maven/3.6.3_1/bin/mvn
```

Varmista ja muokkaa tiedostopolkuun oikea versio version 3.6.3_1 tilalle

Muokkaa rivi

```
JAVA_HOME="${JAVA_HOME:-/usr/local/opt/openjdk/libexec/openjdk.jdk/Contents/Home}" exec "/usr/local/Cellar/maven/
```

Muotoon
```
JAVA_HOME="${JAVA_HOME:-/usr/local/opt/openjdk@11/libexec/openjdk.jdk/Contents/Home}" exec "/usr/local/Cellar/maven/
```

Eli muokkaa polkuun ```openjdk``` ```openjdk@11``` ja tallenna tiedosto. 

Voit tarkistaa komennolla ```mvn --version```, että Maven käyttää Javan versiota 11.

Käynistä terminaali uudestaan. Ja tarkista komennolla java --version että se näyttää versio 11.
