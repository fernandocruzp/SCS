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
        double[] coeficientes = {1, 2, 3};
        double[] coeficientes2 = {2, 3, 5};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);
        OperacionesPolinomios suma = new OperacionesPolinomios();
        Polinomio res= suma.suma(polinomio,polinomio2);
        Assert. assertEquals("3.0 + 5.0(x^1) + 8.0(x^2)", res.toString());
        int grado2 = 4;
        double[] coeficientes3 = {2, 3, 5, 0, 3};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);
        res= suma.suma(polinomio,polinomio3);
        Assert. assertEquals("3.0 + 5.0(x^1) + 8.0(x^2) + 0.0(x^3) + 3.0(x^4)", res.toString());
        try{
            Polinomio res2=suma.suma(polinomio,null);
            Assert.fail();
        }
        catch (NullPointerException ex){}
    }
    @Test
    public void testResta(){
        int grado = 2;
        double[] coeficientes = {1, 2, 3};
        double[] coeficientes2 = {2, 3, 5};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);
        OperacionesPolinomios resta = new OperacionesPolinomios();
        Polinomio res= resta.resta(polinomio,polinomio2);
        Assert. assertEquals("-1.0 + -1.0(x^1) + -2.0(x^2)", res.toString());
        int grado2 = 4;
        double[] coeficientes3 = {2, 3, 5, 0, 3};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);
        res= resta.resta(polinomio,polinomio3);
        Assert. assertEquals("-1.0 + -1.0(x^1) + -2.0(x^2) + 0.0(x^3) + -3.0(x^4)", res.toString());
        try{
            Polinomio res2=resta.resta(polinomio,null);
            Assert.fail();
        }
        catch (NullPointerException ex){}
    }
    @Test
    public void testMulti(){
        int grado = 2;
        double[] coeficientes = {1, 2, 3};
        double[] coeficientes2 = {2, 3, 5};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);
        OperacionesPolinomios multi = new OperacionesPolinomios();
        Polinomio res= multi.multiplicacion(polinomio,polinomio2);
        Assert. assertEquals("2.0 + 7.0(x^1) + 17.0(x^2) + 19.0(x^3) + 15.0(x^4)", res.toString());
        int grado2 = 4;
        double[] coeficientes3 = {2, 3, 5, 0, 3};
        Polinomio polinomio3 = new Polinomio(grado2, coeficientes3);
        res= multi.multiplicacion(polinomio,polinomio3);
        Assert. assertEquals("2.0 + 7.0(x^1) + 17.0(x^2) + 19.0(x^3) + 18.0(x^4) + 6.0(x^5) + 9.0(x^6)", res.toString());
        try{
            Polinomio res2=multi.multiplicacion(polinomio,null);
            Assert.fail();
        }
        catch (NullPointerException ex){}
    }

    @Test
    public void testDivi(){
        int grado = 2;
        double[] coeficientes = {1, 2, 3};
        double[] coeficientes2 = {2, 7, 17, 19, 15};
        Polinomio polinomio = new Polinomio(grado, coeficientes);
        Polinomio polinomio2 = new Polinomio(4, coeficientes2);
        OperacionesPolinomios div = new OperacionesPolinomios();
        Polinomio res= div.division(polinomio,polinomio2);
        Assert. assertEquals("2.0 + 3.0(x^1) + 5.0(x^2)", res.toString());
        try{
            Polinomio res2=div.division(polinomio,null);
            Assert.fail();
            res2=div.division(polinomio2,polinomio);
            Assert.fail();
        }
        catch (NullPointerException ex){}
        catch (IllegalArgumentException ix){}
    }

}
