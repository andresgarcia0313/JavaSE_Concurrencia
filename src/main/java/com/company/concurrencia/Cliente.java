package com.company.concurrencia;

/**
 * La clase Cliente representa a un cliente en un supermercado. Contiene
 * información sobre el nombre del cliente y los productos que tiene en su
 * carrito de compras.
 */
public class Cliente {

    /**
     * El nombre del cliente.
     * @type {String}
     */
    private String nombre;

    // Array de productos que el cliente tiene en su carrito
    private String[] productos;

    /**
     * Constructor de la clase Cliente.
     *
     * @param nombre El nombre del cliente.
     * @param productos Un array de cadenas que representa los productos en el
     * carrito del cliente.
     */
    public Cliente(String nombre, String[] productos) {
        this.nombre = nombre; // Inicializa el nombre del cliente
        this.productos = productos; // Inicializa el array de productos
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre; // Devuelve el nombre del cliente
    }

    /**
     * Obtiene los productos del cliente.
     *
     * @return Un array de cadenas que representa los productos del cliente.
     */
    public String[] getProductos() {
        return productos; // Devuelve el array de productos
    }
}
