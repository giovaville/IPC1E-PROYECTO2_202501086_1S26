/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import Estructuras.ListaSimple;
import enums.Genero;
import enums.Plataforma;
import modelo.Torneo;
import modelo.juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Gio
 */
public class ArchivoTorneos {
    private String rutaArchivo;

    public ArchivoTorneos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public ListaSimple cargarTorneos() {
        ListaSimple listaTorneos = new ListaSimple();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Torneo torneo = convertirLineaATorneo(linea);

                    if (torneo != null) {
                        listaTorneos.agregarAlFinal(torneo);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar torneos: " + e.getMessage());
        }

        return listaTorneos;
    }

    public void guardarTorneos(ListaSimple listaTorneos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            for (int i = 0; i < listaTorneos.size(); i++) {
                Torneo torneo = (Torneo) listaTorneos.obtener(i);

                if (torneo != null) {
                    String nombreJuego = "";
                    String generoJuego = "ACCION";
                    String plataformaJuego = "PC";

                    if (torneo.getJuego() != null) {
                        nombreJuego = torneo.getJuego().getNombre();
                        generoJuego = torneo.getJuego().getGenero().name();
                        plataformaJuego = torneo.getJuego().getPlataforma().name();
                    }

                    bw.write(
                            torneo.getIdTorneo() + "|" +
                            torneo.getNombre() + "|" +
                            nombreJuego + "|" +
                            generoJuego + "|" +
                            plataformaJuego + "|" +
                            torneo.getFecha() + "|" +
                            torneo.getHora() + "|" +
                            torneo.getPrecioTicket() + "|" +
                            torneo.getTicketsDisponibles()
                    );
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al guardar torneos: " + e.getMessage());
        }
    }

    private Torneo convertirLineaATorneo(String linea) {
        String[] partes = linea.split("\\|");

        if (partes.length != 9) {
            return null;
        }

        try {
            String idTorneo = partes[0].trim();
            String nombre = partes[1].trim();
            String nombreJuego = partes[2].trim();
            Genero genero = Genero.valueOf(partes[3].trim().toUpperCase());
            Plataforma plataforma = Plataforma.valueOf(partes[4].trim().toUpperCase().replace(" ", "_"));
            String fecha = partes[5].trim();
            String hora = partes[6].trim();
            double precioTicket = Double.parseDouble(partes[7].trim());
            int ticketsDisponibles = Integer.parseInt(partes[8].trim());

            juego juegoTorneo = new juego(
                    "JUEGO-" + idTorneo,
                    nombreJuego,
                    genero,
                    0.0,
                    plataforma,
                    0,
                    "Juego asociado al torneo"
            );

            return new Torneo(
                    idTorneo,
                    nombre,
                    juegoTorneo,
                    fecha,
                    hora,
                    precioTicket,
                    ticketsDisponibles
            );

        } catch (Exception e) {
            return null;
        }
    }
}