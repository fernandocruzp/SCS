package com.proyecto;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Polinomio {
    private int grado;
    private BigDecimal[] coeficientes;

    public Polinomio(int grado, BigDecimal[] coeficientes) throws IllegalArgumentException {
        if (grado != coeficientes.length - 1)
            throw new IllegalArgumentException("La cantidad de coeficientes de un polinomio es siempre igual a grado + 1");
        this.grado = grado;
        this.coeficientes = Arrays.copyOf(coeficientes, grado + 1);
    }

    public Polinomio(int grado, BigDecimal coef) {
        this.grado = grado;
        coeficientes = new BigDecimal[grado + 1];
        Arrays.fill(coeficientes,BigDecimal.ZERO);
        coeficientes[grado] = coef;
    }

    public void setCoeficientes(BigDecimal[] coeficientes) {
        this.coeficientes = Arrays.copyOf(coeficientes, coeficientes.length);
    }

    public int getGrado() {
        return grado;
    }

    public BigDecimal[] getCoeficientes() {
        return coeficientes;
    }

    public BigDecimal coeficienteIndependiente() throws NoSuchElementException {
        if (grado < 0)
            throw new NoSuchElementException("El polinomio no tiene término independiente");

        return coeficientes[0];
    }

    public BigDecimal evaluarPolinomio(int x) {
        BigDecimal valor = coeficienteIndependiente();
        for (int i = 1; i <= grado; i++) {
            BigDecimal term = coeficientes[i].multiply(BigDecimal.valueOf(x).pow(i));
            valor = valor.add(term);
        }
        return valor;
    }

    public boolean esPolCero() {
        for (BigDecimal i : coeficientes)
            if (i.compareTo(BigDecimal.ZERO) != 0)
                return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polinomio)) return false;
        Polinomio polinomio = (Polinomio) o;
        return getGrado() == polinomio.getGrado() && Arrays.equals(getCoeficientes(), polinomio.getCoeficientes());
    }

    @Override
    public String toString() {
        StringBuilder pol = new StringBuilder(coeficienteIndependiente().toString());
        for (int i = 1; i <= grado; i++) {
            pol.append(" + ").append(coeficientes[i]).append("(x^").append(i).append(")");
        }
        return pol.toString();
    }
}
