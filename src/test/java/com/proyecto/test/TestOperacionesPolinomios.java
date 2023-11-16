package com.proyecto.test;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;
import com.proyecto.Polinomio;
import com.proyecto.OperacionesPolinomios;

public class TestOperacionesPolinomios {

    @Test
    public void testSuma(){
        int grado = 2;
        int[] coeficientes = {1, 2, 3};
        int[] coeficientes2 = {2, 3, 5};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);
        OperacionesPolinomios suma = new OperacionesPolinomios();
        Polinomio res= suma.suma(polinomio,polinomio2);
        Assert. assertEquals("3 + 5(x^1) + 8(x^2)", res.toString());
        int grado2 = 4;
        int[] coeficientes3 = {2, 3, 5, 0, 3};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);
        res= suma.suma(polinomio,polinomio3);
        Assert. assertEquals("3 + 5(x^1) + 8(x^2) + 0(x^3) + 3(x^4)", res.toString());
        try{
            Polinomio res2=suma.suma(polinomio,null);
            Assert.fail();
        }
        catch (NullPointerException ex){}
    }
    @Test
    public void testResta(){
        int grado = 2;
        int[] coeficientes = {1, 2, 3};
        int[] coeficientes2 = {2, 3, 5};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);
        OperacionesPolinomios resta = new OperacionesPolinomios();
        Polinomio res= resta.resta(polinomio,polinomio2);
        Assert. assertEquals("-1 + -1(x^1) + -2(x^2)", res.toString());
        int grado2 = 4;
        int[] coeficientes3 = {2, 3, 5, 0, 3};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);
        res= resta.resta(polinomio,polinomio3);
        Assert. assertEquals("-1 + -1(x^1) + -2(x^2) + 0(x^3) + -3(x^4)", res.toString());
        try{
            Polinomio res2=resta.resta(polinomio,null);
            Assert.fail();
        }
        catch (NullPointerException ex){}
    }
    @Test
    public void testMulti(){
        int grado = 2;
        int[] coeficientes = {1, 2, 3};
        int[] coeficientes2 = {2, 3, 5};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);
        OperacionesPolinomios multi = new OperacionesPolinomios();
        Polinomio res= multi.multiplicacion(polinomio,polinomio2);
        Assert. assertEquals("2 + 7(x^1) + 17(x^2) + 19(x^3) + 15(x^4)", res.toString());
        int grado2 = 4;
        int[] coeficientes3 = {2, 3, 5, 0, 3};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);
        res= multi.multiplicacion(polinomio,polinomio3);
        Assert. assertEquals("2 + 7(x^1) + 17(x^2) + 19(x^3) + 18(x^4) + 6(x^5) + 9(x^6)", res.toString());
        try{
            Polinomio res2=multi.multiplicacion(polinomio,null);
            Assert.fail();
        }
        catch (NullPointerException ex){}
    }
}
