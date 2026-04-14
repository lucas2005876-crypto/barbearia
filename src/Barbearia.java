import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Barbearia {

    // 17 esperando (13 em pé + 4 no sofá)
    private final Semaphore semaforoTotal = new Semaphore(17, true);
    private final Semaphore semaforoSofa = new Semaphore(4, true);
    private final Semaphore semaforoPagamento = new Semaphore(1, true);

    private final BlockingQueue<Cliente> filaEmPe = new ArrayBlockingQueue<>(13);
    private final BlockingQueue<Cliente> filaSofa = new ArrayBlockingQueue<>(4);

    public static volatile boolean isOpen = true;
    public static int clientesAtendidos = 0;

    public boolean temClienteNoSofa() {
        return !filaSofa.isEmpty();
    }

    public void entrar(Cliente cliente) throws InterruptedException {
        if (!isOpen) return;

        semaforoTotal.acquire();

        if (!isOpen) {
            semaforoTotal.release();
            return;
        }

        System.out.println(cliente + " entrou na barbearia");

        // tenta sentar direto no sofá
        if (semaforoSofa.tryAcquire()) {
            System.out.println(cliente.getNameCliente() + " sentou no sofá.");
            filaSofa.put(cliente);
        } else {
            System.out.println(cliente.getNameCliente() + " está em pé aguardando.");
            filaEmPe.put(cliente);
        }
    }

    public Cliente chamarProximoDoSofa() throws InterruptedException {

        Cliente proximo = filaSofa.take();
        System.out.println(proximo.getNameCliente() + " foi chamado para cortar o cabelo");

        semaforoSofa.release(); // liberou vaga no sofá

        // promove alguém da fila em pé (FIFO seguro)
        Cliente clienteEmPe = filaEmPe.poll();

        if (clienteEmPe != null) {
            semaforoSofa.acquire();
            filaSofa.put(clienteEmPe);
            System.out.println(clienteEmPe.getNameCliente() + " saiu da fila e sentou no sofá.");
        }

        return proximo;
    }

    public void realizarPagamento(Cliente cliente, Barbeiro barbeiro) throws InterruptedException {

        semaforoPagamento.acquire();

        System.out.println(barbeiro.getNameBarbeiro() + " está recebendo de " + cliente.getNameCliente() + ".");
        Thread.sleep(500);
        System.out.println(cliente.getNameCliente() + " pagou e saiu.");

        semaforoPagamento.release();
        semaforoTotal.release();

        clientesAtendidos++;
        System.out.println("Clientes atendidos: " + clientesAtendidos);

        if (clientesAtendidos >= 25) {
            isOpen = false;
        }
    }
}