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
    private double subtotal;

    public ItemCarrito() {
        this.juego = null;
        this.cantidad = 0;
        this.subtotal = 0.0;
    }

    public ItemCarrito(juego juego, int cantidad) {
        this.juego = juego;
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public juego getJuego() {
        return juego;
    }

    public void setJuego(juego juego) {
        this.juego = juego;
        this.subtotal = calcularSubtotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double calcularSubtotal() {
        if (juego != null) {
            return juego.getPrecio() * cantidad;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        if (juego != null) {
            return juego.getNombre() + " x" + cantidad + " - Q" + subtotal;
        }
        return "Item vacío";
    }
}