/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import enums.Genero;
import enums.Plataforma;

/**
 *
 * @author Gio
 */
public class juego {


    private String codigo;
    private String nombre;
    private Genero genero;
    private double precio;
    private Plataforma plataforma;
    private int stock;
    private String descripcion;

    public juego() {
        this.codigo = "";
        this.nombre = "";
        this.genero = Genero.ACCION;
        this.precio = 0.0;
        this.plataforma = Plataforma.PC;
        this.stock = 0;
        this.descripcion = "";
    }

    public juego(String codigo, String nombre, Genero genero, double precio,
                 Plataforma plataforma, int stock, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
        this.plataforma = plataforma;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean tieneStock() {
        return stock > 0;
    }

    public boolean tieneStockSuficiente(int cantidad) {
        return cantidad > 0 && stock >= cantidad;
    }

    public void reducirStock(int cantidad) {
        if (cantidad > 0 && stock >= cantidad) {
            stock = stock - cantidad;
        }
    }

    public void aumentarStock(int cantidad) {
        if (cantidad > 0) {
            stock = stock + cantidad;
        }
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - " + genero + " - Q" + precio
                + " - " + plataforma + " - Stock: " + stock;
    }
}

