/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gio
 */
public class ItemCarrito {

    private juego juego;
    private int cantidad;

    public ItemCarrito() {
        this.juego = null;
        this.cantidad = 0;
    }

    public ItemCarrito(juego juego, int cantidad) {
        this.juego = juego;
        this.cantidad = cantidad;
    }

    public juego getJuego() {
        return juego;
    }

    public void setJuego(juego juego) {
        this.juego = juego;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        if (juego != null) {
            return juego.getPrecio() * cantidad;
        }
        return 0.0;
    }

    public void aumentarCantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
        }
    }

    public void disminuirCantidad(int cantidad) {
        if (cantidad > 0 && this.cantidad >= cantidad) {
            this.cantidad -= cantidad;
        }
    }

    @Override
    public String toString() {
        if (juego != null) {
            return juego.getNombre() + " x" + cantidad + " - Q" + getSubtotal();
        }
        return "Item vacío";
    }
}

