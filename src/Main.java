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
                "Bruno", "Carlos", "Daniel", "Eduardo",
                "Felipe", "Gabriel", "Henrique", "Igor", "João",
                "Kleber", "Lucas", "Marcos", "Nicolas", "Otávio",
                "Pedro", "Rafael", "Samuel", "Thiago", "Vitor",
                "William", "Yuri", "Zé", "Arthur", "Bruno",
                "Caio", "Daniel", "Enzo", "Felipe", "Gustavo",
                "Hugo", "Ian", "João", "Kai", "Leonardo",
                "Matheus", "Nathan", "Oliver", "Paulo", "Quentin",
                "Ricardo", "Stefan", "Thomas", "Ulisses", "Victor",
                "Wesley", "Xavier", "Yago", "Zeca", "André"
        };

        int numeroNome = 0;

        while(idCLiente < 50){
            int tamanhoCabelo = (int) (Math.random() * 10);
            Cliente cliente = new Cliente( idCLiente, nomes[numeroNome], tamanhoCabelo, barbearia);
            numeroNome++;

            listaClientes.add(cliente);

            idCLiente++;
        }

        for (Barbeiro barbeiro : barbeiros) {
            barbeiro.start();
        }

        for (Cliente c : listaClientes) {
            c.start();
            Thread.sleep((int)(Math.random() * 200)); // chegada gradual
        }
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
