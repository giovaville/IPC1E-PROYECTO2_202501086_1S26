/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import modelo.Estudiante;

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
public class PanelEstudiante extends JPanel {
    private ControlPrincipal control;

    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblCarnet;
    private JLabel lblCorreo;
    private JLabel lblSeccion;
    private JLabel lblSemestre;
    private JLabel lblAnio;
    private JLabel lblDescripcion;
    private JButton btnRegresar;

    public PanelEstudiante(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("DATOS DEL ESTUDIANTE", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        lblNombre = new JLabel();
        lblCarnet = new JLabel();
        lblCorreo = new JLabel();
        lblSeccion = new JLabel();
        lblSemestre = new JLabel();
        lblAnio = new JLabel();
        lblDescripcion = new JLabel();

        btnRegresar = new JButton("Regresar al menú");

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(8, 1, 10, 10));

        panelDatos.add(lblNombre);
        panelDatos.add(lblCarnet);
        panelDatos.add(lblCorreo);
        panelDatos.add(lblSeccion);
        panelDatos.add(lblSemestre);
        panelDatos.add(lblAnio);
        panelDatos.add(lblDescripcion);
        panelDatos.add(btnRegresar);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelDatos, BorderLayout.CENTER);

        cargarDatosPruebaSiHaceFalta();
        mostrarDatos();

        btnRegresar.addActionListener(e -> regresarMenu());
    }

    private void cargarDatosPruebaSiHaceFalta() {
        if (!control.getControlEstudiante().hayDatosEstudiante()) {
            control.getControlEstudiante().cargarDatosEstudiante(
                    "Tu Nombre Completo",
                    "2025XXXX",
                    "tunombre@ingenieria.usac.edu.gt",
                    "A",
                    "Primer Semestre",
                    2026,
                    "GameZone Pro: plataforma de videojuegos con tienda, album, torneos y recompensas."
            );
        }
    }

    private void mostrarDatos() {
        Estudiante estudiante = control.getControlEstudiante().getEstudiante();

        lblNombre.setText("Nombre: " + estudiante.getNombreCompleto());
        lblCarnet.setText("Carné: " + estudiante.getCarnet());
        lblCorreo.setText("Correo: " + estudiante.getCorreo());
        lblSeccion.setText("Sección: " + estudiante.getSeccion());
        lblSemestre.setText("Semestre: " + estudiante.getSemestre());
        lblAnio.setText("Año: " + estudiante.getAnio());
        lblDescripcion.setText("Descripción: " + estudiante.getDescripcionProyecto());
    }

    private void regresarMenu() {
        Window ventana = SwingUtilities.getWindowAncestor(PanelEstudiante.this);

        if (ventana instanceof VentanaPrincipal) {
            VentanaPrincipal vp = (VentanaPrincipal) ventana;
            vp.cambiarPanel(new PanelMenu(control));
        }
    }
}