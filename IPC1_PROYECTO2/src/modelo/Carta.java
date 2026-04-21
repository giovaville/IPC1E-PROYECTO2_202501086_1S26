/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import enums.TipoCarta;
import enums.Rareza;
/**
 *
 * @author Gio
 */
public class Carta {
    private int codigo;
    private String nombre;
    private TipoCarta tipo;
    private Rareza rareza;
    private int ataque;
    private int defensa;
    private int ps;
    private String imagen;
    private boolean obtenida;

    public Carta() {
        this.codigo = 0;
        this.nombre = "";
        this.tipo = null;
        this.rareza = null;
        this.ataque = 0;
        this.defensa = 0;
        this.ps = 0;
        this.imagen = "";
        this.obtenida = false;
    }

    public Carta(int codigo, String nombre, TipoCarta tipo, Rareza rareza,
                 int ataque, int defensa, int ps, String imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rareza = rareza;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ps = ps;
        this.imagen = imagen;
        this.obtenida = false;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCarta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarta tipo) {
        this.tipo = tipo;
    }

    public Rareza getRareza() {
        return rareza;
    }

    public void setRareza(Rareza rareza) {
        this.rareza = rareza;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isObtenida() {
        return obtenida;
    }

    public void setObtenida(boolean obtenida) {
        this.obtenida = obtenida;
    }

    @Override
    public String toString() {
        return nombre + " [" + tipo + " - " + rareza + "]";
    }
}