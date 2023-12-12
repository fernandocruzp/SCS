/**
 * Clase de prueba para la clase GeneradorLlaves.
 */
package com.proyecto.test;

import com.proyecto.Polinomio;
import com.proyecto.GeneradorLLaves;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que contiene pruebas unitarias para la clase GeneradorLlaves.
 */
public class TestGeneradorLlaves {

    /**
     * Prueba el método para guardar llaves en un archivo.
     */
    @Test
    public void testGuardarLlaves() {
        // Definir una llave de prueba
        BigDecimal llave = new BigDecimal("12345678901234567890123456789012345678901234567890123456789012345678901234567890");

        // Llamar al método para guardar llaves
        GeneradorLLaves.guardarLlaves(4, 7, llave, "Prueba");

        // Verificar que el archivo de prueba existe
        File testFile = new File("Prueba.frg");
        Assert.assertTrue(testFile.exists());

        // Verificar que el archivo tiene la cantidad esperada de líneas
        Path path = Path.of("Prueba.frg");
        try {
            List<String> lineas = Files.readAllLines(path);
            Assert.assertEquals(7, lineas.size());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Eliminar el archivo de prueba si existe
        if (testFile.exists())
            testFile.delete();

        // Verificar que se lanza una excepción si se intenta guardar llaves con parámetros inválidos
        try {
            GeneradorLLaves.guardarLlaves(5, 4, llave, "dad");
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            // Excepción esperada
        }
    }

    /**
     * Prueba el método para generar una llave a partir de un archivo.
     */
    @Test
    public void testGenerarLlave() {
        // Verificar que se retorna null si el archivo de entrada no existe
        Assert.assertTrue(GeneradorLLaves.generarLlave("Pepa") == null);

        // Crear un polinomio de prueba y escribirlo en un archivo de prueba
        BigDecimal[] coeficientes = {BigDecimal.valueOf(2), BigDecimal.valueOf(7), BigDecimal.valueOf(17), BigDecimal.valueOf(19)};
        Polinomio polinomio = new Polinomio(3, coeficientes);
        creaArchivoDePrueba(polinomio);

        // Verificar que la llave generada coincide con el coeficiente independiente del polinomio
        Assert.assertTrue(GeneradorLLaves.generarLlave("pepa.frg").compareTo(polinomio.coeficienteIndependiente()) == 0);

        // Eliminar el archivo de prueba si existe
        File testFile = new File("pepa.frg");
        if (testFile.exists())
            testFile.delete();
    }

    /**
     * Método auxiliar para crear un archivo de prueba con los puntos de un polinomio.
     */
    private void creaArchivoDePrueba(Polinomio p) {
        StringBuilder contenido = new StringBuilder();
        for (int i = 0; i < p.getGrado() + 1; i++) {
            BigDecimal[] punto = new BigDecimal[2];
            BigDecimal x = BigDecimal.valueOf(i);
            BigDecimal y = p.evaluarPolinomio(i);
            punto[0] = x;
            punto[1] = y;
            contenido.append(Arrays.toString(punto)).append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pepa.frg"))) {
            writer.write(contenido.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
