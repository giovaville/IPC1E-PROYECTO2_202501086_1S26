/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
/**
 *
 * @author Gio
 */
public class PanelReportes extends JPanel {

    private ControlPrincipal control;
    private JLabel lblTitulo;
    private JLabel lblMensaje;
    private JButton btnRegresar;

    public PanelReportes(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("REPORTES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        lblMensaje = new JLabel("Aquí irá el módulo de reportes.", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 16));

        btnRegresar = new JButton("Regresar al menú");

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(2, 1, 10, 10));
        panelCentro.add(lblMensaje);
        panelCentro.add(btnRegresar);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

        btnRegresar.addActionListener(e -> {
            java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(PanelReportes.this);

            if (ventana instanceof VentanaPrincipal) {
                VentanaPrincipal vp = (VentanaPrincipal) ventana;
                vp.cambiarPanel(new PanelMenu(control));
            }
        });
    }

    public ControlPrincipal getControl() {
        return control;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }
}