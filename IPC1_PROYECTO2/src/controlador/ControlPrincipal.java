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
    private UsuarioSistema usuarioActual;

    public ControlPrincipal() {
        this.controlTienda = new ControlTienda();
        this.controlTorneos = new ControlTorneos();
        this.controlRecompensas = new ControlRecompensas();
        this.controlEstudiante = new ControlEstudiante();
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

    public UsuarioSistema getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(UsuarioSistema usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void inicializarSistema() {
        // Aquí puedes cargar datos iniciales si quieres después
    }
}
