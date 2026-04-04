import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        Barbearia barbearia = new Barbearia();

        int idCLiente = 1;

        ArrayList<Cliente> listaClientes = new ArrayList<>();

        Barbeiro barbeiro1 = new Barbeiro("Joao", barbearia);
        Barbeiro barbeiro2 = new Barbeiro("Vinícius", barbearia);
        Barbeiro barbeiro3 = new Barbeiro("Lucas", barbearia);

        Barbeiro barbeiros[] = new Barbeiro[3];

        barbeiros[0] = barbeiro1;
        barbeiros[1] = barbeiro2;
        barbeiros[2] = barbeiro3;

        String[] nomes = {
                "Ana", "Bruno", "Carlos", "Diana", "Eduardo",
                "Fernanda", "Gabriel", "Helena", "Igor", "Julia",
                "Kleber", "Larissa", "Marcos", "Natália", "Otávio",
                "Paula", "Rafael", "Sofia", "Thiago", "Vanessa",
                "William", "Yasmin", "Zé", "Aline", "Brenda",
                "Caio", "Daniel", "Elisa", "Felipe", "Giovana",
                "Hugo", "Isabela", "João", "Karen", "Lucas",
                "Marta", "Nicolas", "Olivia", "Pedro", "Quezia",
                "Rita", "Samuel", "Tatiane", "Ulisses", "Vitor",
                "Wesley", "Xavier", "Yuri", "Zilda", "Arthur"
        };


        while(idCLiente < 50){
            int tamanhoCabelo = (int) (Math.random() * 10);
            int numeroNome = (int) (Math.random() * 50);
            Cliente cliente = new Cliente( idCLiente, nomes[numeroNome], tamanhoCabelo, barbearia);
            listaClientes.add(cliente);

            idCLiente++;
        }

        for (Barbeiro barbeiro : barbeiros) {
            barbeiro.start();
        }
        listaClientes.forEach(Thread::start);

        try {
            for (Barbeiro barbeiro : barbeiros) {
                barbeiro.join();
            }

            for (Cliente listaCliente : listaClientes) {
                listaCliente.join();
            }

        } catch (InterruptedException i) {
            System.out.println("Interrupted thread " + i.getMessage());
        }

        System.out.println("Barbearia encerrada com: " + barbearia.clientesAtendidos + " clientes atendidos!");

    }
}
