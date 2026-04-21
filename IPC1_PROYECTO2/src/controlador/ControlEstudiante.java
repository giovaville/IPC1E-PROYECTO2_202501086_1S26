/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.Estudiante;
/**
 *
 * @author Gio
 */
public class ControlEstudiante {
    private Estudiante estudiante;

    public ControlEstudiante() {
        this.estudiante = new Estudiante();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void cargarDatosEstudiante(String nombreCompleto, String carnet, String correo,
                                      String seccion, String semestre, int anio,
                                      String descripcionProyecto) {
        estudiante.setNombreCompleto(nombreCompleto);
        estudiante.setCarnet(carnet);
        estudiante.setCorreo(correo);
        estudiante.setSeccion(seccion);
        estudiante.setSemestre(semestre);
        estudiante.setAnio(anio);
        estudiante.setDescripcionProyecto(descripcionProyecto);
    }

    public boolean hayDatosEstudiante() {
        return estudiante != null
                && estudiante.getNombreCompleto() != null
                && !estudiante.getNombreCompleto().trim().isEmpty();
    }

    public void limpiarDatos() {
        this.estudiante = new Estudiante();
    }
}
