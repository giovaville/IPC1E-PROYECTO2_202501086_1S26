/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import Estructuras.ListaSimple;
import reportes.GeneradorReportes;
/**
 *
 * @author Gio
 */
public class ControlReportes {
    public ControlReportes() {
    }

    public void generarReporteInventario(ListaSimple catalogo) {
        if (catalogo != null) {
            GeneradorReportes.reporteInventario(catalogo);
        }
    }

    public void generarReporteCompras(ListaSimple compras) {
        if (compras != null) {
            GeneradorReportes.reporteCompras(compras);
        }
    }

    public void generarReporteAlbum(ListaSimple cartas) {
        if (cartas != null) {
            GeneradorReportes.reporteAlbum(cartas);
        }
    }

    public void generarReporteTickets(ListaSimple tickets) {
        if (tickets != null) {
            GeneradorReportes.reporteTickets(tickets);
        }
    }

    public void generarReporteLeaderboard(ListaSimple usuarios) {
        if (usuarios != null) {
            GeneradorReportes.reporteLeaderboard(usuarios);
        }
    }
}