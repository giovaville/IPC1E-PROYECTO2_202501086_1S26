/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import Estructuras.Cola;
import Estructuras.ListaSimple;
import modelo.TicketVendido;
import modelo.Torneo;
import modelo.UsuarioSistema;
import util.FechaUtil;
/**
 *
 * @author Gio
 */
public class ControlTorneos {
    private ListaSimple listaTorneos;
    private ListaSimple ticketsVendidos;
    private Cola colaEspera;

    public ControlTorneos() {
        this.listaTorneos = new ListaSimple();
        this.ticketsVendidos = new ListaSimple();
        this.colaEspera = new Cola();
    }

    public ListaSimple getListaTorneos() {
        return listaTorneos;
    }

    public ListaSimple getTicketsVendidos() {
        return ticketsVendidos;
    }

    public Cola getColaEspera() {
        return colaEspera;
    }

    public void agregarTorneo(Torneo torneo) {
        if (torneo != null) {
            listaTorneos.agregarAlFinal(torneo);
        }
    }

    public Torneo buscarTorneoPorId(String idTorneo) {
        if (idTorneo == null || idTorneo.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < listaTorneos.size(); i++) {
            Torneo torneo = (Torneo) listaTorneos.obtener(i);

            if (torneo != null && torneo.getIdTorneo().equalsIgnoreCase(idTorneo.trim())) {
                return torneo;
            }
        }

        return null;
    }

    public boolean agregarUsuarioACola(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            return false;
        }

        colaEspera.encolar(nombreUsuario.trim());
        return true;
    }

    public boolean hayUsuariosEnCola() {
        return !colaEspera.estaVacia();
    }

    public String verSiguienteEnCola() {
        Object dato = colaEspera.peek();

        if (dato != null) {
            return dato.toString();
        }

        return null;
    }

    public TicketVendido venderTicket(Torneo torneo, UsuarioSistema usuario) {
        if (torneo == null || usuario == null) {
            return null;
        }

        if (!torneo.hayTickets()) {
            return null;
        }

        if (colaEspera.estaVacia()) {
            return null;
        }

        Object siguienteUsuario = colaEspera.desencolar();

        if (siguienteUsuario == null) {
            return null;
        }

        boolean vendido = torneo.venderTicket();

        if (!vendido) {
            return null;
        }

        TicketVendido ticket = new TicketVendido(
                generarIdTicket(),
                torneo,
                siguienteUsuario.toString(),
                FechaUtil.obtenerFechaHoraTexto(),
                torneo.getPrecioTicket()
        );

        ticketsVendidos.agregarAlFinal(ticket);
        usuario.agregarXp(150);

        return ticket;
    }

    public int contarTicketsVendidos() {
        return ticketsVendidos.size();
    }

    public ListaSimple obtenerTicketsPorTorneo(String idTorneo) {
        ListaSimple ticketsFiltrados = new ListaSimple();

        if (idTorneo == null || idTorneo.trim().isEmpty()) {
            return ticketsFiltrados;
        }

        for (int i = 0; i < ticketsVendidos.size(); i++) {
            TicketVendido ticket = (TicketVendido) ticketsVendidos.obtener(i);

            if (ticket != null && ticket.getTorneo() != null &&
                ticket.getTorneo().getIdTorneo().equalsIgnoreCase(idTorneo.trim())) {
                ticketsFiltrados.agregarAlFinal(ticket);
            }
        }

        return ticketsFiltrados;
    }

    public void limpiarCola() {
        while (!colaEspera.estaVacia()) {
            colaEspera.desencolar();
        }
    }

    private String generarIdTicket() {
        return "TICKET-" + System.currentTimeMillis();
    }
}