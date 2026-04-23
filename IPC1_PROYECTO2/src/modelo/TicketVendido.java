/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gio
 */
public class TicketVendido {
    private String idTicket;
    private Torneo torneo;
    private String nombreComprador;
    private String fechaCompra;
    private double precio;

    public TicketVendido() {
        this.idTicket = "";
        this.torneo = null;
        this.nombreComprador = "";
        this.fechaCompra = "";
        this.precio = 0.0;
    }

    public TicketVendido(String idTicket, Torneo torneo, String nombreComprador,
                         String fechaCompra, double precio) {
        this.idTicket = idTicket;
        this.torneo = torneo;
        this.nombreComprador = nombreComprador;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        String nombreTorneo = (torneo != null) ? torneo.getNombre() : "Sin torneo";

        return idTicket + " | " +
               nombreTorneo + " | " +
               nombreComprador + " | Q" +
               precio + " | " +
               fechaCompra;
    }
}