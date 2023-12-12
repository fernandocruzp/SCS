/**
 * Clase de prueba para la clase SCS.
 */
package com.proyecto.test;

import com.proyecto.SCS;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Arrays;
import java.nio.ByteBuffer;

/**
 * Clase que contiene pruebas unitarias para la clase SCS.
 */
public class SCSTest {

    /**
     * Prueba la funcionalidad de conversión de BigDecimal a bytes.
     */
    @Test
    public void testToBytes() {
        try {
            // Crear instancia de SCS
            SCS scsInstance = new SCS();

            // Obtener la clase y el método privado "toBytes"
            Class<?> scsClass = SCS.class;
            Method toBytesMethod = scsClass.getDeclaredMethod("toBytes", BigDecimal.class);
            toBytesMethod.setAccessible(true);

            // Crear objeto BigDecimal de prueba
            BigDecimal decimalPrueba = new BigDecimal("12345.6789");

            // Invocar el método privado y obtener el resultado
            byte[] resultado = (byte[]) toBytesMethod.invoke(scsInstance, decimalPrueba);

            // Crear un ByteBuffer esperado y comparar con el resultado
            ByteBuffer bufferEsperado = ByteBuffer.allocate(8);
            bufferEsperado.putDouble(decimalPrueba.doubleValue());
            byte[] esperado = bufferEsperado.array();
            Assert.assertArrayEquals(esperado, resultado);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Capturar y reportar cualquier excepción inesperada
            Assert.fail("Excepción inesperada: " + e.getMessage());
        }
    }

    /**
     * Prueba la funcionalidad de conversión de bytes a BigDecimal.
     */
    @Test
    public void testConvertirByte() {
        try {
            // Crear instancia de SCS
            SCS scsInstance = new SCS();

            // Obtener la clase y el método privado "convertirByte"
            Class<?> scsClass = SCS.class;
            Method convertirByteMethod = scsClass.getDeclaredMethod("convertirByte", byte[].class);
            convertirByteMethod.setAccessible(true);

            // Crear un arreglo de bytes de prueba
            byte[] arregloBytes = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};

            // Invocar el método privado y obtener el resultado
            BigDecimal resultado = (BigDecimal) convertirByteMethod.invoke(scsInstance, (Object) arregloBytes);

            // Extraer los primeros 4 bytes del arreglo de bytes original
            byte[] primerosBytes = Arrays.copyOfRange(arregloBytes, 0, 4);

            // Crear un BigInteger esperado y comparar con el resultado
            BigInteger esperadoIntermedio = new BigInteger(1, primerosBytes);
            BigDecimal esperado = new BigDecimal(esperadoIntermedio, 0);
            Assert.assertEquals(esperado, resultado);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Capturar y reportar cualquier excepción inesperada
            Assert.fail("Excepción inesperada: " + e.getMessage());
        }
    }

    /**
     * Prueba la funcionalidad de conversión de bytes a representación hexadecimal.
     */
    @Test
    public void testBytesToHex() {
        // Definir un arreglo de bytes original para la prueba
        byte[] arregloBytesOriginal = {0x01, 0x23, 0x45, 0x67};

        try {
            // Crear instancia de SCS
            SCS scs = new SCS();

            // Obtener la clase y el método privado "bytesToHex"
            java.lang.reflect.Method method = SCS.class.getDeclaredMethod("bytesToHex", byte[].class);
            method.setAccessible(true);

            // Invocar el método privado y obtener el resultado
            String resultadoHex = (String) method.invoke(scs, (Object) arregloBytesOriginal);

            // Definir la representación hexadecimal esperada
            String expectedHex = "01234567";

            // Comparar la representación hexadecimal esperada con el resultado
            Assert.assertEquals("Conversión de bytes a hexadecimal incorrecta", expectedHex, resultadoHex);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Capturar y reportar cualquier excepción inesperada
            e.printStackTrace();
            Assert.fail("Excepción durante la prueba: " + e.getMessage());
        }
    }

}
