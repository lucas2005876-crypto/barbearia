import java.util.*;
import java.util.concurrent.*;

public class Barbearia {

    private final Semaphore capacidade = new Semaphore(20, true);
    private final Semaphore sofa = new Semaphore(4, true);
    private final Semaphore pagamento = new Semaphore(1, true);

    private final BlockingQueue<Cliente> filaEmPe = new ArrayBlockingQueue<>(13);
    private final BlockingQueue<Cliente> filaSofa = new ArrayBlockingQueue<>(4);

    private final Queue<Cliente> filaEntrada = new LinkedList<>();

    public static volatile boolean isOpen = true;
    public static int clientesAtendidos = 0;

    public static synchronized void log(String msg) {
        System.out.println(msg);
    }

    // 🔥 ENTRADA COM FILA EXTERNA (FIFO GLOBAL)
    public synchronized void entrar(Cliente cliente) throws InterruptedException {

        filaEntrada.add(cliente);

        while (filaEntrada.peek() != cliente) {
            wait();
        }

        if (!isOpen) {
            filaEntrada.poll();
            notifyAll();
            return;
        }

        while (!capacidade.tryAcquire()) {
            log(cliente + " está aguardando do lado de fora...");
            wait();
        }

        log(cliente + " entrou na barbearia");

        if (sofa.tryAcquire()) {
            log(cliente.getNameCliente() + " sentou no sofá");
            filaSofa.put(cliente);
        } else {
            log(cliente.getNameCliente() + " ficou em pé");
            filaEmPe.put(cliente);
        }

        filaEntrada.poll(); // remove da fila de entrada
        notifyAll(); // libera próximo cliente da fila externa
    }

    public Cliente chamarProximo() throws InterruptedException {

        Cliente cliente = filaSofa.poll(1, TimeUnit.SECONDS);
        if (cliente == null) return null;

        log(cliente.getNameCliente() + " foi chamado para cortar o cabelo");

        sofa.release();

        Cliente emPe = filaEmPe.poll();
        if (emPe != null) {
            sofa.acquire();
            log(emPe.getNameCliente() + " saiu da fila e sentou no sofá");
            filaSofa.put(emPe);
        }

        return cliente;
    }

    public void pagar(Cliente cliente, Barbeiro barbeiro) throws InterruptedException {

        pagamento.acquire();

        log(barbeiro.getNome() + " recebendo de " + cliente.getNameCliente());
        Thread.sleep(500);
        log(cliente.getNameCliente() + " pagou e saiu");

        pagamento.release();
        capacidade.release();

        synchronized (this) {
            notifyAll();
        }

        clientesAtendidos++;
        log("Clientes atendidos: " + clientesAtendidos);

        if (clientesAtendidos >= 25) {
            isOpen = false;
        }
    }

    public boolean aindaTemClientes() {
        return !filaSofa.isEmpty() || !filaEmPe.isEmpty() || !filaEntrada.isEmpty();
    }
}