package reto9;

import java.io.IOException;
import java.util.InputMismatchException;
import static reto9.Utileria.DesviacionEstandar;
import static reto9.Utileria.MediaBTC;
import static reto9.Utileria.PrecioMasAlto;
import static reto9.Utileria.PrecioMasBajo;
import static reto9.Utileria.lectura;
import static reto9.Utileria.transcripcion;
import static reto9.Utileria.ingresoInt;

public class Reto9 {

    public static void main(String[] args) throws IOException {
        int opcion = 01;
        String rutaBTC = "BTC-USD.csv"; // la ruta es asi porque ira dentro de la carpeta del proyecto
        while (opcion != 0) {
            try {
                opcion = ingresoInt("\nIngrese la opcion del punto:" + "\n\n"
                 + "1) LECTURA Y CREACION DEL NUEVO ARCHIVO\n"
                 + "2) JUSTIFICACIÓN PUNTO 2\n"
                 + "3) RESPUESTA PUNTO 3\n"
                 + "4) PROMEDIO Y DESVIACION ESTANDAR DE LOS PRECIOS\n"
                 + "DEL BITCOIN DURANTE 2021\n"
                 + "5) PRECIO MAS ALTO Y BAJO DEL BITCOIN Y SU FECHA\n"
                 + "0) SALIR.\n");
                switch (opcion) {
                    case 1:
                        String rutaNueva = "NuevoAnalisis.txt"; //cambiar el nombre a gusto 
                        transcripcion(rutaBTC, rutaNueva, ","); //creamos la rutaBTC de donde se extraen datos, la nueva ruta donde se van los datos analizados, y un string que sirve de split
                        System.out.println("Archivo analizado con exito.");
                        break;
                    case 2:
                        lectura("Punto2.txt");
                        break;
                    case 3:
                        lectura("Punto3.txt");
                        break;
                    case 4:
                        System.out.println("la media del bitcoin en 2021 fue:  " + MediaBTC(rutaBTC));
                        System.out.println("la desviacion estandar del bitcoin en 2021 fue: " + DesviacionEstandar(rutaBTC));
                        break;
                    case 5:
                        PrecioMasAlto(rutaBTC);
                        PrecioMasBajo(rutaBTC);
                        break;
                    case 0: 
                        System.out.println("Hasta Luego.");
                        break;
                    default:
                        System.out.println("Ingrese una opción correcta por favor.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(" Ingrese solo alguno de los numeros enteros que aparecen en el menú.");
            }
        }

    }

}
