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
        NodoMatriz[][] nodos = new NodoMatriz[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                nodos[i][j] = new NodoMatriz(i, j);
            }
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                if (i > 0) {
                    nodos[i][j].setArriba(nodos[i - 1][j]);
                }

                if (i < filas - 1) {
                    nodos[i][j].setAbajo(nodos[i + 1][j]);
                }

                if (j > 0) {
                    nodos[i][j].setIzquierda(nodos[i][j - 1]);
                }

                if (j < columnas - 1) {
                    nodos[i][j].setDerecha(nodos[i][j + 1]);
                }
            }
        }

        inicio = nodos[0][0];
    }

    public NodoMatriz obtenerNodo(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return null;
        }

        NodoMatriz actual = inicio;

        for (int i = 0; i < fila; i++) {
            if (actual != null) {
                actual = actual.getAbajo();
            }
        }

        for (int j = 0; j < columna; j++) {
            if (actual != null) {
                actual = actual.getDerecha();
            }
        }

        return actual;
    }

    public boolean agregarCarta(Carta carta) {
        if (carta == null) {
            return false;
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                NodoMatriz nodo = obtenerNodo(i, j);

                if (nodo != null && nodo.estaVacio()) {
                    nodo.setDato(carta);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean intercambiarCartas(int fila1, int columna1, int fila2, int columna2) {
        NodoMatriz nodo1 = obtenerNodo(fila1, columna1);
        NodoMatriz nodo2 = obtenerNodo(fila2, columna2);

        if (nodo1 == null || nodo2 == null) {
            return false;
        }

        Carta temp = nodo1.getDato();
        nodo1.setDato(nodo2.getDato());
        nodo2.setDato(temp);

        return true;
    }

    public NodoMatriz buscarPorNombre(String nombre) {
        if (nombre == null) {
            return null;
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                NodoMatriz nodo = obtenerNodo(i, j);

                if (nodo != null && nodo.getDato() != null) {
                    if (nodo.getDato().getNombre().equalsIgnoreCase(nombre)) {
                        return nodo;
                    }
                }
            }
        }

        return null;
    }

    public NodoMatriz buscarPorTipo(TipoCarta tipo) {
        if (tipo == null) {
            return null;
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                NodoMatriz nodo = obtenerNodo(i, j);

                if (nodo != null && nodo.getDato() != null) {
                    if (nodo.getDato().getTipo() == tipo) {
                        return nodo;
                    }
                }
            }
        }

        return null;
    }

    public NodoMatriz buscarPorRareza(Rareza rareza) {
        if (rareza == null) {
            return null;
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                NodoMatriz nodo = obtenerNodo(i, j);

                if (nodo != null && nodo.getDato() != null) {
                    if (nodo.getDato().getRareza() == rareza) {
                        return nodo;
                    }
                }
            }
        }

        return null;
    }

    public boolean estaLlena() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                NodoMatriz nodo = obtenerNodo(i, j);

                if (nodo != null && nodo.estaVacio()) {
                    return false;
                }
            }
        }

        return true;
    }

    public int contarCartas() {
        int contador = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                NodoMatriz nodo = obtenerNodo(i, j);

                if (nodo != null && nodo.getDato() != null) {
                    contador++;
                }
            }
        }

        return contador;
    }
}