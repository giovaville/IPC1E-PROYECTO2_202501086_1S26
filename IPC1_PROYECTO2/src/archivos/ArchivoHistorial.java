/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import Estructuras.ListaSimple;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import modelo.Compra;
import util.FechaUtil;
/**
 *
 * @author Gio
 */
public class ArchivoHistorial {

    private String rutaArchivo;

    public ArchivoHistorial(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarHistorial(ListaSimple historial) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            for (int i = 0; i < historial.size(); i++) {
                Compra compra = (Compra) historial.obtener(i);

                if (compra != null) {
                    bw.write(compra.getIdCompra() + "|" + compra.getFecha() + "|" + compra.getTotal());
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al guardar historial: " + e.getMessage());
        }
    }

    public ListaSimple cargarHistorial() {
        ListaSimple historial = new ListaSimple();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Compra compra = convertirLineaACompra(linea);

                    if (compra != null) {
                        historial.agregarAlFinal(compra);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar historial: " + e.getMessage());
        }

        return historial;
    }

    private Compra convertirLineaACompra(String linea) {
        String[] partes = linea.split("\\|");

        if (partes.length != 3) {
            return null;
        }

        try {
            String idCompra = partes[0].trim();
            String fecha = partes[1].trim();
            double total = Double.parseDouble(partes[2].trim());

            Compra compra = new Compra(idCompra, fecha);
            return compra;

        } catch (Exception e) {
            return null;
        }
    }
}
