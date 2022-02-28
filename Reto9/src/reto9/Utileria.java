package reto9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Utileria {

    public static void ingresoLineas(String ruta, ArrayList<String> text) { //metodo para ingresar lineas con el metodo NIO
        Path miRuta = Paths.get(ruta);
        try {
            Files.write(miRuta, text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    //metodo para ir linea a linea del texto a analizar, luego separa la i-esima linea en un arreglo y seleccionamos el valor de cierre, comparamos los valores y reescribimos al final un nuevo documento con los analisis terminados
    public static void transcripcion(String ruta1, String ruta2, String split) {
        Path miRuta = Paths.get(ruta1);
        String[] arreglo;
        String valor = " ";
        ArrayList<String> nuevoArray = new ArrayList();
        try {
            List<String> array = Files.readAllLines(miRuta);

            for (int i = 1; i < array.size(); i++) {
                arreglo = array.get(i).split(split);

                if (Double.parseDouble(arreglo[4]) < 30000) {
                    valor = "MUY BAJO";

                }
                if (Double.parseDouble(arreglo[4]) >= 30000 && Double.parseDouble(arreglo[4]) < 40000) {
                    valor = "BAJO";

                }
                if (Double.parseDouble(arreglo[4]) >= 40000 && Double.parseDouble(arreglo[4]) < 50000) {
                    valor = "MEDIO";

                }
                if (Double.parseDouble(arreglo[4]) >= 50000 && Double.parseDouble(arreglo[4]) < 60000) {
                    valor = "ALTO";

                }
                if (Double.parseDouble(arreglo[4]) >= 60000) {
                    valor = "MUY ALTO";

                }

                nuevoArray.add(i - 1, arreglo[0] + "\t" + valor);

            }
            ingresoLineas(ruta2, nuevoArray);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());

        }

    }

    public static double MediaBTC(String ruta1) throws IOException {
        Path ruta = Paths.get(ruta1);
        String[] linea;
        double media = 0;
        List<String> documento = Files.readAllLines(ruta);

        for (int i = 1; i < documento.size(); i++) {
            linea = documento.get(i).split(",");
            double valor = Double.parseDouble(linea[1]);
            media += valor;
        }
        return (media / documento.size());

    }

    public static double DesviacionEstandar(String Ruta) throws IOException {
        Path ruta = Paths.get(Ruta);
        String[] linea;
        double desviacion;
        double varianza = 0;
        List<String> documento = Files.readAllLines(ruta);
        for (int i = 1; i < documento.size(); i++) {
            linea = documento.get(i).split(",");
            double rango;
            rango = Math.pow(Double.parseDouble(linea[1]) - MediaBTC(Ruta), 2f);
            varianza = varianza + rango;
        }
        varianza = varianza / documento.size();
        desviacion = Math.sqrt(varianza);
        return desviacion;
    }

    public static void PrecioMasAlto(String ruta1) throws IOException {

        Path ruta = Paths.get(ruta1);
        String[] linea;

        ArrayList<Double> Mayores = new ArrayList();

        List<String> documento = Files.readAllLines(ruta);
        for (int i = 1; i < documento.size(); i++) {
            linea = documento.get(i).split(",");
            double high = Double.parseDouble(linea[2]);

            Mayores.add(high);
        }
        // aprovecahmos el collecions que nos saca el valor maximo de una lista
        System.out.println("el mayor valor del btc fue: " + Collections.max(Mayores));

    }

    public static void PrecioMasBajo(String ruta1) throws IOException {
        Path ruta = Paths.get(ruta1);
        String[] linea;
        ArrayList<Double> Minimos = new ArrayList();

        List<String> documento = Files.readAllLines(ruta);
        for (int i = 1; i < documento.size(); i++) {
            linea = documento.get(i).split(",");
            double minimoDeLaLinea = Double.parseDouble(linea[2]);

            Minimos.add(minimoDeLaLinea);
        }
        // aprovecahmos el collecions que nos saca el valor minimo de una lista
        System.out.println("el menor precio del BTC fue: " + Collections.min(Minimos));
    }
    
    public static void lectura(String ruta){  //lector de lineas.
        String linea;
        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
         public static int ingresoInt(String text) { //metodo para ingresar la opcion al menu
        System.out.println(text);
        return (new Scanner(System.in).nextInt());
    }

}
