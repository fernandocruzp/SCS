package com.proyecto;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Polinomio {
    private int grado;
    private double[] coeficientes;


    public Polinomio(int grado, double[] coeficientes) throws IllegalArgumentException{
        if(grado!= coeficientes.length-1)
            throw new IllegalArgumentException("La cantidad de coeficientes de un polinimio es siempre igual a grado +1");
        this.grado = grado;
        this.coeficientes=Arrays.copyOf(coeficientes,grado+1);

    }

    public Polinomio(int grado, double coef){
        this.grado=grado;
        coeficientes=new double[grado+1];
        coeficientes[grado]=coef;
    }

    public void setCoeficientes(double[] coeficientes){
        this.coeficientes=Arrays.copyOf(coeficientes,coeficientes.length);
    }

    public int getGrado() {
        return grado;
    }

    public double[] getCoeficientes() {
        return coeficientes;
    }

    public double coeficienteIndependiente() throws NoSuchElementException{
        if (grado <0)
            throw new NoSuchElementException("EL polinomio no tiene término independite");

        return coeficientes[0];
    }

    public double evaluarPolinomio(int x){
        double valor=coeficienteIndependiente();
        for(int i=1;i<=grado;i++){
            valor+=(coeficientes[i]*Math.pow(x,i));
        }
        return valor;
    }

    public boolean esPolCero(){
        for(double i: coeficientes)
            if(i!=0)
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
        String pol=""+ coeficienteIndependiente();
        for(int i=1;i<=grado;i++){
            pol+=" + " + (coeficientes[i]) +"(x^" + i+")";
        }
        return pol;
    }
}
