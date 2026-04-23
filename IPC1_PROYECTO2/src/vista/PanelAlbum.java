/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import Estructuras.NodoMatriz;
import enums.Rareza;
import modelo.Carta;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class PanelAlbum extends JPanel {
    private ControlPrincipal control;
    private JLabel lblTitulo;
    private JPanel panelMatriz;
    private JPanel panelDerecho;
    private JLabel lblDetalles;
    private JButton btnAgregarCarta;
    private JButton btnRegresar;

    public PanelAlbum(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("ÁLBUM DE CARTAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        panelMatriz = new JPanel();
        panelMatriz.setLayout(new GridLayout(
                control.getControlAlbum().getAlbum().getFilas(),
                control.getControlAlbum().getAlbum().getColumnas(),
                8,
                8
        ));
        panelMatriz.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblDetalles = new JLabel("Selecciona una carta", SwingConstants.CENTER);

        btnAgregarCarta = new JButton("Agregar carta");
        btnRegresar = new JButton("Regresar al menú");

        panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(lblDetalles, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBotones.add(btnAgregarCarta);
        panelBotones.add(btnRegresar);

        panelDerecho.add(panelBotones, BorderLayout.SOUTH);

        add(lblTitulo, BorderLayout.NORTH);
        add(new JScrollPane(panelMatriz), BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);

        cargarCartasInicialesSiHaceFalta();
        dibujarAlbum();

        btnAgregarCarta.addActionListener(e -> agregarCarta());
        btnRegresar.addActionListener(e -> regresarMenu());
    }

    private void cargarCartasInicialesSiHaceFalta() {
        if (control.getControlAlbum().contarCartasEnAlbum() == 0) {
            control.getControlAlbum().agregarCartaPorCodigo(1);
            control.getControlAlbum().agregarCartaPorCodigo(2);
            control.getControlAlbum().agregarCartaPorCodigo(3);
            control.getControlAlbum().agregarCartaPorCodigo(4);
            control.getControlAlbum().agregarCartaPorCodigo(5);
        }
    }

    private void dibujarAlbum() {
        panelMatriz.removeAll();

        for (int i = 0; i < control.getControlAlbum().getAlbum().getFilas(); i++) {
            for (int j = 0; j < control.getControlAlbum().getAlbum().getColumnas(); j++) {
                NodoMatriz nodo = control.getControlAlbum().getAlbum().obtenerNodo(i, j);

                CeldaCarta celda;

                if (nodo != null && nodo.getDato() != null) {
                    Carta carta = nodo.getDato();
                    celda = new CeldaCarta(carta);

                    celda.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            mostrarDetallesCarta(carta);
                        }
                    });
                } else {
                    celda = new CeldaCarta();
                }

                panelMatriz.add(celda);
            }
        }

        panelMatriz.revalidate();
        panelMatriz.repaint();
    }

    private void mostrarDetallesCarta(Carta carta) {
        if (carta == null) {
            lblDetalles.setText("Carta vacía");
            return;
        }

        lblDetalles.setText("<html>"
                + "Nombre: " + carta.getNombre() + "<br>"
                + "Tipo: " + carta.getTipo() + "<br>"
                + "Rareza: " + carta.getRareza() + "<br>"
                + "Ataque: " + carta.getAtaque() + "<br>"
                + "Defensa: " + carta.getDefensa() + "<br>"
                + "PS: " + carta.getPs()
                + "</html>");
    }

    private void agregarCarta() {
        String codigoTexto = JOptionPane.showInputDialog(this, "Ingresa el código de la carta:");

        if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoTexto.trim());
            boolean agregada = control.getControlAlbum().agregarCartaPorCodigo(codigo);

            if (agregada) {
                dibujarAlbum();

                if (control.getControlAlbum().contarCartasLegendarias() > 0) {
                    control.getControlRecompensas().registrarLogroManual(
                            control.getUsuarioActual(),
                            "Coleccionista"
                    );
                }

                for (int i = 0; i < control.getControlAlbum().getAlbum().getFilas(); i++) {
                    if (control.getControlAlbum().filaCompleta(i)) {
                        control.getControlRecompensas().otorgarXp(control.getUsuarioActual(), 100);
                        break;
                    }
                }

                JOptionPane.showMessageDialog(this, "Carta agregada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar la carta.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código debe ser numérico.");
        }
    }

    private void regresarMenu() {
        Window ventana = SwingUtilities.getWindowAncestor(PanelAlbum.this);

        if (ventana instanceof VentanaPrincipal) {
            VentanaPrincipal vp = (VentanaPrincipal) ventana;
            vp.cambiarPanel(new PanelMenu(control));
        }
    }
}