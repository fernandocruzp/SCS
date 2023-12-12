package com.proyecto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase que se encarga de cifrar o descifrar archivos.
 * Así como de su lectura y escritura.
 */
public class Cifrador {
    public SecretKeySpec llave;
    public byte[] vector;

    /**
     * Genera la clave que se usará para la encriptación de un archivo.
     *
     * @param cadena Contraseña proveida por el usuario ya procesada.
     */
    public Cifrador(byte[] cadena){ 
        try {
            llave= new SecretKeySpec(cadena,"AES");
            vector=new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(vector);
        } catch (Exception e) {
	    System.err.println("Error al inicializar el cifrador: " + e.getMessage());
        }
    }

    /**
     * Cifra el contenido del archivo indicado.
     *
     * @param encriptar Nombre del archivo que se desea encriptar.
     * @return Contenido del archivo ahora encriptado.
     */
    public String cifrar(String encriptar){
        try { 
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(cipher.ENCRYPT_MODE, llave,new IvParameterSpec(vector));
            byte[] encriptada= cipher.doFinal(leerArchivo(encriptar).getBytes());
            String textoEncriptado = Base64.getEncoder().encodeToString(encriptada);
            escribirArchivo(encriptar.substring(0,encriptar.length()-4)+".aes",textoEncriptado);
            return textoEncriptado;
        } catch (Exception e) {
            System.err.println("Error al cifrar: " + e.getMessage());
            return null;
        }
    }

    /**
     * Decifra el contenido del archivo indicado.
     *
     * @param decifrar Nombre del archivo que se desea decifrar.
     * @return Contenido decifrado del archivo.
     */
    public String decifrar(String decifrar){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(cipher.DECRYPT_MODE, llave,new IvParameterSpec(vector));
            byte[] encriptado = Base64.getDecoder().decode(leerArchivo(decifrar));
            byte [] desencriptado = cipher.doFinal(encriptado);
            String textoDesencriptado = new String(desencriptado);
            escribirArchivo(decifrar.substring(0,decifrar.length()-4)   +".orig",textoDesencriptado);
            return textoDesencriptado;
        } catch (Exception e) {
            System.err.println("Error al descifrar: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lee un archivo.
     *
     * @param archivo El nombre del archivo.
     * @return El contenido del archivo en un String.
     * @throws IOException Si existe un problema al leer el archivo.
     */ 
    public String leerArchivo(String archivo) throws IOException{
        Path direccion= Paths.get(archivo);
	if(!Files.exists(direccion)) {
	    System.out.println("El archivo no se encontró");
	    return null;
	}
	if(!Files.isReadable(direccion)) {
	     System.out.println("El archivo no se pudo leer");
	    return null;
	}
        byte[] contenido = Files.readAllBytes(direccion);
        return new String(contenido);
    }

     /**
     * Escribe un archivo.
     *
     * @param nombre  El nombre del archivo más terminación .aes .
     * @param contenido El contenido ya procesado que se desea escribir.
     * @throws IOException Si existe un problema al escribir el archivo.
     */ 
    public void escribirArchivo(String nombre, String contenido) throws IOException {
        Path direccion = Paths.get(nombre);
        Files.write(direccion,contenido.getBytes());
        try {
            Files.write(direccion, contenido.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

