# Esquema de Secreto Compartido de Shamir
Programa de ciframiento que sigue el esquema secreto de Shamir. 

## Colaboradores
* Cruz Pineda Fernando
* Espinosa Roque Rebeca
* Flores Carrillo Itzel Paola
* Marquéz Corona Danna Lizette

## Instalación 
```
$ git clone [liga del repositorio]
$ cd ../SCS
```
Se asume que el usuario ya cuenta con el gestionador de proyectos *Maven* en su equipo.

## Uso 
Para correr el programa se debe posicionar en el directorio ```../SCS``` y utilizar los siguientes comandos:
```
$ mvn compile
$ mvn install
$ java -jar target/scs.jar <opcion> <argumentos>
```
Si se elige la opción de cifrar se usarán los siguientes argumentos:
```
$ java -jar target/scs.jar c <archivo para polinomios> <número total de evaluaciones> <número mínimo> <archivo a cifrar> 
```
Si se elige la opción de decifrar se usarán los siguientes argumentos:
```
$ java -jar target/scs.jar d <archivo de polinomios>  <archivo a decifrar> 
```

**Nota** El archivo ya cifrado con terminación *.aes* (en el caso de decifrar con terminación *.orig*) estará en la dirección del archivo original, mientras que el archivo de polinomios estará en la direccion ```../SCS```
