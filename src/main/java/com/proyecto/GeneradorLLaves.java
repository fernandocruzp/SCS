package com.proyecto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class GeneradorLLaves {




    public static void guardarLlaves(int n, int t, BigDecimal llave, String nombreArchivo) throws IllegalArgumentException{
        if(t<n)
            throw new IllegalArgumentException("El número de puntos mínimos debe ser menor al número total de puntos");
        SecureRandom generador=new SecureRandom();
        BigDecimal[] coeficientes=new BigDecimal[n];
        coeficientes[0]=llave;
        for(int i=1;i<n;i++)
            coeficientes[i]=BigDecimal.valueOf(generador.nextInt(1000));

        try {
            Polinomio polinomio=new Polinomio(n-1,coeficientes);

            BigDecimal[][] puntos=new BigDecimal[t][2];
            for (int i=0;i<t;i++){
                int x= (generador.nextInt(6));
                puntos[i][0]=BigDecimal.valueOf(x);
                puntos[i][1]=polinomio.evaluarPolinomio(x);
            }
            StringBuilder contentenido = new StringBuilder();
            for(BigDecimal[] punto : puntos)
                contentenido.append(Arrays.toString(punto)).append("\n");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo+".frg"))) {
                writer.write(contentenido.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IllegalArgumentException ie){
            System.out.println("Hubo un error");
        }
    }

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
            return polinomio.coeficienteIndependiente();
        }catch (IOException ie){
            System.out.println("No se encontró el archivo de llaves");
            return null;
        }
    }

    private static BigDecimal[] procesarTexto(String arreglo){
        arreglo=arreglo.substring(1,arreglo.length()-1);
        String[] elementos=arreglo.split(",");
        BigDecimal[] arreglito=new BigDecimal[2];
        arreglito[0]=new BigDecimal(elementos[0].trim());
        arreglito[1]=new BigDecimal(elementos[1].trim());
        return arreglito;
    }

}
