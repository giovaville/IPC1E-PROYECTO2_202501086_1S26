/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import Estructuras.ListaSimple;
import enums.Genero;
import enums.Plataforma;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import modelo.juego;
/**
 *
 * @author Gio
 */
public class ArchivoCatalogo {

    private String rutaArchivo;

    public ArchivoCatalogo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public ListaSimple cargarCatalogo() {
        ListaSimple catalogo = new ListaSimple();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    juego juegoLeido = convertirLineaAJuego(linea);

                    if (juegoLeido != null) {
                        catalogo.agregarAlFinal(juegoLeido);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar catálogo: " + e.getMessage());
        }

        return catalogo;
    }

    private juego convertirLineaAJuego(String linea) {
        String[] partes = linea.split("\\|");

        if (partes.length != 7) {
            return null;
        }

        try {
            String codigo = partes[0].trim();
            String nombre = partes[1].trim();
            Genero genero = Genero.valueOf(partes[2].trim().toUpperCase());
            double precio = Double.parseDouble(partes[3].trim());
            Plataforma plataforma = Plataforma.valueOf(partes[4].trim().toUpperCase().replace(" ", "_"));
            int stock = Integer.parseInt(partes[5].trim());
            String descripcion = partes[6].trim();

            return new juego(codigo, nombre, genero, precio, plataforma, stock, descripcion);

        } catch (Exception e) {
            return null;
        }
    }
}