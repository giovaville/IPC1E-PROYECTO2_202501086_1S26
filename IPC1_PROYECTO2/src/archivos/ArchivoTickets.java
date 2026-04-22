/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import Estructuras.ListaSimple;
import modelo.TicketVendido;
import modelo.Torneo;
import modelo.juego;
import enums.Genero;
import enums.Plataforma;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Gio
 */
public class ArchivoTickets {
    private String rutaArchivo;

    public ArchivoTickets(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarTickets(ListaSimple ticketsVendidos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            for (int i = 0; i < ticketsVendidos.size(); i++) {
                TicketVendido ticket = (TicketVendido) ticketsVendidos.obtener(i);

                if (ticket != null) {
                    String idTorneo = "";
                    String nombreTorneo = "";
                    String nombreJuego = "";
                    String fechaTorneo = "";
                    String horaTorneo = "";

                    if (ticket.getTorneo() != null) {
                        idTorneo = ticket.getTorneo().getIdTorneo();
                        nombreTorneo = ticket.getTorneo().getNombre();
                        fechaTorneo = ticket.getTorneo().getFecha();
                        horaTorneo = ticket.getTorneo().getHora();

                        if (ticket.getTorneo().getJuego() != null) {
                            nombreJuego = ticket.getTorneo().getJuego().getNombre();
                        }
                    }

                    bw.write(
                            ticket.getIdTicket() + "|" +
                            idTorneo + "|" +
                            nombreTorneo + "|" +
                            nombreJuego + "|" +
                            fechaTorneo + "|" +
                            horaTorneo + "|" +
                            ticket.getNombreComprador() + "|" +
                            ticket.getFechaCompra() + "|" +
                            ticket.getPrecio()
                    );
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al guardar tickets: " + e.getMessage());
        }
    }

    public ListaSimple cargarTickets() {
        ListaSimple tickets = new ListaSimple();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    TicketVendido ticket = convertirLineaATicket(linea);

                    if (ticket != null) {
                        tickets.agregarAlFinal(ticket);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar tickets: " + e.getMessage());
        }

        return tickets;
    }

    private TicketVendido convertirLineaATicket(String linea) {
        String[] partes = linea.split("\\|");

        if (partes.length != 9) {
            return null;
        }

        try {
            String idTicket = partes[0].trim();
            String idTorneo = partes[1].trim();
            String nombreTorneo = partes[2].trim();
            String nombreJuego = partes[3].trim();
            String fechaTorneo = partes[4].trim();
            String horaTorneo = partes[5].trim();
            String nombreComprador = partes[6].trim();
            String fechaCompra = partes[7].trim();
            double precio = Double.parseDouble(partes[8].trim());

            juego juegoTorneo = new juego(
                    "JUEGO-" + idTorneo,
                    nombreJuego,
                    Genero.ACCION,
                    0.0,
                    Plataforma.PC,
                    0,
                    "Juego asociado al torneo"
            );

            Torneo torneo = new Torneo(
                    idTorneo,
                    nombreTorneo,
                    juegoTorneo,
                    fechaTorneo,
                    horaTorneo,
                    precio,
                    0
            );

            return new TicketVendido(
                    idTicket,
                    torneo,
                    nombreComprador,
                    fechaCompra,
                    precio
            );

        } catch (Exception e) {
            return null;
        }
    }
}
