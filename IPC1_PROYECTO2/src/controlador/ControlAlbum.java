/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import Estructuras.ListaSimple;
import Estructuras.MatrizAlbum;
import Estructuras.NodoMatriz;
import enums.Rareza;
import enums.TipoCarta;
import modelo.Carta;

/**
 *
 * @author Gio
 */
public class ControlAlbum {
    private MatrizAlbum album;
    private ListaSimple cartasDisponibles;

    public ControlAlbum() {
        this.album = new MatrizAlbum(4, 6);
        this.cartasDisponibles = new ListaSimple();
        cargarCartasBase();
    }

    public MatrizAlbum getAlbum() {
        return album;
    }

    public ListaSimple getCartasDisponibles() {
        return cartasDisponibles;
    }

    private void cargarCartasBase() {
        cartasDisponibles.agregarAlFinal(new Carta(1, "Dragon Rojo", TipoCarta.FUEGO, Rareza.RARA, 90, 70, 100, "dragon.png"));
        cartasDisponibles.agregarAlFinal(new Carta(2, "Tortuga Marina", TipoCarta.AGUA, Rareza.COMUN, 40, 60, 80, "tortuga.png"));
        cartasDisponibles.agregarAlFinal(new Carta(3, "Guerrero Bosque", TipoCarta.PLANTA, Rareza.POCO_COMUN, 55, 50, 75, "bosque.png"));
        cartasDisponibles.agregarAlFinal(new Carta(4, "Rayo Azul", TipoCarta.ELECTRICO, Rareza.ULTRA_RARA, 100, 65, 95, "rayo.png"));
        cartasDisponibles.agregarAlFinal(new Carta(5, "Sombra Nocturna", TipoCarta.OSCURO, Rareza.LEGENDARIA, 120, 95, 110, "sombra.png"));
        cartasDisponibles.agregarAlFinal(new Carta(6, "Caballero Acero", TipoCarta.ACERO, Rareza.RARA, 85, 100, 105, "acero.png"));
        cartasDisponibles.agregarAlFinal(new Carta(7, "Mente Psiquica", TipoCarta.PSIQUICO, Rareza.POCO_COMUN, 70, 55, 90, "psiquico.png"));
        cartasDisponibles.agregarAlFinal(new Carta(8, "Bestia Normal", TipoCarta.NORMAL, Rareza.COMUN, 35, 35, 50, "normal.png"));
    }

    public boolean agregarCartaAlAlbum(Carta carta) {
        if (carta == null) {
            return false;
        }
        return album.agregarCarta(carta);
    }

    public boolean agregarCartaPorCodigo(int codigo) {
        Carta carta = buscarCartaDisponiblePorCodigo(codigo);

        if (carta == null) {
            return false;
        }

        return album.agregarCarta(carta);
    }

    public Carta buscarCartaDisponiblePorCodigo(int codigo) {
        for (int i = 0; i < cartasDisponibles.size(); i++) {
            Carta carta = (Carta) cartasDisponibles.obtener(i);

            if (carta != null && carta.getCodigo() == codigo) {
                return carta;
            }
        }

        return null;
    }

    public NodoMatriz buscarCartaEnAlbumPorNombre(String nombre) {
        return album.buscarPorNombre(nombre);
    }

    public NodoMatriz buscarCartaEnAlbumPorTipo(TipoCarta tipo) {
        return album.buscarPorTipo(tipo);
    }

    public NodoMatriz buscarCartaEnAlbumPorRareza(Rareza rareza) {
        return album.buscarPorRareza(rareza);
    }

    public boolean intercambiarCartas(int fila1, int columna1, int fila2, int columna2) {
        return album.intercambiarCartas(fila1, columna1, fila2, columna2);
    }

    public int contarCartasEnAlbum() {
        return album.contarCartas();
    }

    public boolean albumLleno() {
        return album.estaLlena();
    }

    public boolean filaCompleta(int fila) {
        if (fila < 0 || fila >= album.getFilas()) {
            return false;
        }

        for (int j = 0; j < album.getColumnas(); j++) {
            NodoMatriz nodo = album.obtenerNodo(fila, j);

            if (nodo == null || nodo.getDato() == null) {
                return false;
            }
        }

        return true;
    }

    public int contarCartasLegendarias() {
        int contador = 0;

        for (int i = 0; i < album.getFilas(); i++) {
            for (int j = 0; j < album.getColumnas(); j++) {
                NodoMatriz nodo = album.obtenerNodo(i, j);

                if (nodo != null && nodo.getDato() != null &&
                        nodo.getDato().getRareza() == Rareza.LEGENDARIA) {
                    contador++;
                }
            }
        }

        return contador;
    }

    public ListaSimple obtenerCartasDelAlbum() {
        ListaSimple cartas = new ListaSimple();

        for (int i = 0; i < album.getFilas(); i++) {
            for (int j = 0; j < album.getColumnas(); j++) {
                NodoMatriz nodo = album.obtenerNodo(i, j);

                if (nodo != null && nodo.getDato() != null) {
                    cartas.agregarAlFinal(nodo.getDato());
                }
            }
        }

        return cartas;
    }
}
