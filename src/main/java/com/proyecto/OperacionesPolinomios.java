package com.proyecto;

public class OperacionesPolinomios {

    public Polinomio suma(Polinomio p1, Polinomio p2) throws NullPointerException{
        if(p1 ==null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinimios para sumar");
        int n= p1.getGrado();
        int m=p2.getGrado(),i=0;
        int grado = (n>=m) ? n : m;
        int[] coef= new int[grado+1];
        while(m>=0 || n >= 0){
            int p11, p22;
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
    public Polinomio resta(Polinomio p1, Polinomio p2){
        if(p1 ==null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinimios para restar");
        int n= p1.getGrado();
        int m=p2.getGrado(),i=0;
        int grado = (n>=m) ? n : m;
        int[] coef= new int[grado+1];
        while(m>=0 || n >= 0){
            int p11, p22;
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
    public Polinomio multiplicacion(Polinomio p1, Polinomio p2){
        if(p1 ==null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinimios para multoplicarr");
        int n= p1.getGrado();
        int m=p2.getGrado();
        int[] coef= new int[n+m+1];
        for(int i=0;i<n+1;i++){
            for (int j=0;j<m+1;j++){
                coef[i+j]=coef[i+j]+(p1.getCoeficientes()[i]*p2.getCoeficientes()[j]);
            }
        }
        return new Polinomio(n+m,coef);
    }

    public Polinomio interpolacionNewton(int[][] puntos){
        return null;
    }
}
