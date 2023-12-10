package com.proyecto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneradorLLaves {



    /**
     * Genera un conjunto de puntos aleatorios y guarda el polinomio correspondiente en un archivo.
     *
     * @param n              El grado del polinomio (número de coeficientes).
     * @param t              El número total de puntos a generar.
     * @param llave          El valor del coeficiente independiente del polinomio (clave secreta).
     * @param nombreArchivo  El nombre del archivo en el que se guardarán los puntos generados.
     * @throws IllegalArgumentException Si el número de puntos mínimos es mayor o igual al número total de puntos.
     */
    public static void guardarLlaves(int n, int t, BigDecimal llave, String nombreArchivo) throws IllegalArgumentException{
        if(t<n)
            throw new IllegalArgumentException("El número de puntos mínimos debe ser menor al número total de puntos");
        SecureRandom generador=new SecureRandom();
        BigDecimal[] coeficientes=new BigDecimal[n];
        coeficientes[0]=llave;
        for(int i=1;i<n;i++)
            coeficientes[i]=BigDecimal.valueOf(generador.nextInt(20));

        try {
            Polinomio polinomio=new Polinomio(n-1,coeficientes);

            BigDecimal[][] puntos=new BigDecimal[t][2];
            ArrayList<Integer> numeros=new ArrayList<>();
            int i=0;
            while(numeros.size()!=t){
                int x= (generador.nextInt(t+20));
                if(!numeros.contains(x)){
                    numeros.add(x);
                    puntos[i][0]=BigDecimal.valueOf(x);
                    puntos[i][1]=polinomio.evaluarPolinomio(x);
                    i++;
                }
            }
            StringBuilder contentenido = new StringBuilder();
            for(BigDecimal[] punto : puntos)
                contentenido.append(Arrays.toString(punto)).append("\n");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo+".frg"))) {
                writer.write(contentenido.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IllegalArgumentException e){
            System.err.println("Hubo un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Lee un archivo de puntos generados, realiza la interpolación de Lagrange y devuelve la clave original.
     *
     * @param nombreArchivo El nombre del archivo que contiene los puntos generados.
     * @return El valor del coeficiente independiente del polinomio interpolado (clave original).
     */
    public static BigDecimal generarLlave(String nombreArchivo){
        Path path = Path.of(nombreArchivo);
        try {
            List<String> lineas = Files.readAllLines(path);
            BigDecimal[][] puntos = new BigDecimal[lineas.size()][2];
            for (int i = 0; i < lineas.size(); i++){
                puntos[i] = procesarTexto(lineas.get(i));
            }
            OperacionesPolinomios lagrange= new OperacionesPolinomios();
            Polinomio polinomio = lagrange.interpolacionLagrange(puntos);
            return polinomio.coeficienteIndependiente().setScale(0, RoundingMode.HALF_UP);
        }catch (IOException e){
            System.err.println("Error al leer el archivo de llaves: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Procesa una línea de texto que contiene un par de valores separados por coma y devuelve un arreglo BigDecimal.
     *
     * @param arreglo La línea de texto que contiene los valores.
     * @return Un arreglo BigDecimal con los dos valores procesados.
     */
    private static BigDecimal[] procesarTexto(String arreglo){
        arreglo=arreglo.substring(1,arreglo.length()-1);
        String[] elementos=arreglo.split(",");
        BigDecimal[] arreglito=new BigDecimal[2];
        arreglito[0]=new BigDecimal(elementos[0].trim());
        arreglito[1]=new BigDecimal(elementos[1].trim());
        return arreglito;
    }

}
