/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import Estructuras.NodoMatriz;
import controlador.ControlPrincipal;
import modelo.Carta;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
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
    private JLabel lblDetalles;

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnLimpiarBusqueda;
    private JButton btnAgregarCarta;
    private JButton btnIntercambiar;
    private JButton btnRegresar;

    public PanelAlbum(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("ÁLBUM DE CARTAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        txtBuscar = new JTextField();
        btnBuscar = new JButton("Buscar");
        btnLimpiarBusqueda = new JButton("Limpiar búsqueda");

        JPanel panelBusqueda = new JPanel(new GridLayout(1, 4, 10, 10));
        panelBusqueda.add(new JLabel("Buscar por nombre:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnLimpiarBusqueda);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        panelSuperior.add(panelBusqueda, BorderLayout.CENTER);

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
        btnIntercambiar = new JButton("Intercambiar cartas");
        btnRegresar = new JButton("Regresar al menú");

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.add(btnAgregarCarta);
        panelBotones.add(btnIntercambiar);
        panelBotones.add(btnRegresar);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(lblDetalles, BorderLayout.CENTER);
        panelDerecho.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(panelMatriz), BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);

        cargarCartasInicialesSiHaceFalta();
        dibujarAlbum(null);

        btnAgregarCarta.addActionListener(e -> agregarCarta());
        btnIntercambiar.addActionListener(e -> intercambiarCartas());
        btnBuscar.addActionListener(e -> buscarCarta());
        btnLimpiarBusqueda.addActionListener(e -> {
            txtBuscar.setText("");
            dibujarAlbum(null);
        });
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

    private void dibujarAlbum(NodoMatriz nodoResaltado) {
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

                if (nodo != null && nodo == nodoResaltado) {
                    celda.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
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
                + "<b>Nombre:</b> " + carta.getNombre() + "<br>"
                + "<b>Tipo:</b> " + carta.getTipo() + "<br>"
                + "<b>Rareza:</b> " + carta.getRareza() + "<br>"
                + "<b>Ataque:</b> " + carta.getAtaque() + "<br>"
                + "<b>Defensa:</b> " + carta.getDefensa() + "<br>"
                + "<b>PS:</b> " + carta.getPs()
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
                dibujarAlbum(null);

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

    private void buscarCarta() {
        String nombre = txtBuscar.getText();

        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un nombre para buscar.");
            return;
        }

        NodoMatriz encontrado = control.getControlAlbum().buscarCartaEnAlbumPorNombre(nombre);

        if (encontrado != null) {
            dibujarAlbum(encontrado);
            mostrarDetallesCarta(encontrado.getDato());
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró la carta.");
        }
    }

    private void intercambiarCartas() {
        try {
            String f1 = JOptionPane.showInputDialog(this, "Fila de la primera carta:");
            String c1 = JOptionPane.showInputDialog(this, "Columna de la primera carta:");
            String f2 = JOptionPane.showInputDialog(this, "Fila de la segunda carta:");
            String c2 = JOptionPane.showInputDialog(this, "Columna de la segunda carta:");

            if (f1 == null || c1 == null || f2 == null || c2 == null) {
                return;
            }

            int fila1 = Integer.parseInt(f1.trim());
            int col1 = Integer.parseInt(c1.trim());
            int fila2 = Integer.parseInt(f2.trim());
            int col2 = Integer.parseInt(c2.trim());

            boolean intercambiado = control.getControlAlbum().intercambiarCartas(fila1, col1, fila2, col2);

            if (intercambiado) {
                dibujarAlbum(null);
                JOptionPane.showMessageDialog(this, "Cartas intercambiadas correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo intercambiar. Revisa las posiciones.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Las filas y columnas deben ser números.");
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