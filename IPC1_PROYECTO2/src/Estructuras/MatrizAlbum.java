/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;
import modelo.Carta;
import enums.TipoCarta;
import enums.Rareza;
/**
 *
 * @author Gio
 */
public class MatrizAlbum {
    private NodoMatriz inicio;
    private int filas;
    private int columnas;

    public MatrizAlbum(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.inicio = null;
        construirMatriz();
    }

    public NodoMatriz getInicio() {
        return inicio;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    private void construirMatriz() {
        if (filas <= 0 || columnas <= 0) {
            inicio = null;
            return;
        }

        NodoMatriz filaAnteriorInicio = null;

        for (int i = 0; i < filas; i++) {
            NodoMatriz nodoIzquierdo = null;
            NodoMatriz nodoArriba = filaAnteriorInicio;
            NodoMatriz inicioFilaActual = null;

            for (int j = 0; j < columnas; j++) {
                NodoMatriz nuevo = new NodoMatriz(i, j);

                if (i == 0 && j == 0) {
                    inicio = nuevo;
                }

                if (j == 0) {
                    inicioFilaActual = nuevo;
                }

                if (nodoIzquierdo != null) {
                    nodoIzquierdo.setDerecha(nuevo);
                    nuevo.setIzquierda(nodoIzquierdo);
                }

                if (nodoArriba != null) {
                    nodoArriba.setAbajo(nuevo);
                    nuevo.setArriba(nodoArriba);
                    nodoArriba = nodoArriba.getDerecha();
                }

                nodoIzquierdo = nuevo;
            }

            filaAnteriorInicio = inicioFilaActual;
        }
    }

    public NodoMatriz obtenerNodo(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas || inicio == null) {
            return null;
        }

        NodoMatriz actual = inicio;

        for (int i = 0; i < fila; i++) {
            actual = actual.getAbajo();
        }

        for (int j = 0; j < columna; j++) {
            actual = actual.getDerecha();
        }

        return actual;
    }

    public boolean agregarCarta(Carta carta) {
        if (carta == null) {
            return false;
        }

        NodoMatriz filaActual = inicio;

        while (filaActual != null) {
            NodoMatriz actual = filaActual;

            while (actual != null) {
                if (actual.estaVacio()) {
                    actual.setDato(carta);
                    return true;
                }

                actual = actual.getDerecha();
            }

            filaActual = filaActual.getAbajo();
        }

        return false;
    }

    public boolean intercambiarCartas(int fila1, int columna1, int fila2, int columna2) {
        NodoMatriz nodo1 = obtenerNodo(fila1, columna1);
        NodoMatriz nodo2 = obtenerNodo(fila2, columna2);

        if (nodo1 == null || nodo2 == null) {
            return false;
        }

        Carta temporal = nodo1.getDato();
        nodo1.setDato(nodo2.getDato());
        nodo2.setDato(temporal);

        return true;
    }

    public NodoMatriz buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        NodoMatriz filaActual = inicio;

        while (filaActual != null) {
            NodoMatriz actual = filaActual;

            while (actual != null) {
                if (actual.getDato() != null &&
                    actual.getDato().getNombre().equalsIgnoreCase(nombre.trim())) {
                    return actual;
                }

                actual = actual.getDerecha();
            }

            filaActual = filaActual.getAbajo();
        }

        return null;
    }

    public NodoMatriz buscarPorTipo(TipoCarta tipo) {
        if (tipo == null) {
            return null;
        }

        NodoMatriz filaActual = inicio;

        while (filaActual != null) {
            NodoMatriz actual = filaActual;

            while (actual != null) {
                if (actual.getDato() != null && actual.getDato().getTipo() == tipo) {
                    return actual;
                }

                actual = actual.getDerecha();
            }

            filaActual = filaActual.getAbajo();
        }

        return null;
    }

    public NodoMatriz buscarPorRareza(Rareza rareza) {
        if (rareza == null) {
            return null;
        }

        NodoMatriz filaActual = inicio;

        while (filaActual != null) {
            NodoMatriz actual = filaActual;

            while (actual != null) {
                if (actual.getDato() != null && actual.getDato().getRareza() == rareza) {
                    return actual;
                }

                actual = actual.getDerecha();
            }

            filaActual = filaActual.getAbajo();
        }

        return null;
    }

    public boolean estaLlena() {
        NodoMatriz filaActual = inicio;

        while (filaActual != null) {
            NodoMatriz actual = filaActual;

            while (actual != null) {
                if (actual.estaVacio()) {
                    return false;
                }

                actual = actual.getDerecha();
            }

            filaActual = filaActual.getAbajo();
        }

        return true;
    }

    public int contarCartas() {
        int contador = 0;
        NodoMatriz filaActual = inicio;

        while (filaActual != null) {
            NodoMatriz actual = filaActual;

            while (actual != null) {
                if (actual.getDato() != null) {
                    contador++;
                }

                actual = actual.getDerecha();
            }

            filaActual = filaActual.getAbajo();
        }

        return contador;
    }
}