package com.proyecto.test;

import com.proyecto.Polinomio;
import org.junit.Assert;
import org.junit.Test;
import com.proyecto.GeneradorLLaves;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class TestGeneradorLlaves {

    @Test
    public void testGuardarLlaves(){
        BigDecimal llave= new BigDecimal("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
        GeneradorLLaves.guardarLlaves(4,7,llave,"Prueba");
        File testFile = new File("Prueba.frg");
        Assert.assertTrue(testFile.exists());
        Path path = Path.of("Prueba.frg");
        try {
            List<String> lineas= Files.readAllLines(path);
            Assert.assertEquals(7,lineas.size());
        }catch (IOException ex){}
        if(testFile.exists())
            testFile.delete();
        try {
            GeneradorLLaves.guardarLlaves(5,4,llave,"dad");
            Assert.fail();
        }catch (IllegalArgumentException ex){}
    }

    @Test
    public void testGenerarLlave(){
        Assert.assertTrue(GeneradorLLaves.generarLlave("Pepa")==null);
        BigDecimal[] coeficientes = {BigDecimal.valueOf(2), BigDecimal.valueOf(7), BigDecimal.valueOf(17), BigDecimal.valueOf(19)};
        Polinomio polinomio = new Polinomio(3, coeficientes);
        creaArchivodePrueba(polinomio);
        Assert.assertTrue(GeneradorLLaves.generarLlave("pepa.frg").compareTo(polinomio.coeficienteIndependiente())==0);
        File testFile = new File("pepa.frg");
        if(testFile.exists())
            testFile.delete();
    }

    private void creaArchivodePrueba(Polinomio p){
        StringBuilder contenido= new StringBuilder();
        for(int i=0;i<p.getGrado()+1;i++){
            BigDecimal[] punto=new BigDecimal[2];
            BigDecimal x=BigDecimal.valueOf(i);
            BigDecimal y= p.evaluarPolinomio(i);
            punto[0]=x;
            punto[1]=y;
            contenido.append(Arrays.toString(punto)).append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pepa.frg"))) {
            writer.write(contenido.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
