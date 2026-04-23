/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
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

    public ControlPrincipal() {
        this.controlTienda = new ControlTienda();
        this.controlTorneos = new ControlTorneos();
        this.controlRecompensas = new ControlRecompensas();
        this.controlEstudiante = new ControlEstudiante();
        this.controlAlbum = new ControlAlbum();
        this.controlReportes = new ControlReportes();
        this.usuarioActual = new UsuarioSistema("Jugador");
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

    public void inicializarSistema() {
        controlRecompensas.agregarUsuarioAlLeaderboard(usuarioActual);
    }
}
