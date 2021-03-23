package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void SaldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void RahanKasvatus(){
        kortti.lataaRahaa(100);

        assertEquals("saldo: 1.10", kortti.toString());
    }

    @Test
    public void RahanOttaminen(){
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }

    @Test
    public void SaldoEiMuutu(){
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void RahanOttaminenBoolean(){

        //kortti.otaRahaa(11);
        kortti.otaRahaa(8);

        boolean arvo = false;
        arvo = kortti.saldo() != 10;
        assertTrue(arvo);
    }

}
