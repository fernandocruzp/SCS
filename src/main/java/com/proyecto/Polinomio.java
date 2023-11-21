package com.proyecto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Representa un polinomio con coeficientes BigDecimal.
 */
public class Polinomio {
    private int grado; // Grado del polinomio
    private BigDecimal[] coeficientes; // Coeficientes del polinomio

    /**
     * Construye un polinomio a partir de un grado y una matriz de coeficientes.
     *
     * @param grado        El grado del polinomio.
     * @param coeficientes La matriz de coeficientes del polinomio.
     * @throws IllegalArgumentException Si la cantidad de coeficientes no coincide con el grado + 1.
     */
    public Polinomio(int grado, BigDecimal[] coeficientes) throws IllegalArgumentException {
        if (grado != coeficientes.length - 1)
            throw new IllegalArgumentException("La cantidad de coeficientes de un polinomio es siempre igual a grado + 1");
        this.grado = grado;
        this.coeficientes = Arrays.copyOf(coeficientes, grado + 1);
    }

    /**
     * Construye un polinomio a partir de un grado y un coeficiente.
     *
     * @param grado El grado del polinomio.
     * @param coef  El coeficiente del término de mayor grado.
     */
    public Polinomio(int grado, BigDecimal coef) {
        this.grado = grado;
        coeficientes = new BigDecimal[grado + 1];
        Arrays.fill(coeficientes, BigDecimal.ZERO);
        coeficientes[grado] = coef;
    }

    /**
     * Establece los coeficientes del polinomio.
     *
     * @param coeficientes La matriz de coeficientes del polinomio.
     */
    public void setCoeficientes(BigDecimal[] coeficientes) {
        this.coeficientes = Arrays.copyOf(coeficientes, coeficientes.length);
    }

    /**
     * Obtiene el grado del polinomio.
     *
     * @return El grado del polinomio.
     */
    public int getGrado() {
        return grado;
    }

    /**
     * Obtiene los coeficientes del polinomio.
     *
     * @return La matriz de coeficientes del polinomio.
     */
    public BigDecimal[] getCoeficientes() {
        return coeficientes;
    }

    /**
     * Calcula y devuelve el coeficiente independiente del polinomio.
     *
     * @return El coeficiente independiente.
     * @throws NoSuchElementException Si el polinomio no tiene término independiente.
     */
    public BigDecimal coeficienteIndependiente() throws NoSuchElementException {
        if (grado < 0)
            throw new NoSuchElementException("El polinomio no tiene término independiente");

        return coeficientes[0];
    }

    /**
     * Evalúa el polinomio para un valor dado de x.
     *
     * @param x El valor para evaluar el polinomio.
     * @return El resultado de la evaluación.
     */
    public BigDecimal evaluarPolinomio(int x) {
        BigDecimal valor = coeficienteIndependiente();
        for (int i = 1; i <= grado; i++) {
            BigDecimal term = coeficientes[i].multiply(BigDecimal.valueOf(x).pow(i));
            valor = valor.add(term);
        }
        return valor;
    }

    /**
     * Verifica si el polinomio es el polinomio cero.
     *
     * @return true si es el polinomio cero, false de lo contrario.
     */
    public boolean esPolCero() {
        for (BigDecimal i : coeficientes)
            if (i.compareTo(BigDecimal.ZERO) != 0)
                return false;
        return true;
    }

    /**
     * Compara el polinomio con otro objeto para verificar la igualdad.
     *
     * @param o El objeto a comparar.
     * @return true si son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polinomio)) return false;
        Polinomio polinomio = (Polinomio) o;
        return getGrado() == polinomio.getGrado() && Arrays.equals(getCoeficientes(), polinomio.getCoeficientes());
    }

    /**
     * Devuelve una representación en cadena del polinomio.
     *
     * @return La representación en cadena del polinomio.
     */
    @Override
    public String toString() {
        StringBuilder pol = new StringBuilder(coeficienteIndependiente().toString());
        for (int i = 1; i <= grado; i++) {
            pol.append(" + ").append(coeficientes[i]).append("(x^").append(i).append(")");
        }
        return pol.toString();
    }
}

