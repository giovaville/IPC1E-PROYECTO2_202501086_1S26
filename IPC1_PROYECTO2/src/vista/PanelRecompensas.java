/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import modelo.UsuarioSistema;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
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

    private JPanel panelRecompensas;

    private JButton btnRegresar;

    public PanelRecompensas(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("RECOMPENSAS Y PROGRESO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        lblNivel = new JLabel();
        lblXP = new JLabel();
        lblRango = new JLabel();

        panelRecompensas = new JPanel();
        panelRecompensas.setLayout(new GridLayout(0, 1, 10, 10));
        panelRecompensas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnRegresar = new JButton("Regresar al menú");

        JPanel panelSuperior = new JPanel(new GridLayout(3, 1));
        panelSuperior.add(lblNivel);
        panelSuperior.add(lblXP);
        panelSuperior.add(lblRango);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelSuperior, BorderLayout.CENTER);
        add(new JScrollPane(panelRecompensas), BorderLayout.EAST);
        add(btnRegresar, BorderLayout.SOUTH);

        actualizarDatos();
        mostrarRecompensas();

        btnRegresar.addActionListener(e -> regresarMenu());
    }

    private void actualizarDatos() {
        UsuarioSistema usuario = control.getUsuarioActual();

        lblNivel.setText("Nivel: " + usuario.getNivel());
        lblXP.setText("XP: " + usuario.getXp());
        lblRango.setText("Rango: " + usuario.getRango());
    }

    private void mostrarRecompensas() {
        panelRecompensas.removeAll();

        UsuarioSistema usuario = control.getUsuarioActual();
        int nivel = usuario.getNivel();

        for (int i = 1; i <= nivel; i++) {
            JLabel recompensa = new JLabel("Recompensa desbloqueada nivel " + i);
            recompensa.setBorder(BorderFactory.createEtchedBorder());
            panelRecompensas.add(recompensa);
        }

        panelRecompensas.revalidate();
        panelRecompensas.repaint();
    }

    private void regresarMenu() {
        Window ventana = SwingUtilities.getWindowAncestor(PanelRecompensas.this);

        if (ventana instanceof VentanaPrincipal) {
            VentanaPrincipal vp = (VentanaPrincipal) ventana;
            vp.cambiarPanel(new PanelMenu(control));
        }
    }
}