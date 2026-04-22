/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;
import Estructuras.ListaSimple;
import modelo.Compra;
import modelo.Carta;
import modelo.TicketVendido;
import modelo.UsuarioSistema;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Gio
 */
public class GeneradorReportes {
    private static String generarNombreArchivo(String nombreBase) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fecha = LocalDateTime.now().format(formatter);
        return nombreBase + "_" + fecha + ".html";
    }

    private static void abrirArchivo(String ruta) {
        try {
            File archivo = new File(ruta);
            java.awt.Desktop.getDesktop().browse(archivo.toURI());
        } catch (Exception e) {
            System.out.println("No se pudo abrir el archivo");
        }
    }

    public static void reporteCompras(ListaSimple compras) {
        String nombre = generarNombreArchivo("reporte_compras");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {

            bw.write("<html><head><title>Reporte de Compras</title></head><body>");
            bw.write("<h1>Reporte de Compras</h1>");
            bw.write("<table border='1'>");
            bw.write("<tr><th>#</th><th>Detalle</th></tr>");

            for (int i = 0; i < compras.size(); i++) {
                Compra compra = (Compra) compras.obtener(i);
                bw.write("<tr><td>" + (i + 1) + "</td><td>" + compra.toString() + "</td></tr>");
            }

            bw.write("</table></body></html>");

        } catch (Exception e) {
            System.out.println("Error generando reporte de compras");
        }

        abrirArchivo(nombre);
    }

    public static void reporteAlbum(ListaSimple cartas) {
        String nombre = generarNombreArchivo("reporte_album");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {

            bw.write("<html><head><title>Reporte de Álbum</title></head><body>");
            bw.write("<h1>Cartas del Álbum</h1>");
            bw.write("<table border='1'>");
            bw.write("<tr><th>#</th><th>Nombre</th><th>Tipo</th><th>Rareza</th></tr>");

            for (int i = 0; i < cartas.size(); i++) {
                Carta carta = (Carta) cartas.obtener(i);
                bw.write("<tr><td>" + (i + 1) + "</td><td>" + carta.getNombre() +
                        "</td><td>" + carta.getTipo() +
                        "</td><td>" + carta.getRareza() + "</td></tr>");
            }

            bw.write("</table></body></html>");

        } catch (Exception e) {
            System.out.println("Error generando reporte de álbum");
        }

        abrirArchivo(nombre);
    }

    public static void reporteTickets(ListaSimple tickets) {
        String nombre = generarNombreArchivo("reporte_tickets");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {

            bw.write("<html><head><title>Reporte de Tickets</title></head><body>");
            bw.write("<h1>Tickets Vendidos</h1>");
            bw.write("<table border='1'>");
            bw.write("<tr><th>#</th><th>Detalle</th></tr>");

            for (int i = 0; i < tickets.size(); i++) {
                TicketVendido ticket = (TicketVendido) tickets.obtener(i);
                bw.write("<tr><td>" + (i + 1) + "</td><td>" + ticket.toString() + "</td></tr>");
            }

            bw.write("</table></body></html>");

        } catch (Exception e) {
            System.out.println("Error generando reporte de tickets");
        }

        abrirArchivo(nombre);
    }

    public static void reporteLeaderboard(ListaSimple usuarios) {
        String nombre = generarNombreArchivo("reporte_leaderboard");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {

            bw.write("<html><head><title>Leaderboard</title></head><body>");
            bw.write("<h1>Ranking de Usuarios</h1>");
            bw.write("<table border='1'>");
            bw.write("<tr><th>#</th><th>Usuario</th><th>Nivel</th><th>XP</th></tr>");

            for (int i = 0; i < usuarios.size(); i++) {
                UsuarioSistema u = (UsuarioSistema) usuarios.obtener(i);
                bw.write("<tr><td>" + (i + 1) + "</td><td>" + u.getNombre() +
                        "</td><td>" + u.getNivel() +
                        "</td><td>" + u.getXp() + "</td></tr>");
            }

            bw.write("</table></body></html>");

        } catch (Exception e) {
            System.out.println("Error generando leaderboard");
        }

        abrirArchivo(nombre);
    }
}
