/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import Estructuras.ListaSimple;
import enums.Rareza;
import enums.TipoCarta;
import modelo.Carta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Gio
 */
public class ArchivoAlbum {
    private String rutaArchivo;

    public ArchivoAlbum(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarCartas(ListaSimple cartas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            for (int i = 0; i < cartas.size(); i++) {
                Carta carta = (Carta) cartas.obtener(i);

                if (carta != null) {
                    bw.write(
                            carta.getCodigo() + "|" +
                            carta.getNombre() + "|" +
                            carta.getTipo().name() + "|" +
                            carta.getRareza().name() + "|" +
                            carta.getAtaque() + "|" +
                            carta.getDefensa() + "|" +
                            carta.getPs() + "|" +
                            carta.getImagen()
                    );
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al guardar álbum: " + e.getMessage());
        }
    }

    public ListaSimple cargarCartas() {
        ListaSimple cartas = new ListaSimple();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Carta carta = convertirLineaACarta(linea);

                    if (carta != null) {
                        cartas.agregarAlFinal(carta);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar álbum: " + e.getMessage());
        }

        return cartas;
    }

    private Carta convertirLineaACarta(String linea) {
        String[] partes = linea.split("\\|");

        if (partes.length != 8) {
            return null;
        }

        try {
            int codigo = Integer.parseInt(partes[0].trim());
            String nombre = partes[1].trim();
            TipoCarta tipo = TipoCarta.valueOf(partes[2].trim().toUpperCase());
            Rareza rareza = Rareza.valueOf(partes[3].trim().toUpperCase());
            int ataque = Integer.parseInt(partes[4].trim());
            int defensa = Integer.parseInt(partes[5].trim());
            int ps = Integer.parseInt(partes[6].trim());
            String imagen = partes[7].trim();

            return new Carta(codigo, nombre, tipo, rareza, ataque, defensa, ps, imagen);

        } catch (Exception e) {
            return null;
        }
    }
}
