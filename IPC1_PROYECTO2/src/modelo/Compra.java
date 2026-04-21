/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import Estructuras.ListaSimple;
/**
 *
 * @author Gio
 */
public class Compra {

    private String idCompra;
    private String fecha;
    private double total;
    private ListaSimple detalles;

    public Compra() {
        this.idCompra = "";
        this.fecha = "";
        this.total = 0.0;
        this.detalles = new ListaSimple();
    }

    public Compra(String idCompra, String fecha) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.total = 0.0;
        this.detalles = new ListaSimple();
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public ListaSimple getDetalles() {
        return detalles;
    }

    public void agregarDetalle(DetalleCompra detalle) {
        if (detalle != null) {
            detalles.agregarAlFinal(detalle);
            total += detalle.getSubtotal();
        }
    }

    public void recalcularTotal() {
        total = 0.0;

        for (int i = 0; i < detalles.size(); i++) {
            DetalleCompra d = (DetalleCompra) detalles.obtener(i);
            total += d.getSubtotal();
        }
    }

    @Override
    public String toString() {
        return "Compra #" + idCompra + " - Fecha: " + fecha + " - Total: Q" + total;
    }
}

