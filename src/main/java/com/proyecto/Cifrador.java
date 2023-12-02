package com.proyecto;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//Clase que se encarga de cifrar y decifrar
public class Cifrador { 
    SecretKeySpec Llave;
    //método para generar clave de encriptación
    public Cifrador(byte[] cadena){ 
        try {
            Llave= new SecretKeySpec(cadena,"AES");
        } catch (Exception e) {

        }
    }

    //Cifrar
    public String cifrar(String encriptar){
        try { 
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipher.ENCRYPT_MODE, Llave);
            byte[] cadena= encriptar.getBytes("UTF-8");
            byte[] encriptada = cipher.doFinal(cadena);
            String cadenaEncriptada = Base64.encode(encriptada);
            return cadenaEncriptada;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    //Decifrar
    public String decifrar(String decifrar){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipher.DECRYPT_MODE, Llave);
            byte[] cadena= Base64.decode(decifrar);
            byte[] desencriptada = cipher.doFinal(cadena);
            String cadenaDesencriptada = new String (desencriptada);
            return cadenaDesencriptada;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
//Verificar errores, si un archivo no esta 