package com.proyecto;

import java.io.Console;

public class SCS{

    public static void main(String[] args) {
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

                char[] passwordArray = console.readPassword("Ingrese la contraseña: ");
                String password = new String(passwordArray);

                // Puedes usar la contraseña aquí

                // Limpiar el array de caracteres después de su uso
                java.util.Arrays.fill(passwordArray, ' ');
                System.out.println("Contraseña ingresada.");
                System.out.println("Cifrando...");

                break;

            case "d":
                // Modo descifrar
                if (args.length < 3) {
                    System.out.println("Para descifrar, se necesitan al menos 3 parámetros.");
                    System.exit(1);
                }

                String decryptionFileName = args[1];
                String passwordForDecryption = args[2];

                // Realizar lógica de descifrado con los parámetros proporcionados
                System.out.println("Descifrando...");

                break;

            default:
                System.out.println("Opción no válida. Debe ingresar 'c' o 'd'.");
                System.exit(1);
        }
    }

}
