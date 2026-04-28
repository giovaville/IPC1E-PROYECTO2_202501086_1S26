/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelActual = new JPanel();
        add(panelActual, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }
        });
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

    private void cerrarAplicacion() {
        if (control != null) {
            control.guardarDatos();
        }

        dispose();
        System.exit(0);
    }

    public ControlPrincipal getControl() {
        return control;
    }
}
