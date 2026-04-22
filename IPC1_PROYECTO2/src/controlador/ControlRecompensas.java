/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import Estructuras.ListaSimple;
import modelo.Logro;
import modelo.UsuarioSistema;
/**
 *
 * @author Gio
 */
public class ControlRecompensas {
    private ListaSimple leaderboard;
    private ListaSimple logrosDisponibles;

    public ControlRecompensas() {
        this.leaderboard = new ListaSimple();
        this.logrosDisponibles = new ListaSimple();
        inicializarLogros();
    }

    public ListaSimple getLeaderboard() {
        return leaderboard;
    }

    public ListaSimple getLogrosDisponibles() {
        return logrosDisponibles;
    }

    public void agregarUsuarioAlLeaderboard(UsuarioSistema usuario) {
        if (usuario != null && !existeEnLeaderboard(usuario.getNombre())) {
            leaderboard.agregarAlFinal(usuario);
        }
    }

    public void otorgarXp(UsuarioSistema usuario, int cantidad) {
        if (usuario != null && cantidad > 0) {
            usuario.agregarXp(cantidad);
            verificarLogros(usuario);
            agregarUsuarioAlLeaderboard(usuario);
            ordenarLeaderboardPorXp();
        }
    }

    private void inicializarLogros() {
        logrosDisponibles.agregarAlFinal(new Logro("Primer paso", "Alcanza 100 XP"));
        logrosDisponibles.agregarAlFinal(new Logro("Jugador activo", "Alcanza 500 XP"));
        logrosDisponibles.agregarAlFinal(new Logro("Veterano", "Alcanza 1500 XP"));
        logrosDisponibles.agregarAlFinal(new Logro("Maestro", "Alcanza 3500 XP"));
        logrosDisponibles.agregarAlFinal(new Logro("Leyenda", "Alcanza 7000 XP"));
        logrosDisponibles.agregarAlFinal(new Logro("Comprador", "Realiza tu primera compra"));
        logrosDisponibles.agregarAlFinal(new Logro("Competidor", "Participa en un torneo"));
        logrosDisponibles.agregarAlFinal(new Logro("Coleccionista", "Obtén 5 cartas"));
    }

    public void registrarLogroManual(UsuarioSistema usuario, String nombreLogro) {
        if (usuario == null || nombreLogro == null || nombreLogro.trim().isEmpty()) {
            return;
        }

        for (int i = 0; i < logrosDisponibles.size(); i++) {
            Logro logro = (Logro) logrosDisponibles.obtener(i);

            if (logro != null && logro.getNombre().equalsIgnoreCase(nombreLogro.trim())) {
                if (!usuarioYaTieneLogro(usuario, logro)) {
                    usuario.agregarLogro(new Logro(logro.getNombre(), logro.getDescripcion()));
                }
                return;
            }
        }
    }

    private void verificarLogros(UsuarioSistema usuario) {
        int xp = usuario.getXp();

        for (int i = 0; i < logrosDisponibles.size(); i++) {
            Logro logro = (Logro) logrosDisponibles.obtener(i);

            if (logro != null && !usuarioYaTieneLogro(usuario, logro)) {

                if (logro.getNombre().equals("Primer paso") && xp >= 100) {
                    usuario.agregarLogro(new Logro(logro.getNombre(), logro.getDescripcion()));
                } else if (logro.getNombre().equals("Jugador activo") && xp >= 500) {
                    usuario.agregarLogro(new Logro(logro.getNombre(), logro.getDescripcion()));
                } else if (logro.getNombre().equals("Veterano") && xp >= 1500) {
                    usuario.agregarLogro(new Logro(logro.getNombre(), logro.getDescripcion()));
                } else if (logro.getNombre().equals("Maestro") && xp >= 3500) {
                    usuario.agregarLogro(new Logro(logro.getNombre(), logro.getDescripcion()));
                } else if (logro.getNombre().equals("Leyenda") && xp >= 7000) {
                    usuario.agregarLogro(new Logro(logro.getNombre(), logro.getDescripcion()));
                }
            }
        }
    }

    private boolean usuarioYaTieneLogro(UsuarioSistema usuario, Logro logro) {
        ListaSimple logrosUsuario = usuario.getLogros();

        for (int i = 0; i < logrosUsuario.size(); i++) {
            Logro actual = (Logro) logrosUsuario.obtener(i);

            if (actual != null && actual.getNombre().equalsIgnoreCase(logro.getNombre())) {
                return true;
            }
        }

        return false;
    }

    private boolean existeEnLeaderboard(String nombreUsuario) {
        for (int i = 0; i < leaderboard.size(); i++) {
            UsuarioSistema usuario = (UsuarioSistema) leaderboard.obtener(i);

            if (usuario != null && usuario.getNombre().equalsIgnoreCase(nombreUsuario)) {
                return true;
            }
        }

        return false;
    }

    public void ordenarLeaderboardPorXp() {
        int n = leaderboard.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                UsuarioSistema actual = (UsuarioSistema) leaderboard.obtener(j);
                UsuarioSistema siguiente = (UsuarioSistema) leaderboard.obtener(j + 1);

                if (actual != null && siguiente != null && actual.getXp() < siguiente.getXp()) {
                    intercambiar(j, j + 1);
                }
            }
        }
    }

    private void intercambiar(int i, int j) {
        Object temp = leaderboard.obtener(i);
        leaderboard.set(i, leaderboard.obtener(j));
        leaderboard.set(j, temp);
    }
}