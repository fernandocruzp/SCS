import org.junit.Test;
import static org.junit.Assert.*;

public class TestPolinomio{

    @Test
    public void testConstructor() {
        int grado = 2;
        int[] coeficientes = {1, 2, 3};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        assertEquals(grado, polinomio.getGrado());
        assertArrayEquals(coeficientes, polinomio.getCoeficientes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorError() {
        int grado = 2;
        int[] coeficientes = {1, 2};

        new Polinomio(grado, coeficientes);
    }

    @Test
    public void testCoeficienteIndependiente() {
        int grado = 2;
        int[] coeficientes = {1, 2, 3};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        assertEquals(1, polinomio.coeficienteIndependiente());
    }

    @Test(expected = NoSuchElementException.class)
    public void testCoeficienteIndependienteError() {
        int grado = -1;
        int[] coeficientes = {1};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        polinomio.coeficienteIndependiente();
    }

    @Test
    public void testEvaluarPolinomio() {
        int grado = 2;
        int[] coeficientes = {1, 2, 3};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        assertEquals(6, polinomio.evaluarPolinomio(1));
        assertEquals(12, polinomio.evaluarPolinomio(2));
    }

    @Test
    public void testEquals() {
        int grado = 2;
        int[] coeficientes1 = {1, 2, 3};
        int[] coeficientes2 = {1, 2, 3};

        Polinomio polinomio1 = new Polinomio(grado, coeficientes1);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        assertTrue(polinomio1.equals(polinomio2));
    }

    @Test
    public void testNotEquals() {
        int grado = 2;
        int[] coeficientes1 = {1, 2, 3};
        int[] coeficientes2 = {1, 2, 4};

        Polinomio polinomio1 = new Polinomio(grado, coeficientes1);
        Polinomio polinomio2 = new Polinomio(grado, coeficientes2);

        assertFalse(polinomio1.equals(polinomio2));
    }

    @Test
    public void testToString() {
        int grado = 2;
        int[] coeficientes = {1, 2, 3};

        Polinomio polinomio = new Polinomio(grado, coeficientes);

        assertEquals("1 + 2(x^1) + 3(x^2)", polinomio.toString());
    }
}

