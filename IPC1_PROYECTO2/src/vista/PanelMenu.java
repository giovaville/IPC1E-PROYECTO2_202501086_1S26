/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Window;
import javax.swing.SwingUtilities;
/**
 *
 * @author Gio
 */
public class PanelMenu extends JPanel {
    private ControlPrincipal control;
    private JLabel lblTitulo;
    private JButton btnTienda;
    private JButton btnAlbum;
    private JButton btnTorneos;
    private JButton btnRecompensas;
    private JButton btnReportes;
    private JButton btnEstudiante;
    private JButton btnSalir;

    public PanelMenu(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("GAMEZONE PRO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(7, 1, 10, 10));

        btnTienda = new JButton("Tienda");
        btnAlbum = new JButton("Álbum");
        btnTorneos = new JButton("Torneos");
        btnRecompensas = new JButton("Recompensas");
        btnReportes = new JButton("Reportes");
        btnEstudiante = new JButton("Datos del Estudiante");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnTienda);
        panelBotones.add(btnAlbum);
        panelBotones.add(btnTorneos);
        panelBotones.add(btnRecompensas);
        panelBotones.add(btnReportes);
        panelBotones.add(btnEstudiante);
        panelBotones.add(btnSalir);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        btnTienda.addActionListener(e -> abrirPanel(new PanelTienda(control)));
        btnAlbum.addActionListener(e -> abrirPanel(new PanelAlbum(control)));
        btnTorneos.addActionListener(e -> abrirPanel(new PanelTorneos(control)));
        btnRecompensas.addActionListener(e -> abrirPanel(new PanelRecompensas(control)));
        btnReportes.addActionListener(e -> abrirPanel(new PanelReportes(control)));
        btnEstudiante.addActionListener(e -> abrirPanel(new PanelEstudiante(control)));
        btnSalir.addActionListener(e -> {
    Window ventana = SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof VentanaPrincipal) {
        ((VentanaPrincipal) ventana).dispatchEvent(
                new java.awt.event.WindowEvent(
                        (VentanaPrincipal) ventana,
                        java.awt.event.WindowEvent.WINDOW_CLOSING
                )
        );
    }
});
    }

    private void abrirPanel(JPanel panel) {
        Window ventana = SwingUtilities.getWindowAncestor(this);

        if (ventana instanceof VentanaPrincipal) {
            VentanaPrincipal vp = (VentanaPrincipal) ventana;
            vp.cambiarPanel(panel);
        }
    }

    public ControlPrincipal getControl() {
        return control;
    }

    public JButton getBtnTienda() {
        return btnTienda;
    }

    public JButton getBtnAlbum() {
        return btnAlbum;
    }

    public JButton getBtnTorneos() {
        return btnTorneos;
    }

    public JButton getBtnRecompensas() {
        return btnRecompensas;
    }

    public JButton getBtnReportes() {
        return btnReportes;
    }

    public JButton getBtnEstudiante() {
        return btnEstudiante;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}
