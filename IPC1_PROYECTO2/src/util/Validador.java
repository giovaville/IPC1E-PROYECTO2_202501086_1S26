/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Gio
 */
public class Validador {
    public static boolean textoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean esEntero(String texto) {
        if (textoVacio(texto)) {
            return false;
        }

        try {
            Integer.parseInt(texto.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esDouble(String texto) {
        if (textoVacio(texto)) {
            return false;
        }

        try {
            Double.parseDouble(texto.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esPositivo(int numero) {
        return numero > 0;
    }

    public static boolean esPositivo(double numero) {
        return numero > 0;
    }

    public static boolean longitudMinima(String texto, int minimo) {
        if (texto == null) {
            return false;
        }

        return texto.trim().length() >= minimo;
    }
}