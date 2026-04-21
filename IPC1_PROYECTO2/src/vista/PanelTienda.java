/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import modelo.Compra;
import modelo.ItemCarrito;
import modelo.UsuarioSistema;
import modelo.juego;
import enums.Genero;
import enums.Plataforma;
import Estructuras.ListaSimple;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Gio
 */
public class PanelTienda extends JPanel {
    private ControlPrincipal control;

    private JLabel lblTitulo;
    private JPanel panelCatalogo;
    private JPanel panelDerecho;
    private DefaultListModel<String> modeloCarrito;
    private JList<String> listaCarrito;
    private JLabel lblTotal;
    private JButton btnComprar;
    private JButton btnRegresar;

    public PanelTienda(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("TIENDA DE VIDEOJUEGOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 2, 10, 10));

        JScrollPane scrollCatalogo = new JScrollPane(panelCatalogo);

        modeloCarrito = new DefaultListModel<>();
        listaCarrito = new JList<>(modeloCarrito);

        lblTotal = new JLabel("Total: Q0.0", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));

        btnComprar = new JButton("Confirmar compra");
        btnRegresar = new JButton("Regresar al menú");

        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));
        panelBotones.add(lblTotal);
        panelBotones.add(btnComprar);
        panelBotones.add(btnRegresar);

        panelDerecho.add(new JLabel("CARRITO", SwingConstants.CENTER), BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(listaCarrito), BorderLayout.CENTER);
        panelDerecho.add(panelBotones, BorderLayout.SOUTH);

        add(lblTitulo, BorderLayout.NORTH);
        add(scrollCatalogo, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);

        cargarDatosPruebaSiHaceFalta();
        cargarCatalogoVisual();
        actualizarCarritoVisual();

        btnComprar.addActionListener(e -> confirmarCompra());

        btnRegresar.addActionListener(e -> {
            Window ventana = SwingUtilities.getWindowAncestor(PanelTienda.this);

            if (ventana instanceof VentanaPrincipal) {
                VentanaPrincipal vp = (VentanaPrincipal) ventana;
                vp.cambiarPanel(new PanelMenu(control));
            }
        });
    }

    private void cargarDatosPruebaSiHaceFalta() {
        if (control.getControlTienda().getCatalogo().estaVacia()) {
            control.getControlTienda().agregarJuegoAlCatalogo(
                    new juego("J001", "FIFA 23", Genero.DEPORTES, 300.0, Plataforma.PC, 5, "Juego de fútbol")
            );
            control.getControlTienda().agregarJuegoAlCatalogo(
                    new juego("J002", "Minecraft", Genero.AVENTURA, 200.0, Plataforma.XBOX, 8, "Juego de construcción")
            );
            control.getControlTienda().agregarJuegoAlCatalogo(
                    new juego("J003", "Resident Evil", Genero.TERROR, 450.0, Plataforma.PLAYSTATION, 3, "Juego de terror")
            );
            control.getControlTienda().agregarJuegoAlCatalogo(
                    new juego("J004", "Age of Empires", Genero.ESTRATEGIA, 250.0, Plataforma.PC, 6, "Juego de estrategia")
            );
        }
    }

    private void cargarCatalogoVisual() {
        panelCatalogo.removeAll();

        ListaSimple catalogo = control.getControlTienda().getCatalogo();

        for (int i = 0; i < catalogo.size(); i++) {
            juego juegoActual = (juego) catalogo.obtener(i);

            if (juegoActual != null) {
                TarjetaJuego tarjeta = new TarjetaJuego(juegoActual);

                tarjeta.getBtnAgregar().addActionListener(e -> {
                    boolean agregado = control.getControlTienda().agregarAlCarrito(juegoActual, 1);

                    if (agregado) {
                        actualizarCarritoVisual();
                        JOptionPane.showMessageDialog(this, "Juego agregado al carrito.");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo agregar. Verifica stock disponible.");
                    }
                });

                panelCatalogo.add(tarjeta);
            }
        }

        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }

    private void actualizarCarritoVisual() {
        modeloCarrito.clear();

        ListaSimple carrito = control.getControlTienda().getCarrito();

        for (int i = 0; i < carrito.size(); i++) {
            ItemCarrito item = (ItemCarrito) carrito.obtener(i);

            if (item != null) {
                modeloCarrito.addElement(item.toString());
            }
        }

        lblTotal.setText("Total: Q" + control.getControlTienda().calcularTotalCarrito());
    }

    private void confirmarCompra() {
        UsuarioSistema usuario = control.getUsuarioActual();

        Compra compra = control.getControlTienda().confirmarCompra(usuario);

        if (compra != null) {
            actualizarCarritoVisual();
            cargarCatalogoVisual();
            JOptionPane.showMessageDialog(this, "Compra realizada con éxito.\n" + compra.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo realizar la compra.");
        }
    }
}