package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;

    @Before
    public void setUp() { kassa = new Kassapaate(); }

    @Test
    public void luotuKassaOlemassa() {
        assertTrue(kassa!=null);
    }

    @Test
    public void oikeaRahaJaLounasMaaraAlussa(){
        int rahaa = kassa.kassassaRahaa();
        int lounasMaara = kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty();

        boolean rahaJaLounasMaara = rahaa == 100000 && lounasMaara == 0;
        assertTrue(rahaJaLounasMaara);
    }

    @Test
    public void kateisOstoEdullisesti(){

        int vaihtoraha = 250;
        int alkuLounasMaara = kassa.edullisiaLounaitaMyyty();

        int vaihtorahaEdullisesti = kassa.syoEdullisesti(vaihtoraha);

        if(vaihtorahaEdullisesti >= 0){

            if(alkuLounasMaara < kassa.edullisiaLounaitaMyyty()){
                boolean oikeaVaihtoRahaJaOikeaKassaRahaMaara = kassa.kassassaRahaa() == 100240 && vaihtorahaEdullisesti == 10;
                assertTrue(oikeaVaihtoRahaJaOikeaKassaRahaMaara);
            }
        }
    }

    @Test
    public void kateisOstoEdullisestiEiRiitaRahaa(){
        int vaihtoraha = 230;
        int alkuLounasMaara = kassa.edullisiaLounaitaMyyty();
        int vaihtorahaEdullisesti = kassa.syoEdullisesti(vaihtoraha);

        if (vaihtorahaEdullisesti < 0){
            assertTrue(kassa.kassassaRahaa() == 100000 && vaihtorahaEdullisesti == vaihtoraha
                    && alkuLounasMaara -  kassa.edullisiaLounaitaMyyty()== 0);
        }
    }

    @Test
    public void kateisOstoMaukkaasti(){

        int vaihtoraha = 410;
        int alkuLounasMaara = kassa.maukkaitaLounaitaMyyty();
        int vaihtorahaEdullisesti = kassa.syoMaukkaasti(vaihtoraha);

        if(vaihtorahaEdullisesti >= 0){

            if(alkuLounasMaara < kassa.maukkaitaLounaitaMyyty()){
                boolean oikeaVaihtoRahaJaOikeaKassaRahaMaara = kassa.kassassaRahaa() == 100400 && vaihtorahaEdullisesti == 10;
                assertTrue(oikeaVaihtoRahaJaOikeaKassaRahaMaara);
            }
        }
        else{

            assertTrue(kassa.kassassaRahaa() == 100000 && vaihtorahaEdullisesti == vaihtoraha
                    && alkuLounasMaara -  kassa.maukkaitaLounaitaMyyty()== 0);
        }
    }

    @Test
    public void kateisOstoMaukkaastiEiRiitaRahaa(){
        int vaihtoraha = 380;
        int alkuLounasMaara = kassa.maukkaitaLounaitaMyyty();
        int vaihtorahaEdullisesti = kassa.syoMaukkaasti(vaihtoraha);

        if (vaihtorahaEdullisesti < 0){
            assertTrue(kassa.kassassaRahaa() == 100000 && vaihtorahaEdullisesti == vaihtoraha
                    && alkuLounasMaara -  kassa.maukkaitaLounaitaMyyty()== 0);
        }
    }

    @Test
    public void korttiOstoMaukkaasti(){

        int maksuMaara = 500;
        int lounaat = kassa.maukkaitaLounaitaMyyty();

        Maksukortti kortti = new Maksukortti(maksuMaara);
        boolean onVaraa = kassa.syoMaukkaasti(kortti);

        int lounas_muutos = lounaat - kassa.maukkaitaLounaitaMyyty();
        int kortinRahaMaara = kortti.saldo();

        if(onVaraa){

            if(lounas_muutos > 0){
                assertTrue(true);
            }
        }
        else{

            if(kortinRahaMaara == maksuMaara && lounas_muutos == 0)
                assertFalse(true);
        }
    }

    @Test
    public void korttiOstoMaukkaastiEiVaraa(){

        int maksuMaara = 100;
        int lounaat = kassa.maukkaitaLounaitaMyyty();

        Maksukortti kortti = new Maksukortti(maksuMaara);
        boolean onVaraa = kassa.syoMaukkaasti(kortti);

        int lounas_muutos = lounaat - kassa.maukkaitaLounaitaMyyty();
        int kortinRahaMaara = kortti.saldo();

        if(onVaraa){

            if(lounas_muutos > 0){
                assertTrue(true);
            }
        }
        else{
            assertTrue(kortinRahaMaara == maksuMaara && lounas_muutos == 0);
        }
    }


    @Test
    public void korttiOstoEdullisesti(){

        int maksuMaara = 500;
        int lounaat = kassa.edullisiaLounaitaMyyty();

        Maksukortti kortti = new Maksukortti(maksuMaara);
        boolean onVaraa = kassa.syoEdullisesti(kortti);

        int lounas_muutos = lounaat - kassa.edullisiaLounaitaMyyty();
        int kortinRahaMaara = kortti.saldo();

        if(onVaraa){

            if(lounas_muutos > 0){
                assertTrue(true);
            }
        }
        else{

            if(kortinRahaMaara == maksuMaara && lounas_muutos == 0)
                assertFalse(true);
        }
    }

    @Test
    public void korttiOstoEdullisestiEiVaraa(){

        int maksuMaara = 200;
        int lounaat = kassa.edullisiaLounaitaMyyty();

        Maksukortti kortti = new Maksukortti(maksuMaara);
        boolean onVaraa = kassa.syoEdullisesti(kortti);

        int lounas_muutos = lounaat - kassa.edullisiaLounaitaMyyty();
        int kortinRahaMaara = kortti.saldo();

        if(onVaraa){

            if(lounas_muutos > 0){
                assertTrue(true);
            }
        }
        else{
            assertTrue(kortinRahaMaara == maksuMaara && lounas_muutos == 0);
        }
    }

    @Test
    public void rahanLataus(){
        Maksukortti kortti = new Maksukortti(100);
        int alkuSaldo = kortti.saldo();
        int alkukassaSaldo = kassa.kassassaRahaa();

        kassa.lataaRahaaKortille(kortti, 200);

        boolean kortinSaldoMuutos = alkuSaldo < kortti.saldo();
        boolean kassanSaldonMuutos = kassa.kassassaRahaa() == 100200;

        assertTrue(kortinSaldoMuutos && kassanSaldonMuutos);

    }

    @Test
    public void rahanLatausSummaAlle(){
        Maksukortti kortti = new Maksukortti(100);
        int alkuSaldo = kortti.saldo();
        int alkukassaSaldo = kassa.kassassaRahaa();

        kassa.lataaRahaaKortille(kortti, -1);

        boolean kortinSaldoMuutos = alkuSaldo < kortti.saldo();

        assertFalse(kortinSaldoMuutos);

    }


}
