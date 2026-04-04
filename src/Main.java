import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int idCLiente = 1;

        ArrayList<Cliente> listaClientes = new ArrayList<>();

        Cadeira cadeira1 = new Cadeira(1);
        Cadeira cadeira2= new Cadeira(2);
        Cadeira cadeira3 = new Cadeira(3);

        Barbeiro[] barbeiros = new Barbeiro[3];

        Barbeiro barbeiro1 = new Barbeiro("Joao", cadeira1);
        Barbeiro barbeiro2 = new Barbeiro("Cleber", cadeira2);
        Barbeiro barbeiro3 = new Barbeiro("Lucas", cadeira3);


        barbeiros[0] = barbeiro1;
        barbeiros[1] = barbeiro2;
        barbeiros[2] = barbeiro3;

        Barbearia barbearia = new Barbearia(barbeiros);

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


        while(idCLiente <= 50){
            int tamanhoCabelo = (int) (Math.random() * 10) + 1;
            int numeroNome = (int) (Math.random() * 50) - 1 ;
            Cliente cliente = new Cliente( idCLiente, nomes[numeroNome], tamanhoCabelo, barbearia);
            listaClientes.add(cliente);

            idCLiente++;
        }

        listaClientes.forEach(cliente -> {
            cliente.start();
        });

    }
}
