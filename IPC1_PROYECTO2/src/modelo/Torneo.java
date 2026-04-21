/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gio
 */
public class Torneo {
    private String idTorneo;
    private String nombre;
    private juego juego;
    private String fecha;
    private String hora;
    private double precioTicket;
    private int ticketsDisponibles;

    public Torneo() {
        this.idTorneo = "";
        this.nombre = "";
        this.juego = null;
        this.fecha = "";
        this.hora = "";
        this.precioTicket = 0.0;
        this.ticketsDisponibles = 0;
    }

    public Torneo(String idTorneo, String nombre, juego juego, String fecha,
                  String hora, double precioTicket, int ticketsDisponibles) {
        this.idTorneo = idTorneo;
        this.nombre = nombre;
        this.juego = juego;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTicket = precioTicket;
        this.ticketsDisponibles = ticketsDisponibles;
    }

    public String getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(String idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public juego getJuego() {
        return juego;
    }

    public void setJuego(juego juego) {
        this.juego = juego;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getPrecioTicket() {
        return precioTicket;
    }

    public void setPrecioTicket(double precioTicket) {
        this.precioTicket = precioTicket;
    }

    public int getTicketsDisponibles() {
        return ticketsDisponibles;
    }

    public void setTicketsDisponibles(int ticketsDisponibles) {
        this.ticketsDisponibles = ticketsDisponibles;
    }

    public boolean hayTickets() {
        return ticketsDisponibles > 0;
    }

    public boolean venderTicket() {
        if (ticketsDisponibles > 0) {
            ticketsDisponibles--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String nombreJuego = "Sin juego";

        if (juego != null) {
            nombreJuego = juego.getNombre();
        }

        return idTorneo + " - " + nombre + " - " + nombreJuego
                + " - " + fecha + " " + hora
                + " - Q" + precioTicket
                + " - Tickets: " + ticketsDisponibles;
    }
}