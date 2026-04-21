/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import modelo.juego;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
/**
 *
 * @author Gio
 */
public class TarjetaJuego extends JPanel {

    private juego juego;
    private JLabel lblNombre;
    private JLabel lblGenero;
    private JLabel lblPlataforma;
    private JLabel lblPrecio;
    private JLabel lblStock;
    private JButton btnAgregar;

    public TarjetaJuego(juego juego) {
        this.juego = juego;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());

        lblNombre = new JLabel();
        lblGenero = new JLabel();
        lblPlataforma = new JLabel();
        lblPrecio = new JLabel();
        lblStock = new JLabel();
        btnAgregar = new JButton("Agregar al carrito");

        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(5, 1));

        panelInfo.add(lblNombre);
        panelInfo.add(lblGenero);
        panelInfo.add(lblPlataforma);
        panelInfo.add(lblPrecio);
        panelInfo.add(lblStock);

        add(panelInfo, BorderLayout.CENTER);
        add(btnAgregar, BorderLayout.SOUTH);

        actualizarDatos();
    }

    public void actualizarDatos() {
        if (juego != null) {
            lblNombre.setText("Nombre: " + juego.getNombre());
            lblGenero.setText("Género: " + juego.getGenero());
            lblPlataforma.setText("Plataforma: " + juego.getPlataforma());
            lblPrecio.setText("Precio: Q" + juego.getPrecio());
            lblStock.setText("Stock: " + juego.getStock());
        } else {
            lblNombre.setText("Sin juego");
            lblGenero.setText("");
            lblPlataforma.setText("");
            lblPrecio.setText("");
            lblStock.setText("");
        }
    }

    public juego getJuego() {
        return juego;
    }

    public void setJuego(juego juego) {
        this.juego = juego;
        actualizarDatos();
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }
}
