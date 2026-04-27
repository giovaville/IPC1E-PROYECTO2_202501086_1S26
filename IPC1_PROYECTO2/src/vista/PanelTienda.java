/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import Estructuras.ListaSimple;
import controlador.ControlPrincipal;
import enums.Genero;
import enums.Plataforma;
import modelo.Compra;
import modelo.ItemCarrito;
import modelo.juego;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Gio
 */
public class PanelTienda extends JPanel {
    private ControlPrincipal control;

    private JLabel lblTitulo;
    private JTextField txtBuscar;
    private JComboBox<String> cmbGenero;
    private JComboBox<String> cmbPlataforma;

    private JPanel panelCatalogo;
    private DefaultListModel<String> modeloCarrito;
    private JList<String> listaCarrito;

    private JLabel lblTotal;
    private JButton btnComprar;
    private JButton btnEliminarSeleccionado;
    private JButton btnLimpiarFiltros;
    private JButton btnRegresar;

    public PanelTienda(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("TIENDA DE VIDEOJUEGOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        txtBuscar = new JTextField();

        cmbGenero = new JComboBox<>();
        cmbGenero.addItem("TODOS");
        cmbGenero.addItem("ACCION");
        cmbGenero.addItem("RPG");
        cmbGenero.addItem("ESTRATEGIA");
        cmbGenero.addItem("DEPORTES");
        cmbGenero.addItem("TERROR");
        cmbGenero.addItem("AVENTURA");

        cmbPlataforma = new JComboBox<>();
        cmbPlataforma.addItem("TODAS");
        cmbPlataforma.addItem("PC");
        cmbPlataforma.addItem("PLAYSTATION");
        cmbPlataforma.addItem("XBOX");
        cmbPlataforma.addItem("NINTENDO_SWITCH");

        btnLimpiarFiltros = new JButton("Limpiar filtros");

        JPanel panelFiltros = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFiltros.add(new JLabel("Buscar:"));
        panelFiltros.add(txtBuscar);
        panelFiltros.add(new JLabel("Género:"));
        panelFiltros.add(cmbGenero);
        panelFiltros.add(new JLabel("Plataforma:"));
        panelFiltros.add(cmbPlataforma);
        panelFiltros.add(new JLabel(""));
        panelFiltros.add(btnLimpiarFiltros);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        panelSuperior.add(panelFiltros, BorderLayout.CENTER);

        panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 2, 10, 10));

        JScrollPane scrollCatalogo = new JScrollPane(panelCatalogo);

        modeloCarrito = new DefaultListModel<>();
        listaCarrito = new JList<>(modeloCarrito);

        lblTotal = new JLabel("Total: Q0.0", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));

        btnComprar = new JButton("Confirmar compra");
        btnEliminarSeleccionado = new JButton("Eliminar seleccionado");
        btnRegresar = new JButton("Regresar al menú");

        JPanel panelBotonesCarrito = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotonesCarrito.add(lblTotal);
        panelBotonesCarrito.add(btnComprar);
        panelBotonesCarrito.add(btnEliminarSeleccionado);
        panelBotonesCarrito.add(btnRegresar);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(new JLabel("CARRITO", SwingConstants.CENTER), BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(listaCarrito), BorderLayout.CENTER);
        panelDerecho.add(panelBotonesCarrito, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollCatalogo, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);

        cargarDatosPruebaSiHaceFalta();
        cargarCatalogoVisual(control.getControlTienda().getCatalogo());
        actualizarCarritoVisual();

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                aplicarFiltros();
            }
        });

        cmbGenero.addActionListener(e -> aplicarFiltros());
        cmbPlataforma.addActionListener(e -> aplicarFiltros());

        btnLimpiarFiltros.addActionListener(e -> {
            txtBuscar.setText("");
            cmbGenero.setSelectedIndex(0);
            cmbPlataforma.setSelectedIndex(0);
            cargarCatalogoVisual(control.getControlTienda().getCatalogo());
        });

        btnComprar.addActionListener(e -> confirmarCompra());
        btnEliminarSeleccionado.addActionListener(e -> eliminarSeleccionado());
        btnRegresar.addActionListener(e -> regresarMenu());
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
            control.getControlTienda().agregarJuegoAlCatalogo(
                    new juego("J005", "Final Fantasy", Genero.RPG, 500.0, Plataforma.PLAYSTATION, 4, "Juego RPG")
            );
        }
    }

    private void aplicarFiltros() {
        ListaSimple filtrados = new ListaSimple();

        String texto = txtBuscar.getText().trim().toLowerCase();
        String genero = cmbGenero.getSelectedItem().toString();
        String plataforma = cmbPlataforma.getSelectedItem().toString();

        ListaSimple catalogo = control.getControlTienda().getCatalogo();

        for (int i = 0; i < catalogo.size(); i++) {
            juego actual = (juego) catalogo.obtener(i);

            if (actual != null) {
                boolean coincideTexto = texto.isEmpty()
                        || actual.getNombre().toLowerCase().contains(texto)
                        || actual.getCodigo().toLowerCase().contains(texto);

                boolean coincideGenero = genero.equals("TODOS")
                        || actual.getGenero().name().equals(genero);

                boolean coincidePlataforma = plataforma.equals("TODAS")
                        || actual.getPlataforma().name().equals(plataforma);

                if (coincideTexto && coincideGenero && coincidePlataforma) {
                    filtrados.agregarAlFinal(actual);
                }
            }
        }

        cargarCatalogoVisual(filtrados);
    }

    private void cargarCatalogoVisual(ListaSimple catalogo) {
        panelCatalogo.removeAll();

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
        Compra compra = control.getControlTienda().confirmarCompra(control.getUsuarioActual());

        if (compra != null) {
            control.getControlRecompensas().registrarLogroManual(
                    control.getUsuarioActual(),
                    "Comprador"
            );

            actualizarCarritoVisual();
            aplicarFiltros();

            JOptionPane.showMessageDialog(this, "Compra realizada con éxito.\n" + compra.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo realizar la compra.");
        }
    }

    private void eliminarSeleccionado() {
        int indice = listaCarrito.getSelectedIndex();

        if (indice < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un elemento del carrito.");
            return;
        }

        boolean eliminado = control.getControlTienda().eliminarDelCarrito(indice);

        if (eliminado) {
            actualizarCarritoVisual();
            JOptionPane.showMessageDialog(this, "Elemento eliminado del carrito.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el elemento.");
        }
    }

    private void regresarMenu() {
        Window ventana = SwingUtilities.getWindowAncestor(PanelTienda.this);

        if (ventana instanceof VentanaPrincipal) {
            VentanaPrincipal vp = (VentanaPrincipal) ventana;
            vp.cambiarPanel(new PanelMenu(control));
        }
    }
}