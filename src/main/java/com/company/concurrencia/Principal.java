package com.company.concurrencia;

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
     * @param args Argumentos de la línea de comandos (no utilizados en este ejemplo).
     */
    public static void main(String[] args) {
        // Crear un pool de hilos con un tamaño fijo de 2 hilos
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Crear dos clientes con diferentes productos
        Cliente cliente1 = new Cliente("Carolina", new String[]{"Producto1", "Producto2", "Producto3", "Producto4"});
        Cliente cliente2 = new Cliente("Eduardo", new String[]{"Producto2", "Producto3"});

        // Crear dos cajeros para atender a los clientes
        Cajero cajero1 = new Cajero("Jaz", cliente1);
        Cajero cajero2 = new Cajero("Luz", cliente2);

        // Enviar los cajeros al pool de hilos para que empiecen a procesar las compras
        pool.submit(cajero1);
        pool.submit(cajero2);

        // Espera a que todos los hilos terminen su ejecución antes de cerrar el pool
        pool.shutdown(); // Después de llamar a shutdown, no se pueden agregar más tareas al pool
    }
}
