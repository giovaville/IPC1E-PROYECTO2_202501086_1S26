/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author Gio
 */
public class ListaSimple { 

    private NodoSimple cabeza;
    private int tamanio;

    public ListaSimple() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int size() {
        return tamanio;
    }

    public void agregarAlInicio(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);

        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        tamanio++;
    }

    public void agregarAlFinal(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);

        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            NodoSimple auxiliar = cabeza;

            while (auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }

            auxiliar.setSiguiente(nuevo);
        }

        tamanio++;
    }

    public Object obtener(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }

        NodoSimple auxiliar = cabeza;
        int contador = 0;

        while (auxiliar != null) {
            if (contador == indice) {
                return auxiliar.getDato();
            }

            auxiliar = auxiliar.getSiguiente();
            contador++;
        }

        return null;
    }

    public boolean eliminar(int indice) {
        if (indice < 0 || indice >= tamanio || estaVacia()) {
            return false;
        }

        if (indice == 0) {
            cabeza = cabeza.getSiguiente();
            tamanio--;
            return true;
        }

        NodoSimple anterior = cabeza;
        int contador = 0;

        while (anterior != null && contador < indice - 1) {
            anterior = anterior.getSiguiente();
            contador++;
        }

        if (anterior != null && anterior.getSiguiente() != null) {
            anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
            tamanio--;
            return true;
        }

        return false;
    }
    public void set(int indice, Object dato) {
    if (indice < 0 || indice >= tamanio) {
        return;
    }

    NodoSimple actual = cabeza;
    int contador = 0;

    while (actual != null) {
        if (contador == indice) {
            actual.setDato(dato);
            return;
        }
        actual = actual.getSiguiente();
        contador++;
    }
}

    public void limpiar() {
        cabeza = null;
        tamanio = 0;
    }

    public NodoSimple getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoSimple cabeza) {
        this.cabeza = cabeza;
    }
}

