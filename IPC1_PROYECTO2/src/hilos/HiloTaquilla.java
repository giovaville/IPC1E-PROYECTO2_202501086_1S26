/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilos;
import controlador.ControlTorneos;
import modelo.Torneo;
import modelo.UsuarioSistema;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
/**
 *
 * @author Gio
 */
public class HiloTaquilla extends Thread {

    private String nombreTaquilla;
    private ControlTorneos controlTorneos;
    private Torneo torneo;
    private UsuarioSistema usuarioActual;
    private JLabel lblEstado;
    private Runnable actualizadorGUI;
    private boolean activo;

    public HiloTaquilla(String nombreTaquilla, ControlTorneos controlTorneos,
                        Torneo torneo, UsuarioSistema usuarioActual,
                        JLabel lblEstado, Runnable actualizadorGUI) {
        this.nombreTaquilla = nombreTaquilla;
        this.controlTorneos = controlTorneos;
        this.torneo = torneo;
        this.usuarioActual = usuarioActual;
        this.lblEstado = lblEstado;
        this.actualizadorGUI = actualizadorGUI;
        this.activo = true;
    }

    @Override
    public void run() {
        while (activo && torneo != null && torneo.hayTickets() && controlTorneos.hayUsuariosEnCola()) {

            String siguienteUsuario = controlTorneos.verSiguienteEnCola();

            if (siguienteUsuario == null) {
                break;
            }

            actualizarEstado(nombreTaquilla + " procesando a: " + siguienteUsuario);

            try {
                Thread.sleep(generarTiempoEspera());
            } catch (InterruptedException e) {
                actualizarEstado(nombreTaquilla + " interrumpida");
                return;
            }

            if (!activo) {
                break;
            }

            controlTorneos.venderTicket(torneo, usuarioActual);

            if (actualizadorGUI != null) {
                SwingUtilities.invokeLater(actualizadorGUI);
            }
        }

        actualizarEstado(nombreTaquilla + " libre");
    }

    public void detenerTaquilla() {
        this.activo = false;
        interrupt();
    }

    private void actualizarEstado(String texto) {
        if (lblEstado != null) {
            SwingUtilities.invokeLater(() -> lblEstado.setText(texto));
        }
    }

    private int generarTiempoEspera() {
        return 800 + (int) (Math.random() * 1201);
    }
}
