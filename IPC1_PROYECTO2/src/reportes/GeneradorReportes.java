/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;
import Estructuras.ListaSimple;
import modelo.Carta;
import modelo.Compra;
import modelo.TicketVendido;
import modelo.UsuarioSistema;
import modelo.juego;

import java.awt.Desktop;
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
    private static String generarNombreArchivo(String tipo) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        return LocalDateTime.now().format(formato) + "_" + tipo + ".html";
    }

    private static void abrirArchivo(String ruta) {
        try {
            File archivo = new File(ruta);
            Desktop.getDesktop().browse(archivo.toURI());
        } catch (Exception e) {
            System.out.println("No se pudo abrir el reporte: " + e.getMessage());
        }
    }

    private static String encabezadoHTML(String titulo) {
        return "<html><head><meta charset='UTF-8'><title>" + titulo + "</title>"
                + "<style>"
                + "body{font-family:Arial;background:#f4f4f4;padding:20px;}"
                + "h1{text-align:center;color:#222;}"
                + "table{width:100%;border-collapse:collapse;background:white;}"
                + "th{background:#222;color:white;padding:10px;}"
                + "td{border:1px solid #ccc;padding:8px;text-align:center;}"
                + ".vacia{background:#d9d9d9;}"
                + ".legendaria{background:#ffd700;}"
                + "</style></head><body><h1>" + titulo + "</h1>";
    }

    private static String cierreHTML() {
        return "</body></html>";
    }

    public static void reporteInventario(ListaSimple catalogo) {
        String nombre = generarNombreArchivo("Inventario");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
            bw.write(encabezadoHTML("Reporte de Inventario de Tienda"));
            bw.write("<table>");
            bw.write("<tr><th>No.</th><th>Código</th><th>Nombre</th><th>Género</th><th>Plataforma</th><th>Precio</th><th>Stock</th><th>Descripción</th></tr>");

            for (int i = 0; i < catalogo.size(); i++) {
                juego j = (juego) catalogo.obtener(i);

                if (j != null) {
                    bw.write("<tr>");
                    bw.write("<td>" + (i + 1) + "</td>");
                    bw.write("<td>" + j.getCodigo() + "</td>");
                    bw.write("<td>" + j.getNombre() + "</td>");
                    bw.write("<td>" + j.getGenero() + "</td>");
                    bw.write("<td>" + j.getPlataforma() + "</td>");
                    bw.write("<td>Q" + j.getPrecio() + "</td>");
                    bw.write("<td>" + j.getStock() + "</td>");
                    bw.write("<td>" + j.getDescripcion() + "</td>");
                    bw.write("</tr>");
                }
            }

            bw.write("</table>");
            bw.write(cierreHTML());

        } catch (Exception e) {
            System.out.println("Error generando reporte de inventario: " + e.getMessage());
        }

        abrirArchivo(nombre);
    }

    public static void reporteCompras(ListaSimple compras) {
        String nombre = generarNombreArchivo("Ventas");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
            bw.write(encabezadoHTML("Reporte de Ventas"));
            bw.write("<table>");
            bw.write("<tr><th>No.</th><th>ID Compra</th><th>Fecha</th><th>Total</th></tr>");

            for (int i = 0; i < compras.size(); i++) {
                Compra compra = (Compra) compras.obtener(i);

                if (compra != null) {
                    bw.write("<tr>");
                    bw.write("<td>" + (i + 1) + "</td>");
                    bw.write("<td>" + compra.getIdCompra() + "</td>");
                    bw.write("<td>" + compra.getFecha() + "</td>");
                    bw.write("<td>Q" + compra.getTotal() + "</td>");
                    bw.write("</tr>");
                }
            }

            bw.write("</table>");
            bw.write(cierreHTML());

        } catch (Exception e) {
            System.out.println("Error generando reporte de ventas: " + e.getMessage());
        }

        abrirArchivo(nombre);
    }

    public static void reporteAlbum(ListaSimple cartas) {
        String nombre = generarNombreArchivo("Album");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
            bw.write(encabezadoHTML("Reporte de Álbum"));
            bw.write("<table>");
            bw.write("<tr><th>No.</th><th>Código</th><th>Nombre</th><th>Tipo</th><th>Rareza</th><th>Ataque</th><th>Defensa</th><th>PS</th></tr>");

            for (int i = 0; i < cartas.size(); i++) {
                Carta carta = (Carta) cartas.obtener(i);

                if (carta != null) {
                    String clase = "";

                    if (carta.getRareza() != null && carta.getRareza().name().equals("LEGENDARIA")) {
                        clase = " class='legendaria'";
                    }

                    bw.write("<tr" + clase + ">");
                    bw.write("<td>" + (i + 1) + "</td>");
                    bw.write("<td>" + carta.getCodigo() + "</td>");
                    bw.write("<td>" + carta.getNombre() + "</td>");
                    bw.write("<td>" + carta.getTipo() + "</td>");
                    bw.write("<td>" + carta.getRareza() + "</td>");
                    bw.write("<td>" + carta.getAtaque() + "</td>");
                    bw.write("<td>" + carta.getDefensa() + "</td>");
                    bw.write("<td>" + carta.getPs() + "</td>");
                    bw.write("</tr>");
                }
            }

            bw.write("</table>");
            bw.write(cierreHTML());

        } catch (Exception e) {
            System.out.println("Error generando reporte de álbum: " + e.getMessage());
        }

        abrirArchivo(nombre);
    }

    public static void reporteTickets(ListaSimple tickets) {
        String nombre = generarNombreArchivo("Torneos");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
            bw.write(encabezadoHTML("Reporte de Torneos y Tickets"));
            bw.write("<table>");
            bw.write("<tr><th>No.</th><th>ID Ticket</th><th>Torneo</th><th>Comprador</th><th>Fecha Compra</th><th>Precio</th></tr>");

            for (int i = 0; i < tickets.size(); i++) {
                TicketVendido ticket = (TicketVendido) tickets.obtener(i);

                if (ticket != null) {
                    String torneo = "Sin torneo";

                    if (ticket.getTorneo() != null) {
                        torneo = ticket.getTorneo().getNombre();
                    }

                    bw.write("<tr>");
                    bw.write("<td>" + (i + 1) + "</td>");
                    bw.write("<td>" + ticket.getIdTicket() + "</td>");
                    bw.write("<td>" + torneo + "</td>");
                    bw.write("<td>" + ticket.getNombreComprador() + "</td>");
                    bw.write("<td>" + ticket.getFechaCompra() + "</td>");
                    bw.write("<td>Q" + ticket.getPrecio() + "</td>");
                    bw.write("</tr>");
                }
            }

            bw.write("</table>");
            bw.write(cierreHTML());

        } catch (Exception e) {
            System.out.println("Error generando reporte de torneos: " + e.getMessage());
        }

        abrirArchivo(nombre);
    }

    public static void reporteLeaderboard(ListaSimple usuarios) {
        String nombre = generarNombreArchivo("Leaderboard");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
            bw.write(encabezadoHTML("Reporte de Leaderboard"));
            bw.write("<table>");
            bw.write("<tr><th>Posición</th><th>Usuario</th><th>Nivel</th><th>Rango</th><th>XP</th></tr>");

            for (int i = 0; i < usuarios.size(); i++) {
                UsuarioSistema usuario = (UsuarioSistema) usuarios.obtener(i);

                if (usuario != null) {
                    bw.write("<tr>");
                    bw.write("<td>" + (i + 1) + "</td>");
                    bw.write("<td>" + usuario.getNombre() + "</td>");
                    bw.write("<td>" + usuario.getNivel() + "</td>");
                    bw.write("<td>" + usuario.getRango() + "</td>");
                    bw.write("<td>" + usuario.getXp() + "</td>");
                    bw.write("</tr>");
                }
            }

            bw.write("</table>");
            bw.write(cierreHTML());

        } catch (Exception e) {
            System.out.println("Error generando reporte de leaderboard: " + e.getMessage());
        }

        abrirArchivo(nombre);
    }
}