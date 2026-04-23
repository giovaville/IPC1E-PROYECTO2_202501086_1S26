/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import Estructuras.ListaSimple;
import modelo.Logro;
import modelo.UsuarioSistema;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;

/**
 *
 * @author Gio
 */
public class PanelRecompensas extends JPanel {
    private ControlPrincipal control;

    private JLabel lblTitulo;
    private JLabel lblNivel;
    private JLabel lblXP;
    private JLabel lblRango;

    private JLabel lblLeaderboard;
    private JLabel lblLogros;

    private javax.swing.JTextArea areaLeaderboard;
    private javax.swing.JTextArea areaLogros;

    private JButton btnRegresar;

    public PanelRecompensas(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("RECOMPENSAS Y LEADERBOARD", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        lblNivel = new JLabel();
        lblXP = new JLabel();
        lblRango = new JLabel();

        lblLeaderboard = new JLabel("Leaderboard", SwingConstants.CENTER);
        lblLogros = new JLabel("Logros", SwingConstants.CENTER);

        areaLeaderboard = new javax.swing.JTextArea();
        areaLeaderboard.setEditable(false);

        areaLogros = new javax.swing.JTextArea();
        areaLogros.setEditable(false);

        btnRegresar = new JButton("Regresar al menú");

        JPanel panelSuperior = new JPanel(new GridLayout(3, 1));
        panelSuperior.add(lblNivel);
        panelSuperior.add(lblXP);
        panelSuperior.add(lblRango);

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.add(lblLeaderboard, BorderLayout.NORTH);
        panelIzquierdo.add(new JScrollPane(areaLeaderboard), BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(lblLogros, BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(areaLogros), BorderLayout.CENTER);

        panelCentro.add(panelIzquierdo);
        panelCentro.add(panelDerecho);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelSuperior, BorderLayout.WEST);
        add(panelCentro, BorderLayout.CENTER);
        add(btnRegresar, BorderLayout.SOUTH);

        cargarDatos();

        btnRegresar.addActionListener(e -> regresarMenu());
    }

    private void cargarDatos() {
        UsuarioSistema usuario = control.getUsuarioActual();

        control.getControlRecompensas().agregarUsuarioAlLeaderboard(usuario);
        control.getControlRecompensas().ordenarLeaderboardPorXp();

        lblNivel.setText("Nivel: " + usuario.getNivel());
        lblXP.setText("XP: " + usuario.getXp());
        lblRango.setText("Rango: " + usuario.getRango());

        cargarLeaderboard();
        cargarLogros();
    }

    private void cargarLeaderboard() {
        areaLeaderboard.setText("");

        ListaSimple leaderboard = control.getControlRecompensas().getLeaderboard();

        for (int i = 0; i < leaderboard.size(); i++) {
            UsuarioSistema usuario = (UsuarioSistema) leaderboard.obtener(i);

            if (usuario != null) {
                areaLeaderboard.append((i + 1) + ". " +
                        usuario.getNombre() + " - Nivel " +
                        usuario.getNivel() + " - XP " +
                        usuario.getXp() + "\n");
            }
        }
    }

    private void cargarLogros() {
        areaLogros.setText("");

        ListaSimple logros = control.getUsuarioActual().getLogros();

        if (logros.estaVacia()) {
            areaLogros.setText("Sin logros desbloqueados todavía.");
            return;
        }

        for (int i = 0; i < logros.size(); i++) {
            Logro logro = (Logro) logros.obtener(i);

            if (logro != null) {
                areaLogros.append("- " + logro.getNombre() + ": " + logro.getDescripcion() + "\n");
            }
        }
    }

    private void regresarMenu() {
        Window ventana = SwingUtilities.getWindowAncestor(PanelRecompensas.this);

        if (ventana instanceof VentanaPrincipal) {
            VentanaPrincipal vp = (VentanaPrincipal) ventana;
            vp.cambiarPanel(new PanelMenu(control));
        }
    }
}