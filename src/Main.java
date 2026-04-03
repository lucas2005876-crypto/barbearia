public class Main {
    public static void main(String[] args) {

        Barbeiro barbeiro1 = new Barbeiro("Joao");
        Barbeiro barbeiro2 = new Barbeiro("Cleber");
        Barbeiro barbeiro3 = new Barbeiro("Lucas");

        Barbeiro barbeiros[] = new Barbeiro[3];

        barbeiros[0] = barbeiro1;
        barbeiros[1] = barbeiro2;
        barbeiros[2] = barbeiro3;

        Barbearia barbearia = new Barbearia(barbeiros);




    }
}
