/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import Estructuras.ListaSimple;
import modelo.UsuarioSistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Gio
 */
public class ArchivoLeaderboard {
    private String rutaArchivo;

    public ArchivoLeaderboard(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarLeaderboard(ListaSimple usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            for (int i = 0; i < usuarios.size(); i++) {
                UsuarioSistema usuario = (UsuarioSistema) usuarios.obtener(i);

                if (usuario != null) {
                    bw.write(
                            usuario.getNombre() + "|" +
                            usuario.getXp()
                    );
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al guardar leaderboard: " + e.getMessage());
        }
    }

    public ListaSimple cargarLeaderboard() {
        ListaSimple usuarios = new ListaSimple();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    UsuarioSistema usuario = convertirLineaAUsuario(linea);

                    if (usuario != null) {
                        usuarios.agregarAlFinal(usuario);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar leaderboard: " + e.getMessage());
        }

        return usuarios;
    }

    private UsuarioSistema convertirLineaAUsuario(String linea) {
        String[] partes = linea.split("\\|");

        if (partes.length != 2) {
            return null;
        }

        try {
            String nombre = partes[0].trim();
            int xp = Integer.parseInt(partes[1].trim());

            UsuarioSistema usuario = new UsuarioSistema(nombre);
            usuario.setXp(xp);

            return usuario;

        } catch (Exception e) {
            return null;
        }
    }
}