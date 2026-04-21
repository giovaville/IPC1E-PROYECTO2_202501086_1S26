/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Gio
 */
public class Estudiante {
    private String nombreCompleto;
    private String carnet;
    private String correo;
    private String seccion;
    private String semestre;
    private int anio;
    private String descripcionProyecto;

    public Estudiante() {
        this.nombreCompleto = "";
        this.carnet = "";
        this.correo = "";
        this.seccion = "";
        this.semestre = "";
        this.anio = 0;
        this.descripcionProyecto = "";
    }

    public Estudiante(String nombreCompleto, String carnet, String correo,
                      String seccion, String semestre, int anio,
                      String descripcionProyecto) {
        this.nombreCompleto = nombreCompleto;
        this.carnet = carnet;
        this.correo = correo;
        this.seccion = seccion;
        this.semestre = semestre;
        this.anio = anio;
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    @Override
    public String toString() {
        return nombreCompleto + " - " + carnet + " - " + seccion;
    }
}
