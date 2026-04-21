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
public class UsuarioSistema {
    private String nombre;
    private int xp;
    private int nivel;
    private String rango;
    private ListaSimple compras;
    private ListaSimple logros;

    public UsuarioSistema() {
        this.nombre = "";
        this.xp = 0;
        this.nivel = 1;
        this.rango = "Aprendiz";
        this.compras = new ListaSimple();
        this.logros = new ListaSimple();
    }

    public UsuarioSistema(String nombre) {
        this.nombre = nombre;
        this.xp = 0;
        this.nivel = 1;
        this.rango = "Aprendiz";
        this.compras = new ListaSimple();
        this.logros = new ListaSimple();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
        actualizarNivelYRango();
    }

    public int getNivel() {
        return nivel;
    }

    public String getRango() {
        return rango;
    }

    public ListaSimple getCompras() {
        return compras;
    }

    public ListaSimple getLogros() {
        return logros;
    }

    public void agregarXp(int puntos) {
        if (puntos > 0) {
            this.xp += puntos;
            actualizarNivelYRango();
        }
    }

    public void agregarCompra(Compra compra) {
        if (compra != null) {
            compras.agregarAlInicio(compra);
        }
    }

    public void agregarLogro(Logro logro) {
        if (logro != null) {
            logros.agregarAlFinal(logro);
        }
    }

    public int getXpActualDelNivel() {
        if (nivel == 1) {
            return xp;
        } else if (nivel == 2) {
            return xp - 500;
        } else if (nivel == 3) {
            return xp - 1500;
        } else if (nivel == 4) {
            return xp - 3500;
        } else {
            return xp - 7000;
        }
    }

    public int getXpNecesariaNivel() {
        if (nivel == 1) {
            return 500;
        } else if (nivel == 2) {
            return 1000;
        } else if (nivel == 3) {
            return 2000;
        } else if (nivel == 4) {
            return 3500;
        } else {
            return 0;
        }
    }

    private void actualizarNivelYRango() {
        if (xp >= 7000) {
            nivel = 5;
            rango = "Leyenda";
        } else if (xp >= 3500) {
            nivel = 4;
            rango = "Maestro";
        } else if (xp >= 1500) {
            nivel = 3;
            rango = "Veterano";
        } else if (xp >= 500) {
            nivel = 2;
            rango = "Jugador";
        } else {
            nivel = 1;
            rango = "Aprendiz";
        }
    }

    @Override
    public String toString() {
        return nombre + " - Nivel " + nivel + " - " + rango + " - XP: " + xp;
    }
}