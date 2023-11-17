package com.proyecto;

import java.util.Arrays;

public class OperacionesPolinomios {


    Polinomio[] polinomios;

    public Polinomio suma(Polinomio p1, Polinomio p2) throws NullPointerException{
        if(p1 ==null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinimios para sumar");
        int n= p1.getGrado();
        int m=p2.getGrado(),i=0;
        int grado = (n>=m) ? n : m;
        double [] coef= new double[grado+1];
        while(m>=0 || n >= 0){
            double p11, p22;
            if(n<0)
                p11=0;
            else
                p11=p1.getCoeficientes()[i];
            if(m<0)
                p22=0;
            else
                p22=p2.getCoeficientes()[i];
            coef[i]=p11+p22;
            m--;
            n--;
            i++;
        }
        return new Polinomio(grado,coef);
    }
    public Polinomio resta(Polinomio p1, Polinomio p2) throws NullPointerException{
        if(p1 ==null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinimios para restar");
        int n= p1.getGrado();
        int m=p2.getGrado(),i=0;
        int grado = (n>=m) ? n : m;
        double[] coef= new double[grado+1];
        while(m>=0 || n >= 0){
            double p11, p22;
            if(n<0)
                p11=0;
            else
                p11=p1.getCoeficientes()[i];
            if(m<0)
                p22=0;
            else
                p22=p2.getCoeficientes()[i];
            coef[i]=p11-p22;
            m--;
            n--;
            i++;
        }
        return new Polinomio(grado,coef);
    }
    public Polinomio multiplicacion(Polinomio p1, Polinomio p2) throws NullPointerException{
        if(p1 ==null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinimios para multoplicarr");
        int n= p1.getGrado();
        int m=p2.getGrado();
        double[] coef= new double[n+m+1];
        for(int i=0;i<n+1;i++){
            for (int j=0;j<m+1;j++){
                coef[i+j]=coef[i+j]+(p1.getCoeficientes()[i]*p2.getCoeficientes()[j]);
            }
        }
        return new Polinomio(n+m,coef);
    }

    public Polinomio productoEscalar(Polinomio p, double i){
        double[] m= new double[p.getGrado()+1];
        for (int j=0;j<p.getGrado()+1;j++)
            m[j]=(p.getCoeficientes()[j])*i;
        return new Polinomio(p.getGrado(),m);
    }

    public Polinomio division(Polinomio p1, Polinomio p2) throws IllegalArgumentException, NullPointerException{
        if(p1 ==null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinimios para multoplicarr");
        if(p1.getGrado()>p2.getGrado())
            throw new IllegalArgumentException("Primero ingresa el polinomio de grado menor");
        Polinomio p3 = new Polinomio(p2.getGrado()-p1.getGrado(),new double[p2.getGrado()-p1.getGrado()+1]);
        return divisionR(p1,p2,p3);
    }
    private Polinomio divisionR(Polinomio p1, Polinomio p2, Polinomio p3){
        if(p2.esPolCero())
            return p3;
        double[] coef= Arrays.copyOf(p3.getCoeficientes(),p3.getGrado()+1);
        int posicion=p2.getGrado();
        while(p2.getCoeficientes()[posicion]==0){
            posicion--;
        }
        if(posicion<p1.getGrado())
            return p3;
        coef[posicion-p1.getGrado()]=p2.getCoeficientes()[posicion]/p1.getCoeficientes()[p1.getGrado()];
        Polinomio multiplicador=new Polinomio(posicion-p1.getGrado(),coef[posicion-p1.getGrado()]);
        p3.setCoeficientes(coef);
        return divisionR(p1,resta(p2,multiplicacion(p1,multiplicador)),p3);
    }

    public Polinomio interpolacionLagrange(int[][] puntos) throws NullPointerException{
        if(puntos==null)
            throw new NullPointerException("No se recibieron puntos");
        Polinomio polinomio=null;
        for(int i=0;i<puntos.length;i++){

        }
        return null;
    }

    private void generarPolinomios(int[][] puntos){
        polinomios=new Polinomio[puntos.length];
        int i=0;
        for (int[] punto : puntos){
            polinomios[i]=new Polinomio(1,new double[]{(-1*punto[0]),1});
        }
    }




    private Polinomio prodcutoLagrange(int i, int[] x){
        return null;
    }
}
