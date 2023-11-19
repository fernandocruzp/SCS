package com.proyecto;

import java.io.Console;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SCS{

    public static void main(String[] args){
        if (args.length < 2) {
            System.out.println("Uso: java Main <opcion> <otros_parametros>");
            System.exit(1);
        }

        String option = args[0];

        switch (option) {
            case "c":
                // Modo cifrar
                if (args.length < 5) {
                    System.out.println("Para cifrar, se necesitan 5 parámetros <opcion><nombre archivo ev> <n requeridas> <t para descifrar> <archivo a descifrar>.");
                    System.exit(1);
                }

                String evaluacionNombre = args[1];
                int totalEvaluaciones = Integer.parseInt(args[2]);
                int minPuntos = Integer.parseInt(args[3]);
                String nombredocumento = args[4];
                Console console = System.console();

                if (console == null) {
                    System.out.println("La consola no está disponible. Ejecuta este programa desde la línea de comandos.");
                    System.exit(1);
                }

                char[] arreglo = console.readPassword("Ingrese la contraseña: ");
                String contraseña = new String(arreglo);
                try {
                    MessageDigest hash = MessageDigest.getInstance("SHA-256");
                    hash.update(contraseña.getBytes(StandardCharsets.UTF_8));
                    byte[] llave= hash.digest();
                    java.util.Arrays.fill(arreglo, ' ');
                    System.out.println("Contraseña ingresada.");
                    System.out.println("Cifrando...");
                    System.out.println(convertirByte(llave));
                    GeneradorLLaves.guardarLlaves(minPuntos,totalEvaluaciones,convertirByte(llave),evaluacionNombre);
                }catch (NoSuchAlgorithmException ns){}
                catch (IllegalArgumentException ie){}
                break;

            case "d":
                // Modo descifrar
                if (args.length < 3) {
                    System.out.println("Para descifrar, se necesitan al menos 2 parámetros.");
                    System.exit(1);
                }

                String llaves = args[1];
                String archivoCifrado=args[2];
                System.out.println("Descifrando...");
                BigDecimal llave= GeneradorLLaves.generarLlave(llaves);
                byte[] llaveArreglo=convertirBigDecimal(llave);
                System.out.println(llave);
                break;

            default:
                System.out.println("Opción no válida. Debe ingresar 'c' o 'd'.");
                System.exit(1);
        }
    }
    private static BigDecimal convertirByte(byte[] arreglo){
        BigInteger intermedio= new BigInteger(1,arreglo);
        return new BigDecimal(intermedio,0);
    }

    private static byte[] convertirBigDecimal(BigDecimal p){
        return null;
    }

}
