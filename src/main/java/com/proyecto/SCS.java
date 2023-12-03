package com.proyecto;

import java.io.Console;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
                    BigDecimal terminoIndependiente= convertirByte(llave);
                    GeneradorLLaves.guardarLlaves(minPuntos,totalEvaluaciones,terminoIndependiente,evaluacionNombre);
                    hash.reset();
                    hash.update(toBytes(terminoIndependiente));
                    byte[] encripta= hash.digest();
                    Cifrador cifrador = new Cifrador(encripta);
                    String texto =cifrador.cifrar(nombredocumento);
                    if(texto==null){
                        System.out.println("Hubo un error");
                        System.exit(1);
                    }
                    System.out.println("Se ha encriptado correctamente el archivo");
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
                try {
                    MessageDigest hash = MessageDigest.getInstance("SHA-256");
                    hash.update(toBytes(llave));
                    byte[] clave= hash.digest();
                    Cifrador desifrar = new Cifrador(clave);
                    String texto = desifrar.decifrar(archivoCifrado);
                    if(texto==null){
                        System.out.println("Hubo un error");
                        System.exit(1);
                    }
                    System.out.println("Se ha desencriptado correctamente el archivo");
                }catch (NoSuchAlgorithmException e){}

                break;

            default:
                System.out.println("Opción no válida. Debe ingresar 'c' o 'd'.");
                System.exit(1);
        }
    }

    private static byte[] toBytes(BigDecimal decimal) {
        return ByteBuffer.allocate(8).putDouble(decimal.doubleValue()).array();
    }
    private static BigDecimal convertirByte(byte[] arreglo){
        byte[] primeros = Arrays.copyOfRange(arreglo, 0, 4);
        BigInteger intermedio= new BigInteger(1,primeros);
        return new BigDecimal(intermedio,0);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}
