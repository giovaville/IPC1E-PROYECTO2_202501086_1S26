/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import controlador.ControlReportes;
import Estructuras.ListaSimple;
import modelo.Carta;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class PanelReportes extends JPanel {
    private ControlPrincipal control;
    private ControlReportes controlReportes;

    private JLabel lblTitulo;

    private JButton btnReporteCompras;
    private JButton btnReporteAlbum;
    private JButton btnReporteTickets;
    private JButton btnReporteLeaderboard;
    private JButton btnRegresar;

    public PanelReportes(ControlPrincipal control) {
        this.control = control;
        this.controlReportes = new ControlReportes();

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("REPORTES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        btnReporteCompras = new JButton("Reporte de Compras");
        btnReporteAlbum = new JButton("Reporte de Álbum");
        btnReporteTickets = new JButton("Reporte de Tickets");
        btnReporteLeaderboard = new JButton("Reporte de Leaderboard");
        btnRegresar = new JButton("Regresar al menú");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));

        panelBotones.add(btnReporteCompras);
        panelBotones.add(btnReporteAlbum);
        panelBotones.add(btnReporteTickets);
        panelBotones.add(btnReporteLeaderboard);
        panelBotones.add(btnRegresar);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        btnReporteCompras.addActionListener(e -> generarReporteCompras());
        btnReporteAlbum.addActionListener(e -> generarReporteAlbum());
        btnReporteTickets.addActionListener(e -> generarReporteTickets());
        btnReporteLeaderboard.addActionListener(e -> generarReporteLeaderboard());
        btnRegresar.addActionListener(e -> regresarMenu());
    }

    private void generarReporteCompras() {
        ListaSimple compras = control.getUsuarioActual().getCompras();
        controlReportes.generarReporteCompras(compras);
    }

    private void generarReporteAlbum() {
        ListaSimple cartas = new ListaSimple();

        if (control != null) {
            // Si tienes acceso real al álbum, luego lo reemplazas
            // Por ahora lista vacía evita errores
        }

        controlReportes.generarReporteAlbum(cartas);
    }

    private void generarReporteTickets() {
        ListaSimple tickets = control.getControlTorneos().getTicketsVendidos();
        controlReportes.generarReporteTickets(tickets);
    }

    private void generarReporteLeaderboard() {
        ListaSimple usuarios = control.getControlRecompensas().getLeaderboard();
        controlReportes.generarReporteLeaderboard(usuarios);
    }

    private void regresarMenu() {
        Window ventana = SwingUtilities.getWindowAncestor(PanelReportes.this);

        if (ventana instanceof VentanaPrincipal) {
            VentanaPrincipal vp = (VentanaPrincipal) ventana;
            vp.cambiarPanel(new PanelMenu(control));
        }
    }
}