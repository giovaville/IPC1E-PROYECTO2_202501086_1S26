/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import Estructuras.ListaSimple;
import modelo.Carta;
import modelo.ItemCarrito;
import modelo.TicketVendido;
import modelo.UsuarioSistema;

/**
 *
 * @author Gio
 */
public class Convertidor {
    public static String listaACadena(ListaSimple lista) {
        if (lista == null || lista.estaVacia()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lista.size(); i++) {
            Object obj = lista.obtener(i);

            if (obj != null) {
                sb.append(obj.toString());

                if (i < lista.size() - 1) {
                    sb.append("\n");
                }
            }
        }

        return sb.toString();
    }

    public static String carritoACadena(ListaSimple carrito) {
        if (carrito == null || carrito.estaVacia()) {
            return "Carrito vacío";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < carrito.size(); i++) {
            ItemCarrito item = (ItemCarrito) carrito.obtener(i);

            if (item != null) {
                sb.append(item.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public static String ticketsACadena(ListaSimple tickets) {
        if (tickets == null || tickets.estaVacia()) {
            return "Sin tickets";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tickets.size(); i++) {
            TicketVendido ticket = (TicketVendido) tickets.obtener(i);

            if (ticket != null) {
                sb.append(ticket.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public static String cartasACadena(ListaSimple cartas) {
        if (cartas == null || cartas.estaVacia()) {
            return "Sin cartas";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cartas.size(); i++) {
            Carta carta = (Carta) cartas.obtener(i);

            if (carta != null) {
                sb.append(carta.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public static String leaderboardACadena(ListaSimple usuarios) {
        if (usuarios == null || usuarios.estaVacia()) {
            return "Sin datos";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < usuarios.size(); i++) {
            UsuarioSistema u = (UsuarioSistema) usuarios.obtener(i);

            if (u != null) {
                sb.append((i + 1)).append(". ")
                  .append(u.getNombre())
                  .append(" - Nivel ")
                  .append(u.getNivel())
                  .append(" - XP ")
                  .append(u.getXp())
                  .append("\n");
            }
        }

        return sb.toString();
    }
}
