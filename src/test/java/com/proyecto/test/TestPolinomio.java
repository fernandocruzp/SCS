package com.proyecto.test;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;
import com.proyecto.Polinomio;

public class TestPolinomio {

    @Test
    public void testConstructor() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        Assert.assertEquals(grado, polinomio.getGrado());
    }

    @Test
    public void testConstructorError() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2)};
        try {
            new Polinomio(grado, coeficientes);
            Assert.fail();
        }
        catch (IllegalArgumentException ia){}
    }

    @Test
    public void testCoeficienteIndependiente() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        Assert.assertEquals(BigDecimal.valueOf(1), polinomio.coeficienteIndependiente());
    }

    @Test
    public void testEvaluarPolinomio() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        Assert.assertEquals(BigDecimal.valueOf(6), polinomio.evaluarPolinomio(1));
        Assert.assertEquals(BigDecimal.valueOf(17), polinomio.evaluarPolinomio(2));
    }

    @Test
    public void testEquals() {
        int grado = 2;
        BigDecimal[] coeficientes1 = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
        BigDecimal[] coeficientes2 = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        Polinomio polinomio1 = new Polinomio(grado, coeficientes1);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        Assert.assertTrue(polinomio1.equals(polinomio2));
    }

    @Test
    public void testNotEquals() {
        int grado = 2;
        BigDecimal[] coeficientes1 = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
        BigDecimal[] coeficientes2 = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(4)};

        Polinomio polinomio1 = new Polinomio(grado, coeficientes1);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        Assert.assertFalse(polinomio1.equals(polinomio2));
    }

    @Test
    public void testToString() {
        int grado = 2;
        BigDecimal[] coeficientes = {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        Assert. assertEquals("1 + 2(x^1) + 3(x^2)", polinomio.toString());
    }
}
