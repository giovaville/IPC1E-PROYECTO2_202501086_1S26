/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ipc1_proyecto2;
import controlador.ControlPrincipal;
import vista.*;

import javax.swing.SwingUtilities;
/**
 *
 * @author Gio
 */
public class IPC1_PROYECTO2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            ControlPrincipal control = new ControlPrincipal();

            VentanaPrincipal ventana = new VentanaPrincipal(control);

            PanelMenu menu = new PanelMenu(control);

            menu.getBtnTienda().addActionListener(e ->
                ventana.cambiarPanel(new PanelTienda(control))
            );

            menu.getBtnAlbum().addActionListener(e ->
                ventana.cambiarPanel(new PanelAlbum(control))
            );

            menu.getBtnTorneos().addActionListener(e ->
                ventana.cambiarPanel(new PanelTorneos(control))
            );

            menu.getBtnRecompensas().addActionListener(e ->
                ventana.cambiarPanel(new PanelRecompensas(control))
            );

            menu.getBtnReportes().addActionListener(e ->
                ventana.cambiarPanel(new PanelReportes(control))
            );

            menu.getBtnEstudiante().addActionListener(e ->
                ventana.cambiarPanel(new PanelEstudiante(control))
            );

            menu.getBtnSalir().addActionListener(e ->
                System.exit(0)
            );

            ventana.cambiarPanel(menu);
            ventana.setVisible(true);
        });
    }
}