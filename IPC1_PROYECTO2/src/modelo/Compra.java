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

    public void setDetalles(ListaSimple detalles) {
        if (detalles != null) {
            this.detalles = detalles;
            recalcularTotal();
        }
    }

    public void agregarDetalle(DetalleCompra detalle) {
        if (detalle != null) {
            detalles.agregarAlFinal(detalle);
            total += detalle.getSubtotal();
        }
    }

    public DetalleCompra obtenerDetalle(int indice) {
        Object obj = detalles.obtener(indice);

        if (obj instanceof DetalleCompra) {
            return (DetalleCompra) obj;
        }

        return null;
    }

    public int cantidadDetalles() {
        return detalles.size();
    }

    public void recalcularTotal() {
        total = 0.0;

        for (int i = 0; i < detalles.size(); i++) {
            DetalleCompra detalle = (DetalleCompra) detalles.obtener(i);

            if (detalle != null) {
                total += detalle.getSubtotal();
            }
        }
    }

    @Override
    public String toString() {
        return "Compra #" + idCompra + " - Fecha: " + fecha + " - Total: Q" + total;
    }
}

