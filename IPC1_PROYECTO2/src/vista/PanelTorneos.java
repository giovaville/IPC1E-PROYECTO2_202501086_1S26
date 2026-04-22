/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControlPrincipal;
import hilos.HiloTaquilla;
import modelo.TicketVendido;
import modelo.Torneo;
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
public class PanelTorneos extends JPanel {
    private ControlPrincipal control;

    private JLabel lblTitulo;
    private DefaultListModel<String> modeloTorneos;
    private JList<String> listaTorneos;

    private JTextField txtNombreUsuario;
    private JButton btnEntrarCola;
    private JButton btnVenderTicket;
    private JButton btnIniciarTaquillas;
    private JButton btnRegresar;

    private DefaultListModel<String> modeloTickets;
    private JList<String> listaTickets;

    private JLabel lblCola;
    private JLabel lblTaquilla1;
    private JLabel lblTaquilla2;

    private HiloTaquilla taquilla1;
    private HiloTaquilla taquilla2;

    public PanelTorneos(ControlPrincipal control) {
        this.control = control;

        setLayout(new BorderLayout());

        lblTitulo = new JLabel("TORNEOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        modeloTorneos = new DefaultListModel<>();
        listaTorneos = new JList<>(modeloTorneos);

        modeloTickets = new DefaultListModel<>();
        listaTickets = new JList<>(modeloTickets);

        txtNombreUsuario = new JTextField();
        btnEntrarCola = new JButton("Entrar a la cola");
        btnVenderTicket = new JButton("Vender ticket");
        btnIniciarTaquillas = new JButton("Iniciar taquillas");
        btnRegresar = new JButton("Regresar al menú");

        lblCola = new JLabel("Siguiente en cola: Nadie", SwingConstants.CENTER);
        lblTaquilla1 = new JLabel("Taquilla 1 libre", SwingConstants.CENTER);
        lblTaquilla2 = new JLabel("Taquilla 2 libre", SwingConstants.CENTER);

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 10));
        panelCentro.add(new JScrollPane(listaTorneos));
        panelCentro.add(new JScrollPane(listaTickets));

        JPanel panelInferior = new JPanel(new GridLayout(7, 1, 10, 10));
        panelInferior.add(new JLabel("Nombre del usuario:"));
        panelInferior.add(txtNombreUsuario);
        panelInferior.add(lblCola);
        panelInferior.add(lblTaquilla1);
        panelInferior.add(lblTaquilla2);
        panelInferior.add(btnEntrarCola);
        panelInferior.add(btnVenderTicket);

        JPanel panelEste = new JPanel(new GridLayout(2, 1, 10, 10));
        panelEste.add(btnIniciarTaquillas);
        panelEste.add(btnRegresar);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        add(panelEste, BorderLayout.EAST);

        cargarTorneosPruebaSiHaceFalta();
        actualizarListaTorneos();
        actualizarListaTickets();
        actualizarEstadoCola();

