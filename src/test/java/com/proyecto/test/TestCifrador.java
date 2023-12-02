package com.proyecto.test;
import com.proyecto.Cifrador;

public class TestCifrador{
    //Test constuctor: que cree objeto de instancia
    @Test
    public void testCifrador(){
        byte[] cadena= null;
        Cifrador cifrador = new Cifrador(cadena);
        Assert. assertEquals();
    }

    //Texto corto que se cifre y decifre
    @Test
    public void testCifra(){
        String cifra = "Hola mundo";
        String cifrado = cifrar(cifra);
        Assert. assertEquals(cifra,cifrado);
    }

    //No tengo ni idea de como hacer test XD
    @Test
    public void testDecifra(){
        String cifra = "Adiós mundo";
        String decifrado = decifrar(cifra);
        Assert. assertEquals(cifra,decifrado);
    }
}