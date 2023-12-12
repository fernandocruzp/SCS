/**
 * Clase de prueba para la clase Cifrador.
 */
package com.proyecto.test;

import com.proyecto.Cifrador;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;

/**
 * Clase que contiene pruebas unitarias para la clase Cifrador.
 */
public class TestCifrador {

    /**
     * Prueba la generación de clave y vector en el objeto Cifrador.
     */
    @Test
    public void testGenerarClave() {
        // Definir una clave de prueba
        byte[] clave = "claveSecreta".getBytes();

        // Crear instancia de Cifrador con la clave de prueba
        Cifrador cifrador = new Cifrador(clave);

        // Verificar que la clave y el vector no sean nulos
        assertNotNull(cifrador.llave);
        assertNotNull(cifrador.vector);
    }

    /**
     * Prueba el cifrado y descifrado de un archivo.
     */
    @Test
    public void testCifrarYDecifrar() {
        try {
            // Caso de prueba exitoso
            byte[] clave = "claveSecreta".getBytes();
            Cifrador cifrador = new Cifrador(clave);
            String resultadoCifrar = cifrador.cifrar("archivo.txt");
            String resultadoDecifrar = cifrador.decifrar("archivo.txt.aes");

            // Verificar que el resultado de descifrar sea igual al resultado de cifrar
            assertEquals(resultadoDecifrar, resultadoCifrar);

            // Caso de prueba con archivo inexistente
            resultadoDecifrar = cifrador.decifrar("archivo_inexistente.txt.aes");

            // Verificar que el resultado de descifrar sea nulo (archivo inexistente)
            assertNull(resultadoDecifrar);
        } catch (Exception e) {
            // Reportar error en caso de excepción
            fail("Decifrar: Error");
        }
    }

    /**
     * Prueba la lectura de un archivo y verifica que el contenido sea el esperado.
     */
    @Test
    public void testLeerArchivo() {
        // Definir una clave de prueba
        byte[] clave = "claveSecreta".getBytes();

        // Crear instancia de Cifrador con la clave de prueba
        Cifrador cifrador = new Cifrador(clave);

        // Definir un texto de prueba
        String textoOriginal = "Texto de prueba para leer archivo";

        try {
            // Definir el nombre del archivo de prueba
            String archivo = "archivo_lectura.txt";

            // Escribir el texto de prueba en el archivo
            cifrador.escribirArchivo(archivo, textoOriginal);

            // Leer el contenido del archivo
            String contenidoLeido = cifrador.leerArchivo(archivo);

            // Verificar que el contenido leído sea igual al texto original
            assertNotNull(contenidoLeido);
            assertEquals(textoOriginal, contenidoLeido);

            // Eliminar el archivo de prueba si existe
            Path path = Paths.get(archivo);
            if (Files.exists(path))
                Files.delete(path);

        } catch (IOException e) {
            // Reportar error en caso de excepción de E/S
            fail("Excepción de E/S: " + e.getMessage());
        }
    }

    /**
     * Prueba la escritura de un archivo y verifica que el contenido sea el esperado.
     */
    @Test
    public void testEscribirArchivo() {
        // Definir una clave de prueba
        byte[] clave = "claveSecreta".getBytes();

        // Crear instancia de Cifrador con la clave de prueba
        Cifrador cifrador = new Cifrador(clave);

        // Definir un texto de prueba
        String textoOriginal = "Texto de prueba para escribir archivo";

        try {
            // Definir el nombre del archivo de prueba
            String archivo = "archivo_escritura.txt";

            // Escribir el texto de prueba en el archivo
            cifrador.escribirArchivo(archivo, textoOriginal);

            // Verificar que el archivo existe
            Path path = Paths.get(archivo);
            assertTrue(Files.exists(path));

            // Leer el contenido del archivo
            String contenidoLeido = cifrador.leerArchivo(archivo);

            // Verificar que el contenido leído sea igual al texto original
            assertNotNull(contenidoLeido);
            assertEquals(textoOriginal, contenidoLeido);

            // Eliminar el archivo de prueba si existe
            if (Files.exists(path))
                Files.delete(path);

        } catch (IOException e) {
            // Reportar error en caso de excepción de E/S
            fail("Excepción de E/S: " + e.getMessage());
        }
    }
}
