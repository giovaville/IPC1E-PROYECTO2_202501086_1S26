/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import modelo.Carta;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
/**
 *
 * @author Gio
 */
public class CeldaCarta extends JPanel {

    private Carta carta;
    private JLabel lblNombre;
    private JLabel lblTipo;
    private JLabel lblRareza;

    public CeldaCarta() {
        this.carta = null;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        lblNombre = new JLabel("Vacía", SwingConstants.CENTER);
        lblTipo = new JLabel("", SwingConstants.CENTER);
        lblRareza = new JLabel("", SwingConstants.CENTER);

        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(3, 1));
        panelInfo.add(lblNombre);
        panelInfo.add(lblTipo);
        panelInfo.add(lblRareza);

        add(panelInfo, BorderLayout.CENTER);

        actualizarVista();
    }

    public CeldaCarta(Carta carta) {
        this();
        this.carta = carta;
        actualizarVista();
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
        actualizarVista();
    }

    public boolean estaVacia() {
        return carta == null;
    }

    public void actualizarVista() {
        if (carta == null) {
            setBackground(new Color(220, 220, 220));
            lblNombre.setText("Vacía");
            lblTipo.setText("");
            lblRareza.setText("");
        } else {
            setBackground(Color.WHITE);
            lblNombre.setText(carta.getNombre());
            lblTipo.setText("Tipo: " + carta.getTipo());
            lblRareza.setText("Rareza: " + carta.getRareza());
        }

        repaint();
    }
}