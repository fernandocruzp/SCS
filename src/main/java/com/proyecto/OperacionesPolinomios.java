package com.proyecto;

import java.util.Arrays;

public class OperacionesPolinomios {



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

    public Polinomio interpolacionLagrange(double[][] puntos) throws NullPointerException{
        if(puntos==null)
            throw new NullPointerException("No se recibieron puntos");
        Polinomio[] polinomios=Arrays.copyOf(generarPolinomios(puntos),puntos.length);
        Polinomio polinomioProd=prodPolinomio(polinomios);
        Polinomio res=productoEscalar(prodcutoLagrange(0,puntos,polinomioProd,polinomios),puntos[0][1]);
        for(int i=1;i<puntos.length;i++){
            res=suma(res,productoEscalar(prodcutoLagrange(i,puntos,polinomioProd,polinomios),puntos[i][1]));
        }
        return res;
    }

    private Polinomio prodPolinomio(Polinomio[] polinomios){
        Polinomio p= polinomios[0];
        for(int i=1;i<polinomios.length;i++){
            p=multiplicacion(p,polinomios[i]);
        }
        return p;
    }

    private Polinomio[] generarPolinomios(double[][] puntos){
        Polinomio[] polinomios=new Polinomio[puntos.length];
        int i=0;
        for (double[] punto : puntos){
            double[] n= {(-1*punto[0]),1};
            polinomios[i]=new Polinomio(1,n);
            i++;
        }
        return polinomios;
    }




    private Polinomio prodcutoLagrange(int i, double[][] x, Polinomio polinomio, Polinomio[] polinomios){
        Polinomio res=division(polinomios[i],polinomio);
        double prod=1;
        for(int j=0;j< x.length;j++){
            if(j==i)
                continue;
            prod*=(x[i][0]-x[j][0]);
        }
        res=productoEscalar(res,(1/prod));
        return res;
    }
}
