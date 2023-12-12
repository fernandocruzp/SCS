package com.proyecto.test;

import com.proyecto.Cifrador;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.io.*;
import java.nio.file.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;

public class TestCifrador {

    @Test
    public void testGenerarClave() {
        byte[] clave = "claveSecreta".getBytes();
        Cifrador cifrador = new Cifrador(clave);
        assertNotNull(cifrador.llave);
        assertNotNull(cifrador.vector);
    }


    @Test
    public void testCifrarYDecifrar() {
        try {
            // Caso de prueba exitoso
            byte[] clave = "claveSecreta".getBytes();
            Cifrador cifrador = new Cifrador(clave);
            String resultadoCifrar = cifrador.cifrar("archivo.txt");
            String resultadoDecifrar = cifrador.decifrar("archivo.txt.aes");
	    //assertNotNull(resultadoCifrar);
	    assertEquals(resultadoDecifrar, resultadoCifrar);

            // Caso de prueba con archivo inexistente
            resultadoDecifrar = cifrador.decifrar("archivo_inexistente.txt.aes");
            if (resultadoDecifrar == null) {
                System.out.println("Decifrar (archivo inexistente): Éxito");
            } else {
                System.out.println("Decifrar (archivo inexistente): Error");
            }
        } catch (Exception e) {
            System.out.println("Decifrar: Error");
        }
    }



    

    @Test
    public void testLeerArchivo() {
        byte[] clave = "claveSecreta".getBytes();
        Cifrador cifrador = new Cifrador(clave);
        String textoOriginal = "Texto de prueba para leer archivo";

        try {
            String archivo = "archivo_lectura.txt";
            cifrador.escribirArchivo(archivo, textoOriginal);
            String contenidoLeido = cifrador.leerArchivo(archivo);
            assertNotNull(contenidoLeido);
            assertEquals(textoOriginal, contenidoLeido);

	    Path path= Paths.get(archivo);
	    if(Files.exists(path))
		Files.delete(path);
	    
        } catch (IOException e) {
            fail("Excepción de E/S: " + e.getMessage());
        }
	
    }

    @Test
    public void testEscribirArchivo() {
        byte[] clave = "claveSecreta".getBytes();
        Cifrador cifrador = new Cifrador(clave);
        String textoOriginal = "Texto de prueba para escribir archivo";

        try {
            String archivo = "archivo_escritura.txt";
            cifrador.escribirArchivo(archivo, textoOriginal);

            Path path = Paths.get(archivo);
            assertTrue(Files.exists(path));

            String contenidoLeido = cifrador.leerArchivo(archivo);
            assertNotNull(contenidoLeido);
            assertEquals(textoOriginal, contenidoLeido);

	    if(Files.exists(path))
		Files.delete(path);
	    
        } catch (IOException e) {
            fail("Excepción de E/S: " + e.getMessage());
        }
    }
}
