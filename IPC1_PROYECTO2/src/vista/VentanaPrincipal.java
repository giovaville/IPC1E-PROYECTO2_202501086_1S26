/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;


/**
 *
 * @author Gio
 */
public class VentanaPrincipal extends JFrame {

    private ControlPrincipal control;
    private JPanel panelActual;

    public VentanaPrincipal(ControlPrincipal control) {
        this.control = control;

        setTitle("GameZone Pro");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelActual = new JPanel();
        add(panelActual, BorderLayout.CENTER);
    }

    public void cambiarPanel(JPanel nuevoPanel) {
        if (panelActual != null) {
            remove(panelActual);
        }

        panelActual = nuevoPanel;
        add(panelActual, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public ControlPrincipal getControl() {
        return control;
    }
}
