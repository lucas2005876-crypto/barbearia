import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Barbearia {
    // Semáforos para controle de capacidade (O "Guarda" da barbearia)
    private final Semaphore semaforoTotal = new Semaphore(17, true);
    private final Semaphore semaforoSofa = new Semaphore(4, true);
    private final Semaphore semaforoPagamento = new Semaphore(1, true);

    // Filas que garantem o FIFO explícito e evitam Busy-Wait
    private final BlockingQueue<Cliente> filaEmPe = new ArrayBlockingQueue<>(13);
    private final BlockingQueue<Cliente> filaSofa = new ArrayBlockingQueue<>(4);

    public static volatile boolean isOpen = true;

    public static int clientesAtendidos = 0;

    // O cliente chama este método ao chegar
    public void entrar(Cliente cliente) throws InterruptedException {
        // Tenta adquirir vaga no recinto (max 20)
            semaforoTotal.acquire();
            filaEmPe.put(cliente); // put() bloqueia se estiver cheio, mas aqui o semáforo garante vaga
            System.out.println(cliente.getNameCliente() + " entrou na barbearia");
            // Tenta ir para o sofá
            promoverParaSofa(cliente);
    }

    private void promoverParaSofa(Cliente cliente) throws InterruptedException {
        semaforoSofa.acquire();
        filaEmPe.remove(cliente);
        filaSofa.put(cliente);
        System.out.println(cliente.getNameCliente() + " sentou no sofá.");
    }

    // O Barbeiro chama este método para pegar o próximo do sofá
    public Cliente chamarProximoDoSofa() throws InterruptedException {
        Cliente proximo = filaSofa.take(); // Bloqueia o barbeiro se o sofá estiver vazio
        System.out.println(proximo.getNameCliente() + " foi chamado para cortar o cabelo");
        semaforoSofa.release(); // Libera uma vaga no sofá para quem estava em pé
        return proximo;
    }

    public void realizarPagamento(Cliente cliente, Barbeiro barbeiro) throws InterruptedException {
        // Garante que apenas um barbeiro e seu respectivo cliente paguem por vez
        semaforoPagamento.acquire();
        System.out.println(barbeiro.getNameBarbeiro() + " está recebendo de " + cliente.getNameCliente() + ".");
        Thread.sleep(500); // O tempo que ambos gastam no caixa
        System.out.println(cliente.getNameCliente() + " pagou e saiu.");
        semaforoPagamento.release(); // Libera o caixa para o próximo par
        semaforoTotal.release();     // Libera a vaga na barbearia (o cliente saiu do prédio)
        clientesAtendidos++;
        System.out.println("Clientes atendidos: " + clientesAtendidos);
        if (clientesAtendidos >= 25) {
            isOpen = false;
        }
    }
}