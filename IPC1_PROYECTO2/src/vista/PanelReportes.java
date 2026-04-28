/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import Estructuras.ListaSimple;
import controlador.ControlPrincipal;
import controlador.ControlReportes;

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

    private JButton btnReporteInventario;
    private JButton btnReporteVentas;
    private JButton btnReporteAlbum;
    private JButton btnReporteTorneos;
    private JButton btnReporteLeaderboard;
    private JButton btnRegresar;

    public PanelReportes(ControlPrincipal control) {
        this.control = control;
        this.controlReportes = control.getControlReportes();

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("REPORTES HTML", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        btnReporteInventario = new JButton("Reporte de Inventario");
        btnReporteVentas = new JButton("Reporte de Ventas");
        btnReporteAlbum = new JButton("Reporte de Álbum");
        btnReporteTorneos = new JButton("Reporte de Torneos");
        btnReporteLeaderboard = new JButton("Reporte de Leaderboard");
        btnRegresar = new JButton("Regresar al menú");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 1, 10, 10));

        panelBotones.add(btnReporteInventario);
        panelBotones.add(btnReporteVentas);
        panelBotones.add(btnReporteAlbum);
        panelBotones.add(btnReporteTorneos);
        panelBotones.add(btnReporteLeaderboard);
        panelBotones.add(btnRegresar);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        btnReporteInventario.addActionListener(e -> generarReporteInventario());
        btnReporteVentas.addActionListener(e -> generarReporteVentas());
        btnReporteAlbum.addActionListener(e -> generarReporteAlbum());
        btnReporteTorneos.addActionListener(e -> generarReporteTorneos());
        btnReporteLeaderboard.addActionListener(e -> generarReporteLeaderboard());
        btnRegresar.addActionListener(e -> regresarMenu());
    }

    private void generarReporteInventario() {
        ListaSimple catalogo = control.getControlTienda().getCatalogo();
        controlReportes.generarReporteInventario(catalogo);
    }

    private void generarReporteVentas() {
        ListaSimple compras = control.getUsuarioActual().getCompras();
        controlReportes.generarReporteCompras(compras);
    }

    private void generarReporteAlbum() {
        ListaSimple cartas = control.getControlAlbum().obtenerCartasDelAlbum();
        controlReportes.generarReporteAlbum(cartas);
    }

    private void generarReporteTorneos() {
        ListaSimple tickets = control.getControlTorneos().getTicketsVendidos();
        controlReportes.generarReporteTickets(tickets);
    }

    private void generarReporteLeaderboard() {
        control.getControlRecompensas().agregarUsuarioAlLeaderboard(control.getUsuarioActual());
        control.getControlRecompensas().ordenarLeaderboardPorXp();

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