import java.util.Arrays;
import java.util.NoSuchElementException;

public class Polinomio {
    private int grado;
    private int[] coeficientes;


    public Polinomio(int grado, int[] coeficientes) throws IllegalArgumentException{
        if(grado!= coeficientes.length-1)
            throw new IllegalArgumentException("La cantidad de coeficientes de un polinimio es siempre igual a grado +1");
        this.grado = grado;
        this.coeficientes=Arrays.copyOf(coeficientes,grado+1);

    }

    public int getGrado() {
        return grado;
    }

    public int[] getCoeficientes() {
        return coeficientes;
    }

    public int coeficienteIndependiente() throws NoSuchElementException{
        if (grado <0)
            return coeficientes[0];
        throw new NoSuchElementException("EL polinomio no tiene término independite");
    }

    public int evaluarPolinomio(int x){
        int valor=coeficienteIndependiente();
        for(int i=1;i<=grado;i++){
            valor+=(coeficientes[i]*Math.pow(x,i));
        }
        return valor;
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
