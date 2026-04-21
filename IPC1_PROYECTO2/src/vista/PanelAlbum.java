/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import Estructuras.MatrizAlbum;
import Estructuras.NodoMatriz;
import enums.Rareza;
import enums.TipoCarta;
import modelo.Carta;

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
public class PanelAlbum extends JPanel {
    private ControlPrincipal control;
    private JLabel lblTitulo;
    private JPanel panelMatriz;
    private JButton btnRegresar;
    private MatrizAlbum album;

    public PanelAlbum(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("ÁLBUM DE CARTAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        album = new MatrizAlbum(4, 6);

        panelMatriz = new JPanel();
        panelMatriz.setLayout(new GridLayout(album.getFilas(), album.getColumnas(), 8, 8));
        panelMatriz.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnRegresar = new JButton("Regresar al menú");

        add(lblTitulo, BorderLayout.NORTH);
        add(new JScrollPane(panelMatriz), BorderLayout.CENTER);
        add(btnRegresar, BorderLayout.SOUTH);

        cargarCartasPrueba();
        dibujarAlbum();

        btnRegresar.addActionListener(e -> {
            Window ventana = SwingUtilities.getWindowAncestor(PanelAlbum.this);

            if (ventana instanceof VentanaPrincipal) {
                VentanaPrincipal vp = (VentanaPrincipal) ventana;
                vp.cambiarPanel(new PanelMenu(control));
            }
        });
    }

    private void cargarCartasPrueba() {
        if (album.contarCartas() == 0) {
            album.agregarCarta(new Carta(1, "Dragon Rojo", TipoCarta.FUEGO, Rareza.RARA, 90, 70, 100, "dragon.png"));
            album.agregarCarta(new Carta(2, "Tortuga Marina", TipoCarta.AGUA, Rareza.COMUN, 40, 60, 80, "tortuga.png"));
            album.agregarCarta(new Carta(3, "Guerrero Bosque", TipoCarta.PLANTA, Rareza.POCO_COMUN, 55, 50, 75, "bosque.png"));
            album.agregarCarta(new Carta(4, "Rayo Azul", TipoCarta.ELECTRICO, Rareza.ULTRA_RARA, 100, 65, 95, "rayo.png"));
            album.agregarCarta(new Carta(5, "Sombra Nocturna", TipoCarta.OSCURO, Rareza.LEGENDARIA, 120, 95, 110, "sombra.png"));
        }
    }

    private void dibujarAlbum() {
        panelMatriz.removeAll();

        for (int i = 0; i < album.getFilas(); i++) {
            for (int j = 0; j < album.getColumnas(); j++) {
                NodoMatriz nodo = album.obtenerNodo(i, j);

                CeldaCarta celda;

                if (nodo != null && nodo.getDato() != null) {
                    celda = new CeldaCarta(nodo.getDato());
                } else {
                    celda = new CeldaCarta();
                }

                panelMatriz.add(celda);
            }
        }

        panelMatriz.revalidate();
        panelMatriz.repaint();
    }

    public MatrizAlbum getAlbum() {
        return album;
    }
}