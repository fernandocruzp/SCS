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

//Clase que se encarga de cifrar y decifrar
public class Cifrador { 
    SecretKeySpec Llave;
    byte[] vector;
    //método para generar clave de encriptación
    public Cifrador(byte[] cadena){ 
        try {
            Llave= new SecretKeySpec(cadena,"AES");
            vector=new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(vector);
        } catch (Exception e) {

        }
    }

    //Cifrar
    public String cifrar(String encriptar){
        try { 
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(cipher.ENCRYPT_MODE, Llave,new IvParameterSpec(vector));
            byte[] encriptada= cipher.doFinal(leerArchivo(encriptar).getBytes());
            String textoEncriptado = Base64.getEncoder().encodeToString(encriptada);
            escribirArchivo(encriptar+".aes",textoEncriptado);
            return textoEncriptado;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //Decifrar
    public String decifrar(String decifrar){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(cipher.DECRYPT_MODE, Llave,new IvParameterSpec(vector));
            byte[] encriptado = Base64.getDecoder().decode(leerArchivo(decifrar));
            byte [] desencriptado = cipher.doFinal(encriptado);
            String textoDesencriptado = new String(desencriptado);
            escribirArchivo(decifrar+".orig",textoDesencriptado);
            return textoDesencriptado;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private String leerArchivo(String archivo) throws IOException{
        Path direccion= Paths.get(archivo);
        byte[] contenido = Files.readAllBytes(direccion);
        return new String(contenido);
    }

    private void escribirArchivo(String nombre, String contenido) throws IOException {
        Path direccion = Paths.get(nombre);
        Files.write(direccion,contenido.getBytes());
    }

}
//Verificar errores, si un archivo no esta 