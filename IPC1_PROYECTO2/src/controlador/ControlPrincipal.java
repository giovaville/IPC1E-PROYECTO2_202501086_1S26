/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import Estructuras.ListaSimple;
import archivos.ArchivoAlbum;
import archivos.ArchivoEstudiante;
import archivos.ArchivoHistorial;
import archivos.ArchivoLeaderboard;
import archivos.ArchivoCatalogo;
import archivos.ArchivoTickets;
import archivos.ArchivoTorneos;
import modelo.Carta;
import modelo.Compra;
import modelo.Estudiante;
import modelo.TicketVendido;
import modelo.Torneo;
import modelo.UsuarioSistema;
/**
 *
 * @author Gio
 */
public class ControlPrincipal {
    private ControlTienda controlTienda;
    private ControlTorneos controlTorneos;
    private ControlRecompensas controlRecompensas;
    private ControlEstudiante controlEstudiante;
    private ControlAlbum controlAlbum;
    private ControlReportes controlReportes;
    private UsuarioSistema usuarioActual;

    private ArchivoHistorial archivoHistorial;
    private ArchivoAlbum archivoAlbum;
    private ArchivoTorneos archivoTorneos;
    private ArchivoTickets archivoTickets;
    private ArchivoLeaderboard archivoLeaderboard;
    private ArchivoEstudiante archivoEstudiante;
    private ArchivoCatalogo archivoCatalogo;

    public ControlPrincipal() {
        this.controlTienda = new ControlTienda();
        this.controlTorneos = new ControlTorneos();
        this.controlRecompensas = new ControlRecompensas();
        this.controlEstudiante = new ControlEstudiante();
        this.controlAlbum = new ControlAlbum();
        this.controlReportes = new ControlReportes();
        this.usuarioActual = new UsuarioSistema("Jugador");

        this.archivoHistorial = new ArchivoHistorial("historial.txt");
        this.archivoCatalogo = new ArchivoCatalogo("catalogo.txt");
        this.archivoAlbum = new ArchivoAlbum("album.txt");
        this.archivoTorneos = new ArchivoTorneos("torneos.txt");
        this.archivoTickets = new ArchivoTickets("tickets_vendidos.txt");
        this.archivoLeaderboard = new ArchivoLeaderboard("leaderboard.txt");
        this.archivoEstudiante = new ArchivoEstudiante("estudiante.txt");
    }

    public void inicializarSistema() {
        cargarDatos();
        controlRecompensas.agregarUsuarioAlLeaderboard(usuarioActual);
    }

    public void cargarDatos() {
        cargarCatalogo();
        cargarHistorial();
        cargarAlbum();
        cargarTorneos();
        cargarTickets();
        cargarLeaderboard();
        cargarEstudiante();
    }

    public void guardarDatos() {
        archivoHistorial.guardarHistorial(controlTienda.getHistorial());
        archivoAlbum.guardarCartas(controlAlbum.obtenerCartasDelAlbum());
        archivoTorneos.guardarTorneos(controlTorneos.getListaTorneos());
        archivoTickets.guardarTickets(controlTorneos.getTicketsVendidos());
        archivoLeaderboard.guardarLeaderboard(controlRecompensas.getLeaderboard());
        archivoEstudiante.guardarEstudiante(controlEstudiante.getEstudiante());
    }

    private void cargarHistorial() {
        ListaSimple historial = archivoHistorial.cargarHistorial();

        for (int i = 0; i < historial.size(); i++) {
            Compra compra = (Compra) historial.obtener(i);

            if (compra != null) {
                controlTienda.getHistorial().agregarAlFinal(compra);
                usuarioActual.agregarCompra(compra);
            }
        }
    }

    private void cargarAlbum() {
        ListaSimple cartas = archivoAlbum.cargarCartas();

        for (int i = 0; i < cartas.size(); i++) {
            Carta carta = (Carta) cartas.obtener(i);

            if (carta != null) {
                controlAlbum.agregarCartaAlAlbum(carta);
            }
        }
    }

    private void cargarTorneos() {
        ListaSimple torneos = archivoTorneos.cargarTorneos();

        for (int i = 0; i < torneos.size(); i++) {
            Torneo torneo = (Torneo) torneos.obtener(i);

            if (torneo != null) {
                controlTorneos.agregarTorneo(torneo);
            }
        }
    }

    private void cargarTickets() {
        ListaSimple tickets = archivoTickets.cargarTickets();

        for (int i = 0; i < tickets.size(); i++) {
            TicketVendido ticket = (TicketVendido) tickets.obtener(i);

            if (ticket != null) {
                controlTorneos.getTicketsVendidos().agregarAlFinal(ticket);
            }
        }
    }

    private void cargarLeaderboard() {
        ListaSimple usuarios = archivoLeaderboard.cargarLeaderboard();

        for (int i = 0; i < usuarios.size(); i++) {
            UsuarioSistema usuario = (UsuarioSistema) usuarios.obtener(i);

            if (usuario != null) {
                controlRecompensas.agregarUsuarioAlLeaderboard(usuario);
            }
        }

        controlRecompensas.agregarUsuarioAlLeaderboard(usuarioActual);
        controlRecompensas.ordenarLeaderboardPorXp();
    }

    private void cargarEstudiante() {
        Estudiante estudiante = archivoEstudiante.cargarEstudiante();

        if (estudiante != null && estudiante.getNombreCompleto() != null
                && !estudiante.getNombreCompleto().trim().isEmpty()) {
            controlEstudiante.setEstudiante(estudiante);
        }
    }

    public ControlTienda getControlTienda() {
        return controlTienda;
    }

    public ControlTorneos getControlTorneos() {
        return controlTorneos;
    }

    public ControlRecompensas getControlRecompensas() {
        return controlRecompensas;
    }

    public ControlEstudiante getControlEstudiante() {
        return controlEstudiante;
    }

    public ControlAlbum getControlAlbum() {
        return controlAlbum;
    }

    public ControlReportes getControlReportes() {
        return controlReportes;
    }

    public UsuarioSistema getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(UsuarioSistema usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    private void cargarCatalogo() {
    ListaSimple catalogo = archivoCatalogo.cargarCatalogo();

    for (int i = 0; i < catalogo.size(); i++) {
        modelo.juego juego = (modelo.juego) catalogo.obtener(i);

        if (juego != null) {
            controlTienda.agregarJuegoAlCatalogo(juego);
        }
    }
}
}
