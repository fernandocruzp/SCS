package com.proyecto;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Clase que realiza operaciones comunes sobre polinomios.
 */
public class OperacionesPolinomios {


    /**
     * Realiza la suma de dos polinomios.
     *
     * @param p1 El primer polinomio.
     * @param p2 El segundo polinomio.
     * @return Un nuevo polinomio que es el resultado de la suma.
     * @throws NullPointerException Si alguno de los polinomios es nulo.
     */
    public Polinomio suma(Polinomio p1, Polinomio p2) throws NullPointerException {
        if (p1 == null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinomios para sumar");
        int n = p1.getGrado();
        int m = p2.getGrado(), i = 0;
        int grado = (n >= m) ? n : m;
        BigDecimal[] coef = new BigDecimal[grado + 1];
        while (m >= 0 || n >= 0) {
            BigDecimal p11, p22;
            if (n < 0)
                p11 = BigDecimal.ZERO;
            else
                p11 = p1.getCoeficientes()[i];
            if (m < 0)
                p22 = BigDecimal.ZERO;
            else
                p22 = (p2.getCoeficientes()[i]);
            coef[i] = p11.add(p22);
            m--;
            n--;
            i++;
        }
        return new Polinomio(grado, coef);
    }
    /**
     * Realiza la resta de dos polinomios.
     *
     * @param p1 El primer polinomio.
     * @param p2 El segundo polinomio.
     * @return Un nuevo polinomio que es el resultado de la resta.
     * @throws NullPointerException Si alguno de los polinomios es nulo.
     */
    public Polinomio resta(Polinomio p1, Polinomio p2) throws NullPointerException {
        if (p1 == null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinomios para restar");
        int n = p1.getGrado();
        int m = p2.getGrado(), i = 0;
        int grado = (n >= m) ? n : m;
        BigDecimal[] coef = new BigDecimal[grado + 1];
        while (m >= 0 || n >= 0) {
            BigDecimal p11, p22;
            if (n < 0)
                p11 = BigDecimal.ZERO;
            else
                p11 = p1.getCoeficientes()[i];
            if (m < 0)
                p22 = BigDecimal.ZERO;
            else
                p22 = p2.getCoeficientes()[i];
            coef[i] = p11.subtract(p22);
            m--;
            n--;
            i++;
        }
        return new Polinomio(grado, coef);
    }
    /**
     * Realiza la multiplicación de dos polinomios.
     *
     * @param p1 El primer polinomio.
     * @param p2 El segundo polinomio.
     * @return Un nuevo polinomio que es el resultado de la resta.
     * @throws NullPointerException Si alguno de los polinomios es nulo.
     */
    public Polinomio multiplicacion(Polinomio p1, Polinomio p2) throws NullPointerException {
        if (p1 == null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinomios para multiplicar");
        int n = p1.getGrado();
        int m = p2.getGrado();
        BigDecimal[] coef = new BigDecimal[n + m + 1];
        Arrays.fill(coef, BigDecimal.ZERO);
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                BigDecimal producto=p1.getCoeficientes()[i].multiply(p2.getCoeficientes()[j]);
                coef[i + j] = coef[i + j].add(producto);
            }
        }
        return new Polinomio(n + m, coef);
    }

    /**
     * Realiza la multiplicación de un polinomio por un escalar.
     *
     * @param p El polinomio.
     * @param i El escalar.
     * @return Un nuevo polinomio que es el resultado de la multiplicación por el escalar.
     */
    public Polinomio productoEscalar(Polinomio p, BigDecimal i) {
        BigDecimal[] m = new BigDecimal[p.getGrado() + 1];
        for (int j = 0; j < p.getGrado() + 1; j++)
            m[j] = p.getCoeficientes()[j].multiply(i);
        return new Polinomio(p.getGrado(), m);
    }
    /**
     * Realiza la división de dos polinomios.
     *
     * @param p1 El numerador (polinomio de grado menor o igual).
     * @param p2 El denominador (polinomio de grado mayor o igual).
     * @return Un nuevo polinomio que es el resultado de la división.
     * @throws IllegalArgumentException Si el grado del numerador no es menor o igual al del denominador.
     * @throws NullPointerException     Si alguno de los polinomios es nulo.
     */
    public Polinomio division(Polinomio p1, Polinomio p2) throws IllegalArgumentException, NullPointerException {
        if (p1 == null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinomios para dividir");
        if (p1.getGrado() > p2.getGrado())
            throw new IllegalArgumentException("Primero ingresa el polinomio de grado menor");
        BigDecimal[] coeficientes=new BigDecimal[p2.getGrado() - p1.getGrado() + 1];
        Arrays.fill(coeficientes,BigDecimal.ZERO);
        Polinomio p3 = new Polinomio(p2.getGrado() - p1.getGrado(), coeficientes);
        return divisionR(p1, p2, p3);
    }

    private Polinomio divisionR(Polinomio p1, Polinomio p2, Polinomio p3) {
        if (p2.esPolCero())
            return p3;
        BigDecimal[] coef = Arrays.copyOf(p3.getCoeficientes(), p3.getGrado() + 1);
        int posicion = p2.getGrado();
        while (p2.getCoeficientes()[posicion].compareTo(BigDecimal.ZERO) == 0) {
            posicion--;
        }
        if (posicion < p1.getGrado())
            return p3;
        coef[posicion - p1.getGrado()] = p2.getCoeficientes()[posicion].divide(p1.getCoeficientes()[p1.getGrado()]);
        Polinomio multiplicador = new Polinomio(posicion-p1.getGrado(), coef[posicion - p1.getGrado()]);
        p3.setCoeficientes(coef);
        return divisionR(p1, resta(p2, multiplicacion(p1, multiplicador)), p3);
    }
    /**
     * Realiza la interpolación de Lagrange para un conjunto de puntos.
     *
     * @param puntos La matriz de puntos donde cada fila es un par (x, y).
     * @return El polinomio resultante de la interpolación de Lagrange.
     * @throws NullPointerException Si la matriz de puntos es nula.
     */
    public Polinomio interpolacionLagrange(BigDecimal[][] puntos) throws NullPointerException {
        if (puntos == null)
            throw new NullPointerException("No se recibieron puntos");
        Polinomio[] polinomios = Arrays.copyOf(generarPolinomios(puntos), puntos.length);
        Polinomio polinomioProd = prodPolinomio(polinomios);
        Polinomio res = productoEscalar(prodcutoLagrange(0, puntos, polinomioProd, polinomios), puntos[0][1]);
        for (int i = 1; i < puntos.length; i++) {
            res = suma(res, productoEscalar(prodcutoLagrange(i, puntos, polinomioProd, polinomios), puntos[i][1]));
        }
        System.out.println(res);
        System.out.println(redondeo(res));
        return redondeo(res);
    }
    /**
     * Redondea los coeficientes de un polinomio a una décima.
     *
     * @param p El polinomio a redondear.
     * @return Un nuevo polinomio con los coeficientes redondeados.
     */
    private Polinomio redondeo(Polinomio p) {
        BigDecimal[] coeficientes = new BigDecimal[p.getGrado() + 1];
        for (int i = 0; i <= p.getGrado(); i++) {
            coeficientes[i] = p.getCoeficientes()[i].setScale(1, BigDecimal.ROUND_HALF_UP);
        }
        p.setCoeficientes(coeficientes);
        return p;
    }
    /**
     * Realiza el producto de varios polinomios.
     *
     * @param polinomios Los polinomios a multiplicar.
     * @return Un nuevo polinomio que es el resultado del producto.
     */
    private Polinomio prodPolinomio(Polinomio[] polinomios) {
        Polinomio p = polinomios[0];
        for (int i = 1; i < polinomios.length; i++) {
            p = multiplicacion(p, polinomios[i]);
        }
        return p;
    }

    /**
     * Genera polinomios de primer grado a partir de puntos dados.
     *
     * @param puntos La matriz de puntos donde cada fila es un par (x, y).
     * @return Un arreglo de polinomios de primer grado.
     */
    private Polinomio[] generarPolinomios(BigDecimal[][] puntos) {
        Polinomio[] polinomios = new Polinomio[puntos.length];
        int i = 0;
        for (BigDecimal[] punto : puntos) {
            BigDecimal[] n = {punto[0].negate(), BigDecimal.ONE};
            polinomios[i] = new Polinomio(1, n);
            i++;
        }
        return polinomios;
    }
    /**
     * Calcula el producto de Lagrange para un punto específico.
     *
     * @param i          El índice del punto en la matriz de puntos.
     * @param x          La matriz de puntos donde cada fila es un par (x, y).
     * @param polinomio  El producto de los polinomios generados.
     * @param polinomios Los polinomios generados a partir de los puntos.
     * @return El resultado del producto de Lagrange para el punto especificado.
     */
    private Polinomio prodcutoLagrange(int i, BigDecimal[][] x, Polinomio polinomio, Polinomio[] polinomios) {
        Polinomio res = division(polinomios[i], polinomio);
        BigDecimal prod = BigDecimal.ONE;
        for (int j = 0; j < x.length; j++) {
            if (j == i)
                continue;
            prod = prod.multiply(x[i][0].subtract(x[j][0]));
        }
        BigDecimal divisor = BigDecimal.ONE.divide(prod, 20, BigDecimal.ROUND_HALF_UP);
        res = productoEscalar(res, divisor);
        return res;
    }
}
