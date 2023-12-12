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
public class SCSTest {

    @Test
    public void testToBytes() {
        try {
            SCS scsInstance = new SCS();
            Class<?> scsClass = SCS.class;
            Method toBytesMethod = scsClass.getDeclaredMethod("toBytes", BigDecimal.class);
            toBytesMethod.setAccessible(true);
            BigDecimal decimalPrueba = new BigDecimal("12345.6789");
            byte[] resultado = (byte[]) toBytesMethod.invoke(scsInstance, decimalPrueba);
            ByteBuffer bufferEsperado = ByteBuffer.allocate(8);
            bufferEsperado.putDouble(decimalPrueba.doubleValue());
            byte[] esperado = bufferEsperado.array();
            Assert.assertArrayEquals(esperado, resultado);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            Assert.fail("Excepción inesperada: " + e.getMessage());
        }
    }
    @Test
    public void testConvertirByte() {
        try {
            SCS scsInstance = new SCS();
            Class<?> scsClass = SCS.class;
            Method convertirByteMethod = scsClass.getDeclaredMethod("convertirByte", byte[].class);
            convertirByteMethod.setAccessible(true);
            byte[] arregloBytes = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
            BigDecimal resultado = (BigDecimal) convertirByteMethod.invoke(scsInstance, (Object) arregloBytes);
            byte[] primerosBytes = Arrays.copyOfRange(arregloBytes, 0, 4);
            BigInteger esperadoIntermedio = new BigInteger(1, primerosBytes);
            BigDecimal esperado = new BigDecimal(esperadoIntermedio, 0);
            Assert.assertEquals(esperado, resultado);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            Assert.fail("Excepción inesperada: " + e.getMessage());
        }
    }
    @Test
    public void testBytesToHex() {
        byte[] arregloBytesOriginal = {0x01, 0x23, 0x45, 0x67};

        try {
            SCS scs = new SCS();
            java.lang.reflect.Method method = SCS.class.getDeclaredMethod("bytesToHex", byte[].class);
            method.setAccessible(true);
            String resultadoHex = (String) method.invoke(scs, (Object) arregloBytesOriginal);
            String expectedHex = "01234567";
            Assert.assertEquals("Conversión de bytes a hexadecimal incorrecta", expectedHex, resultadoHex);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            Assert.fail("Excepción durante la prueba: " + e.getMessage());
        }
    }

}