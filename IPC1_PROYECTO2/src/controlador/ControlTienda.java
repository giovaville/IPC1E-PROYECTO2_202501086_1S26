/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import Estructuras.ListaSimple;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.ItemCarrito;
import modelo.UsuarioSistema;
import modelo.juego;
import util.FechaUtil;
/**
 *
 * @author Gio
 */
public class ControlTienda {
    private ListaSimple catalogo;
    private ListaSimple carrito;
    private ListaSimple historial;

    public ControlTienda() {
        this.catalogo = new ListaSimple();
        this.carrito = new ListaSimple();
        this.historial = new ListaSimple();
    }

    public ListaSimple getCatalogo() {
        return catalogo;
    }

    public ListaSimple getCarrito() {
        return carrito;
    }

    public ListaSimple getHistorial() {
        return historial;
    }

    public void agregarJuegoAlCatalogo(juego juego) {
        if (juego != null) {
            catalogo.agregarAlFinal(juego);
        }
    }

    public juego buscarJuegoPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < catalogo.size(); i++) {
            juego actual = (juego) catalogo.obtener(i);

            if (actual != null && actual.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return actual;
            }
        }

        return null;
    }

    public juego buscarJuegoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < catalogo.size(); i++) {
            juego actual = (juego) catalogo.obtener(i);

            if (actual != null && actual.getNombre().equalsIgnoreCase(nombre.trim())) {
                return actual;
            }
        }

        return null;
    }

    public ListaSimple filtrarPorGenero(String genero) {
        ListaSimple filtrados = new ListaSimple();

        if (genero == null || genero.trim().isEmpty()) {
            return filtrados;
        }

        for (int i = 0; i < catalogo.size(); i++) {
            juego actual = (juego) catalogo.obtener(i);

            if (actual != null && actual.getGenero().name().equalsIgnoreCase(genero.trim())) {
                filtrados.agregarAlFinal(actual);
            }
        }

        return filtrados;
    }

    public ListaSimple filtrarPorPlataforma(String plataforma) {
        ListaSimple filtrados = new ListaSimple();

        if (plataforma == null || plataforma.trim().isEmpty()) {
            return filtrados;
        }

        for (int i = 0; i < catalogo.size(); i++) {
            juego actual = (juego) catalogo.obtener(i);

            if (actual != null && actual.getPlataforma().name().equalsIgnoreCase(plataforma.trim())) {
                filtrados.agregarAlFinal(actual);
            }
        }

        return filtrados;
    }

    public boolean agregarAlCarrito(juego juego, int cantidad) {
        if (juego == null || cantidad <= 0) {
            return false;
        }

        if (!juego.tieneStockSuficiente(cantidad)) {
            return false;
        }

        ItemCarrito itemExistente = buscarItemEnCarrito(juego.getCodigo());

        if (itemExistente != null) {
            int nuevaCantidad = itemExistente.getCantidad() + cantidad;

            if (!juego.tieneStockSuficiente(nuevaCantidad)) {
                return false;
            }

            itemExistente.setCantidad(nuevaCantidad);
        } else {
            carrito.agregarAlFinal(new ItemCarrito(juego, cantidad));
        }

        return true;
    }

    public ItemCarrito buscarItemEnCarrito(String codigoJuego) {
        if (codigoJuego == null || codigoJuego.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < carrito.size(); i++) {
            ItemCarrito item = (ItemCarrito) carrito.obtener(i);

            if (item != null && item.getJuego() != null &&
                item.getJuego().getCodigo().equalsIgnoreCase(codigoJuego.trim())) {
                return item;
            }
        }

        return null;
    }

    public boolean actualizarCantidadCarrito(String codigoJuego, int nuevaCantidad) {
        ItemCarrito item = buscarItemEnCarrito(codigoJuego);

        if (item == null || item.getJuego() == null || nuevaCantidad <= 0) {
            return false;
        }

        if (!item.getJuego().tieneStockSuficiente(nuevaCantidad)) {
            return false;
        }

        item.setCantidad(nuevaCantidad);
        return true;
    }

    public boolean eliminarDelCarrito(int indice) {
        return carrito.eliminar(indice);
    }

    public double calcularTotalCarrito() {
        double total = 0.0;

        for (int i = 0; i < carrito.size(); i++) {
            ItemCarrito item = (ItemCarrito) carrito.obtener(i);

            if (item != null) {
                total += item.getSubtotal();
            }
        }

        return total;
    }

    public boolean carritoVacio() {
        return carrito.estaVacia();
    }

    public Compra confirmarCompra(UsuarioSistema usuario) {
        if (usuario == null || carrito.estaVacia()) {
            return null;
        }

        for (int i = 0; i < carrito.size(); i++) {
            ItemCarrito item = (ItemCarrito) carrito.obtener(i);

            if (item == null || item.getJuego() == null) {
                return null;
            }

            if (!item.getJuego().tieneStockSuficiente(item.getCantidad())) {
                return null;
            }
        }

        Compra nuevaCompra = new Compra(generarIdCompra(), FechaUtil.obtenerFechaHoraTexto());

        for (int i = 0; i < carrito.size(); i++) {
            ItemCarrito item = (ItemCarrito) carrito.obtener(i);

            item.getJuego().reducirStock(item.getCantidad());

            DetalleCompra detalle = new DetalleCompra(item.getJuego(), item.getCantidad());
            nuevaCompra.agregarDetalle(detalle);
        }

        historial.agregarAlInicio(nuevaCompra);
        usuario.agregarCompra(nuevaCompra);
        usuario.agregarXp(contarJuegosComprados(nuevaCompra) * 50);

        carrito.limpiar();

        return nuevaCompra;
    }

    private int contarJuegosComprados(Compra compra) {
        int totalJuegos = 0;

        if (compra == null || compra.getDetalles() == null) {
            return 0;
        }

        for (int i = 0; i < compra.getDetalles().size(); i++) {
            DetalleCompra detalle = (DetalleCompra) compra.getDetalles().obtener(i);

            if (detalle != null) {
                totalJuegos += detalle.getCantidad();
            }
        }

        return totalJuegos;
    }

    private String generarIdCompra() {
        return "COMPRA-" + System.currentTimeMillis();
    }

    public void limpiarCarrito() {
        carrito.limpiar();
    }
}