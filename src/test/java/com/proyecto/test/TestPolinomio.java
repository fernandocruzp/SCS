/**
 * Clase de prueba para la clase Polinomio.
 */
package com.proyecto.test;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import com.proyecto.Polinomio;

/**
 * Clase que contiene pruebas unitarias para la clase Polinomio.
 */
public class TestPolinomio {

    /**
     * Prueba el constructor de la clase Polinomio.
     */
    @Test
    public void testConstructor() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        // Crear un polinomio
        Polinomio polinomio = new Polinomio(grado, coeficientes);

        // Verificar que el grado del polinomio es el esperado
        Assert.assertEquals(grado, polinomio.getGrado());
    }

    /**
     * Prueba el constructor de la clase Polinomio con un error esperado.
     */
    @Test
    public void testConstructorError() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2)};

        // Verificar que se lanza una excepción IllegalArgumentException
        try {
            new Polinomio(grado, coeficientes);
            Assert.fail();
        } catch (IllegalArgumentException ia) {
            // Excepción esperada
        }
    }

    /**
     * Prueba el método coeficienteIndependiente de la clase Polinomio.
     */
    @Test
    public void testCoeficienteIndependiente() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        // Crear un polinomio
        Polinomio polinomio = new Polinomio(grado, coeficientes);

        // Verificar que el coeficiente independiente del polinomio es el esperado
        Assert.assertEquals(BigDecimal.valueOf(1), polinomio.coeficienteIndependiente());
    }

    /**
     * Prueba el método evaluarPolinomio de la clase Polinomio.
     */
    @Test
    public void testEvaluarPolinomio() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        // Crear un polinomio
        Polinomio polinomio = new Polinomio(grado, coeficientes);

        // Verificar que la evaluación del polinomio en x=1 es el esperado
        Assert.assertEquals(BigDecimal.valueOf(6), polinomio.evaluarPolinomio(1));

        // Verificar que la evaluación del polinomio en x=2 es el esperado
        Assert.assertEquals(BigDecimal.valueOf(17), polinomio.evaluarPolinomio(2));
    }

    /**
     * Prueba el método equals de la clase Polinomio.
     */
    @Test
    public void testEquals() {
        int grado = 2;
        BigDecimal[] coeficientes1 = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
        BigDecimal[] coeficientes2 = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        // Crear dos polinomios con los mismos coeficientes
        Polinomio polinomio1 = new Polinomio(grado, coeficientes1);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        // Verificar que los polinomios son iguales
        Assert.assertTrue(polinomio1.equals(polinomio2));
    }

    /**
     * Prueba el método equals de la clase Polinomio cuando los polinomios no son iguales.
     */
    @Test
    public void testNotEquals() {
        int grado = 2;
        BigDecimal[] coeficientes1 = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
        BigDecimal[] coeficientes2 = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(4)};

        // Crear dos polinomios con coeficientes diferentes
        Polinomio polinomio1 = new Polinomio(grado, coeficientes1);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        // Verificar que los polinomios no son iguales
        Assert.assertFalse(polinomio1.equals(polinomio2));
    }

    /**
     * Prueba el método toString de la clase Polinomio.
     */
    @Test
    public void testToString() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        // Crear un polinomio
        Polinomio polinomio = new Polinomio(grado, coeficientes);

        // Verificar que la representación en cadena del polinomio es la esperada
        Assert.assertEquals("1 + 2(x^1) + 3(x^2)", polinomio.toString());
    }
}
