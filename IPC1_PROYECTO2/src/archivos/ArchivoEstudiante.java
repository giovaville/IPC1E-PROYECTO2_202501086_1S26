/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import modelo.Estudiante;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Gio
 */
public class ArchivoEstudiante {
    private String rutaArchivo;

    public ArchivoEstudiante(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarEstudiante(Estudiante estudiante) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            if (estudiante != null) {
                bw.write(estudiante.getNombreCompleto());
                bw.newLine();
                bw.write(estudiante.getCarnet());
                bw.newLine();
                bw.write(estudiante.getCorreo());
                bw.newLine();
                bw.write(estudiante.getSeccion());
                bw.newLine();
                bw.write(estudiante.getSemestre());
                bw.newLine();
                bw.write(String.valueOf(estudiante.getAnio()));
                bw.newLine();
                bw.write(estudiante.getDescripcionProyecto());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
    }

    public Estudiante cargarEstudiante() {
        Estudiante estudiante = new Estudiante();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {

            estudiante.setNombreCompleto(br.readLine());
            estudiante.setCarnet(br.readLine());
            estudiante.setCorreo(br.readLine());
            estudiante.setSeccion(br.readLine());
            estudiante.setSemestre(br.readLine());

            String anioTexto = br.readLine();
            if (anioTexto != null) {
                estudiante.setAnio(Integer.parseInt(anioTexto));
            }

            estudiante.setDescripcionProyecto(br.readLine());

        } catch (IOException e) {
            System.out.println("Error al cargar estudiante: " + e.getMessage());
        }

        return estudiante;
    }
}
