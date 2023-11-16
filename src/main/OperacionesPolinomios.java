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
        }
        return new Polinomio(grado,coef);
    }
    public Polinomio multiplicacion(Polinomio p1, Polinomio p2){
        if(p1 ==null || p2 == null)
            throw new NullPointerException("Debes introducir dos polinimios para multoplicarr");
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
            coef[i]=p11*p22;
        }
        return new Polinomio(grado,coef);
    }

    public Polinomio interpolacionNewton(int[][] puntos){
        return null;
    }
}
