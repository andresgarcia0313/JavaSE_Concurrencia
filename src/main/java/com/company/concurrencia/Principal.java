package com.company.concurrencia;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
// Importa las clases necesarias para trabajar con un pool de hilos
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * La clase Principal contiene el método main que inicia el programa.
 * Se encarga de crear y gestionar el pool de hilos para procesar
 * las compras de los clientes utilizando la clase Cajero.
 */
public class Principal {

  /**
   * Método principal que se ejecuta al iniciar el programa.
   * Crea un pool de hilos, clientes, cajeros y envía los cajeros
   * al pool para su ejecución concurrente.
   *
   * @param args Argumentos de la línea de comandos (no utilizados en este
   *             ejemplo).
   */
  // Estructura concurrente para almacenar los resultados
  private static ConcurrentHashMap<Integer, String> resultados = new ConcurrentHashMap<>();

  // Lista de datos para procesar, usando CopyOnWriteArrayList para soportar
  // concurrencia
  private static CopyOnWriteArrayList<String> listaDatos = new CopyOnWriteArrayList<>(
      Arrays.asList("Dato1", "Dato2", "Dato3", "Dato4"));

  public static void main(String[] args) {
    // Crear un pool de hilos con un tamaño fijo de 2 hilos
    ExecutorService pool = Executors.newFixedThreadPool(2);

    // Crear dos clientes con diferentes productos
    Cliente cliente1 = new Cliente("Carolina", new String[] { "Producto1", "Producto2", "Producto3", "Producto4" });
    Cliente cliente2 = new Cliente("Eduardo", new String[] { "Producto2", "Producto3" });

    // Crear dos cajeros para atender a los clientes
    Cajero cajero1 = new Cajero("Jaz", cliente1);
    Cajero cajero2 = new Cajero("Luz", cliente2);

    // Enviar los cajeros al pool de hilos para que empiecen a procesar las compras
    pool.submit(cajero1);
    pool.submit(cajero2);

    // Espera a que todos los hilos terminen su ejecución antes de cerrar el pool
    pool.shutdown(); // Después de llamar a shutdown, no se pueden agregar más tareas al pool

    // Número de hilos/trabajadores
    int numTrabajadores = 4;

    // CyclicBarrier para sincronizar los hilos
    CyclicBarrier barrera = new CyclicBarrier(numTrabajadores, () -> {
      System.out.println("Todos los trabajadores han finalizado su tarea. Procesando resultados...");
      // Mostrar los resultados una vez que todos han terminado
      resultados.forEach((key, value) -> System.out.println("ID: " + key + " - Resultado: " + value));
    });

    // Creación de un pool de threads
    ExecutorService executor = Executors.newFixedThreadPool(numTrabajadores);

    for (int i = 0; i < numTrabajadores; i++) {
      final int id = i; // ID del trabajador

      executor.submit(() -> {
        try {
          // Simular el procesamiento de los datos
          String datoProcesado = procesarDato(id);
          // Guardar el resultado en la estructura concurrente
          resultados.put(id, datoProcesado);

          // Esperar a que todos los hilos terminen su trabajo
          barrera.await();
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }

    executor.shutdown(); // Finalizar el pool de hilos
  }

  private static String procesarDato(int id) throws InterruptedException {
    // Simulación de procesamiento
    Thread.sleep(1000);
    return "Resultado del trabajador " + id + ": " + listaDatos.get(id);
  }
}
