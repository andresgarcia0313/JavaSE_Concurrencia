/*
 * Este archivo define la clase Cajero que extiende la clase Thread.
 * La clase Cajero simula a un cajero en un supermercado que procesa
 * los productos de un cliente en un hilo separado.
 */
package com.company.concurrencia;

// Importa clases necesarias para trabajar con tiempos y temporizadores
import java.time.LocalTime;// Clase que representa una hora en un día permitiendo la funcionalidad de obtener la hora actual
import java.time.temporal.ChronoUnit;// Enumeración que representa una unidad de tiempo permitiendo la funcionalidad de obtener la diferencia entre dos tiempos

/**
 * La clase Cajero representa un cajero en un supermercado. Extiende la clase
 * Thread para permitir que cada instancia de Cajero ejecute su propio hilo de
 * ejecución.
 */
public class Cajero extends Thread {

  // Nombre del cajero (ej. "Jaz")
  private final String nombre;

  // Cliente asociado a este cajero
  private final Cliente cliente;

  /**
   * Constructor de la clase Cajero.
   *
   * @param nombre  El nombre del cajero.
   * @param cliente El cliente que el cajero va a atender.
   */
  public Cajero(String nombre, Cliente cliente) {
    this.nombre = nombre;
    this.cliente = cliente;
  }

  /**
   * Método que se ejecuta cuando se inicia el hilo. Llama al método
   * procesarCompra para simular el proceso de compra.
   */
  @Override
  public void run() {
    procesarCompra();
  }

  /**
   * Simula el proceso de compra del cliente por parte del cajero. Procesa
   * cada producto del carrito del cliente y muestra el tiempo que toma
   * procesar cada uno.
   */
  private void procesarCompra() {
    // Registra la hora en la que comienza a procesar la compra
    LocalTime horaInicio = LocalTime.now();
    System.out.println("El cajero " + this.nombre + " comienza a procesar el carrito del cliente " + cliente.getNombre()
        + " a las " + horaInicio);

    // Itera sobre cada producto del cliente y lo procesa
    for (int i = 0; i < cliente.getProductos().length; i++) {
      esperarProceso();// Simula el tiempo que toma procesar un producto
      System.out.println("Se ha procesado el producto: " + (i + 1)
          + " del cliente "
          + cliente.getNombre());
    }

    // Muestra el tiempo total que se tardó en procesar todos los productos
    System.out.println("El cajero " + this.nombre
        + " ha finalizado de procesar el carrito de compras de "
        + cliente.getNombre() + " en "
        + horaInicio.until(LocalTime.now(), ChronoUnit.SECONDS)
        + " segundos");
  }

  /**
   * Simula una pausa de 1 segundo en el proceso de compra. Esto representa el
   * tiempo que toma procesar cada producto.
   */
  private void esperarProceso() {
    try {
      // Hace que el hilo duerma por 1000 milisegundos (1 segundo)
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // Muestra un mensaje si ocurre una interrupción durante el sueño del hilo
      System.out.println("Error al esperar: " + e);
    }
  }
}
