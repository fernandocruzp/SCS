/**
 * Clase de prueba para la clase OperacionesPolinomios.
 */
package com.proyecto.test;

import org.junit.Assert;
import org.junit.Test;
import com.proyecto.Polinomio;
import com.proyecto.OperacionesPolinomios;

import java.math.BigDecimal;

/**
 * Clase que contiene pruebas unitarias para la clase OperacionesPolinomios.
 */
public class TestOperacionesPolinomios {

    /**
     * Prueba la operación de suma de polinomios.
     */
    @Test
    public void testSuma() {
        // Crear polinomios de prueba
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
        BigDecimal[] coeficientes2 = {BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(5)};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        // Realizar la suma de polinomios
        OperacionesPolinomios suma = new OperacionesPolinomios();
        Polinomio res = suma.suma(polinomio, polinomio2);

        // Verificar que el resultado es el esperado
        Assert.assertEquals("3 + 5(x^1) + 8(x^2)", res.toString());

        // Crear polinomios de prueba con diferente grado
        int grado2 = 4;
        BigDecimal[] coeficientes3 = {BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(5), BigDecimal.ZERO, BigDecimal.valueOf(3)};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);

        // Realizar la suma de polinomios con diferente grado
        res = suma.suma(polinomio, polinomio3);

        // Verificar que el resultado es el esperado
        Assert.assertEquals("3 + 5(x^1) + 8(x^2) + 0(x^3) + 3(x^4)", res.toString());

        // Verificar que se lanza una excepción si uno de los polinomios es nulo
        try {
            Polinomio res2 = suma.suma(polinomio, null);
            Assert.fail();
        } catch (NullPointerException ex) {
            // Excepción esperada
        }
    }

    /**
     * Prueba la operación de resta de polinomios.
     */
    @Test
    public void testResta() {
        // Crear polinomios de prueba
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
        BigDecimal[] coeficientes2 = {BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(5)};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        // Realizar la resta de polinomios
        OperacionesPolinomios resta = new OperacionesPolinomios();
        Polinomio res = resta.resta(polinomio, polinomio2);

        // Verificar que el resultado es el esperado
        Assert.assertEquals("-1 + -1(x^1) + -2(x^2)", res.toString());

        // Crear polinomios de prueba con diferente grado
        int grado2 = 4;
        BigDecimal[] coeficientes3 = {BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(5), BigDecimal.ZERO, BigDecimal.valueOf(3)};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);

        // Realizar la resta de polinomios con diferente grado
        res = resta.resta(polinomio, polinomio3);

        // Verificar que el resultado es el esperado
        Assert.assertEquals("-1 + -1(x^1) + -2(x^2) + 0(x^3) + -3(x^4)", res.toString());

        // Verificar que se lanza una excepción si uno de los polinomios es nulo
        try {
            Polinomio res2 = resta.resta(polinomio, null);
            Assert.fail();
        } catch (NullPointerException ex) {
            // Excepción esperada
        }
    }

    /**
     * Prueba la operación de multiplicación de polinomios.
     */
    @Test
    public void testMultiplicacion() {
        // Crear polinomios de prueba
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
        BigDecimal[] coeficientes2 = {BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(5)};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        // Realizar la multiplicación de polinomios
        OperacionesPolinomios multi = new OperacionesPolinomios();
        Polinomio res = multi.multiplicacion(polinomio, polinomio2);

        // Verificar que el resultado es el esperado
        Assert.assertEquals("2 + 7(x^1) + 17(x^2) + 19(x^3) + 15(x^4)", res.toString());

        // Crear polinomios de prueba con diferente grado
        int grado2 = 4;
        BigDecimal[] coeficientes3 = {BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(5), BigDecimal.ZERO, BigDecimal.valueOf(3)};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);

        // Realizar la multiplicación de polinomios con diferente grado
        res = multi.multiplicacion(polinomio, polinomio3);

        // Verificar que el resultado es el esperado
        Assert.assertEquals("2 + 7(x^1) + 17(x^2) + 19(x^3) + 18(x^4) + 6(x^5) + 9(x^6)", res.toString());

        // Verificar que se lanza una excepción si uno de los polinomios es nulo
        try {
            Polinomio res2 = multi.multiplicacion(polinomio, null);
            Assert.fail();
        } catch (NullPointerException ex) {
            // Excepción esperada
        }
    }

    /**
     * Prueba la operación de división de polinomios.
     */
    @Test
    public void testDivision() {
        // Crear polinomios de prueba
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
        BigDecimal[] coeficientes2 = {BigDecimal.valueOf(2), BigDecimal.valueOf(7), BigDecimal.valueOf(17), BigDecimal.valueOf(19), BigDecimal.valueOf(15)};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(4, coeficientes2);

        // Realizar la división de polinomios
        OperacionesPolinomios div = new OperacionesPolinomios();
        Polinomio res = div.division(polinomio, polinomio2);

        // Verificar que el resultado es el esperado
        Assert.assertEquals("2 + 3(x^1) + 5(x^2)", res.toString());

        // Verificar que se lanza una excepción si uno de los polinomios es nulo
        try {
            Polinomio res2 = div.division(polinomio, null);
            Assert.fail();
            res2 = div.division(polinomio2, polinomio);
            Assert.fail();
        } catch (NullPointerException | IllegalArgumentException ex) {
            // Excepción esperada
        }
    }

    /**
     * Prueba la operación de interpolación de Lagrange.
     */
    @Test
    public void testInterpolacion() {
        // Crear polinomio de prueba
        BigDecimal[] coeficientes = {BigDecimal.valueOf(2), BigDecimal.valueOf(7), BigDecimal.valueOf(17), BigDecimal.valueOf(19)};
        Polinomio polinomio = new Polinomio(3, coeficientes);

        // Definir puntos para la interpolación de Lagrange
        BigDecimal[] punto1 = {BigDecimal.ZERO, BigDecimal.valueOf(2)};
        BigDecimal[] punto2 = {BigDecimal.valueOf(3), polinomio.evaluarPolinomio(3)};
        BigDecimal[] punto3 = {BigDecimal.valueOf(7), polinomio.evaluarPolinomio(7)};
        BigDecimal[] punto4 = {BigDecimal.valueOf(12), polinomio.evaluarPolinomio(12)};
        BigDecimal[][] puntos = {punto1, punto2, punto3, punto4};

        // Realizar la interpolación de Lagrange
        OperacionesPolinomios lagrange = new OperacionesPolinomios();
        Polinomio res = lagrange.interpolacionLagrange(puntos);

        // Verificar que el resultado es el esperado
        Assert.assertEquals("2.0 + 7.0(x^1) + 17.0(x^2) + 19.0(x^3)", res.toString());
    }
}

