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

    public ControlRecompensas() {
        this.leaderboard = new ListaSimple();
    }

    public ListaSimple getLeaderboard() {
        return leaderboard;
    }

    public void agregarUsuarioAlLeaderboard(UsuarioSistema usuario) {
        if (usuario != null) {
            leaderboard.agregarAlFinal(usuario);
        }
    }

    public void otorgarXpPorCompra(UsuarioSistema usuario, int cantidadJuegos) {
        if (usuario != null && cantidadJuegos > 0) {
            usuario.agregarXp(cantidadJuegos * 50);
        }
    }

    public void otorgarXpPorFilaCompleta(UsuarioSistema usuario) {
        if (usuario != null) {
            usuario.agregarXp(100);
        }
    }

    public void otorgarXpPorCartaLegendaria(UsuarioSistema usuario) {
        if (usuario != null) {
            usuario.agregarXp(200);
        }
    }

    public void otorgarXpPorInscripcionTorneo(UsuarioSistema usuario) {
        if (usuario != null) {
            usuario.agregarXp(150);
        }
    }

    public void otorgarXpPorInicioSesion(UsuarioSistema usuario) {
        if (usuario != null) {
            usuario.agregarXp(10);
        }
    }

    public void registrarLogro(UsuarioSistema usuario, Logro logro) {
        if (usuario != null && logro != null) {
            usuario.agregarLogro(logro);
        }
    }

    public void desbloquearLogro(Logro logro) {
        if (logro != null) {
            logro.setDesbloqueado(true);
        }
    }

    public Logro buscarLogroPorNombre(UsuarioSistema usuario, String nombre) {
        if (usuario == null || nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        ListaSimple logros = usuario.getLogros();

        for (int i = 0; i < logros.size(); i++) {
            Logro logro = (Logro) logros.obtener(i);

            if (logro != null && logro.getNombre().equalsIgnoreCase(nombre.trim())) {
                return logro;
            }
        }

        return null;
    }

    public void ordenarLeaderboardPorXp() {
        int n = leaderboard.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                UsuarioSistema actual = (UsuarioSistema) leaderboard.obtener(j);
                UsuarioSistema siguiente = (UsuarioSistema) leaderboard.obtener(j + 1);

                if (actual != null && siguiente != null && actual.getXp() < siguiente.getXp()) {
                    intercambiarUsuarios(j, j + 1);
                }
            }
        }
    }

    private void intercambiarUsuarios(int indice1, int indice2) {
        UsuarioSistema usuario1 = (UsuarioSistema) leaderboard.obtener(indice1);
        UsuarioSistema usuario2 = (UsuarioSistema) leaderboard.obtener(indice2);

        if (usuario1 == null || usuario2 == null) {
            return;
        }

        NodoTemporal nodo1 = obtenerNodoTemporal(indice1);
        NodoTemporal nodo2 = obtenerNodoTemporal(indice2);

        if (nodo1 != null && nodo2 != null) {
            nodo1.setDato(usuario2);
            nodo2.setDato(usuario1);
        }
    }

    private NodoTemporal obtenerNodoTemporal(int indice) {
        if (indice < 0 || indice >= leaderboard.size()) {
            return null;
        }

        return new NodoTemporal(leaderboard, indice);
    }

    private class NodoTemporal {

        private ListaSimple lista;
        private int indice;

        public NodoTemporal(ListaSimple lista, int indice) {
            this.lista = lista;
            this.indice = indice;
        }

        public void setDato(Object dato) {
            if (lista == null || indice < 0 || indice >= lista.size()) {
                return;
            }

            Estructuras.NodoSimple auxiliar = lista.getCabeza();
            int contador = 0;

            while (auxiliar != null) {
                if (contador == indice) {
                    auxiliar.setDato(dato);
                    return;
                }
                auxiliar = auxiliar.getSiguiente();
                contador++;
            }
        }
    }
}