        btnEntrarCola.addActionListener(e -> entrarACola());
        btnVenderTicket.addActionListener(e -> venderTicketManual());
        btnIniciarTaquillas.addActionListener(e -> iniciarTaquillas());
        btnRegresar.addActionListener(e -> regresarMenu());
    }

    private void cargarTorneosPruebaSiHaceFalta() {
        if (control.getControlTorneos().getListaTorneos().estaVacia()) {
            control.getControlTorneos().agregarTorneo(
                    new Torneo("T001", "Copa FIFA",
                            new juego("J001", "FIFA 23", Genero.DEPORTES, 300, Plataforma.PC, 5, "Fútbol"),
                            "25/04/2026", "15:00", 50.0, 10)
            );

            control.getControlTorneos().agregarTorneo(
                    new Torneo("T002", "Battle Royale",
                            new juego("J002", "Fortnite", Genero.ACCION, 0, Plataforma.XBOX, 10, "Shooter"),
                            "26/04/2026", "18:00", 35.0, 8)
            );

            control.getControlTorneos().agregarTorneo(
                    new Torneo("T003", "Liga Estratégica",
                            new juego("J003", "Age of Empires", Genero.ESTRATEGIA, 250, Plataforma.PC, 6, "Estrategia"),
                            "27/04/2026", "14:00", 40.0, 6)
            );
        }
    }

    private void actualizarListaTorneos() {
        modeloTorneos.clear();

        ListaSimple torneos = control.getControlTorneos().getListaTorneos();

        for (int i = 0; i < torneos.size(); i++) {
            Torneo torneo = (Torneo) torneos.obtener(i);

            if (torneo != null) {
                modeloTorneos.addElement(torneo.toString());
            }
        }
    }

    private void actualizarListaTickets() {
        modeloTickets.clear();

        ListaSimple tickets = control.getControlTorneos().getTicketsVendidos();

        for (int i = 0; i < tickets.size(); i++) {
            TicketVendido ticket = (TicketVendido) tickets.obtener(i);

            if (ticket != null) {
                modeloTickets.addElement(ticket.toString());
            }
        }
    }

    private Torneo obtenerTorneoSeleccionado() {
        int indice = listaTorneos.getSelectedIndex();

        if (indice < 0) {
            return null;
        }

        return (Torneo) control.getControlTorneos().getListaTorneos().obtener(indice);
    }

    private void entrarACola() {
        String nombre = txtNombreUsuario.getText();

        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un nombre.");
            return;
        }

        boolean agregado = control.getControlTorneos().agregarUsuarioACola(nombre);

        if (agregado) {
            txtNombreUsuario.setText("");
            actualizarEstadoCola();
            JOptionPane.showMessageDialog(this, "Usuario agregado a la cola.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar a la cola.");
        }
    }

    private void venderTicketManual() {
        Torneo torneo = obtenerTorneoSeleccionado();

        if (torneo == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un torneo.");
            return;
        }

        TicketVendido ticket = control.getControlTorneos().venderTicket(torneo, control.getUsuarioActual());

        if (ticket != null) {
            actualizarListaTorneos();
            actualizarListaTickets();
            actualizarEstadoCola();
            JOptionPane.showMessageDialog(this, "Ticket vendido correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo vender el ticket.");
        }
    }

    private void iniciarTaquillas() {
        Torneo torneo = obtenerTorneoSeleccionado();

        if (torneo == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un torneo.");
            return;
        }

        if (!control.getControlTorneos().hayUsuariosEnCola()) {
            JOptionPane.showMessageDialog(this, "No hay usuarios en la cola.");
            return;
        }

        detenerTaquillasSiExisten();

        Runnable actualizador = () -> {
            actualizarListaTorneos();
            actualizarListaTickets();
            actualizarEstadoCola();
        };

        taquilla1 = new HiloTaquilla(
                "Taquilla 1",
                control.getControlTorneos(),
                torneo,
                control.getUsuarioActual(),
                lblTaquilla1,
                actualizador
        );

        taquilla2 = new HiloTaquilla(
                "Taquilla 2",
                control.getControlTorneos(),
                torneo,
                control.getUsuarioActual(),
                lblTaquilla2,
                actualizador
        );

        taquilla1.start();
        taquilla2.start();
    }

    private void detenerTaquillasSiExisten() {
        if (taquilla1 != null && taquilla1.isAlive()) {
            taquilla1.detenerTaquilla();
        }

        if (taquilla2 != null && taquilla2.isAlive()) {
            taquilla2.detenerTaquilla();
        }
    }

    private void actualizarEstadoCola() {
        String siguiente = control.getControlTorneos().verSiguienteEnCola();

        if (siguiente != null) {
            lblCola.setText("Siguiente en cola: " + siguiente);
        } else {
            lblCola.setText("Siguiente en cola: Nadie");
        }
    }

    private void regresarMenu() {
        detenerTaquillasSiExisten();

        Window ventana = SwingUtilities.getWindowAncestor(PanelTorneos.this);

        if (ventana instanceof VentanaPrincipal) {
            VentanaPrincipal vp = (VentanaPrincipal) ventana;
            vp.cambiarPanel(new PanelMenu(control));
        }
    }
}