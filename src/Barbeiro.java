public class Barbeiro extends Thread {

    private String nome;
    private Barbearia barbearia;

    public Barbeiro(String nome, Barbearia b) {
        this.nome = nome;
        this.barbearia = b;
    }

    public String getNome() {
        return nome;
    }

    public void cortar(Cliente c) throws InterruptedException {
        int t = c.getTamanhoDeCabelo();
        int tempo;

        if (t < 5) {
            tempo = 600;
        } else if (t < 8) {
            tempo = 1200;
        } else {
            tempo = 2000;
        }
        Barbearia.log("Barbeiro " + nome + " está cortando o cabelo de " + c.getNameCliente());        Thread.sleep(tempo);
        Barbearia.log(nome + " terminou o corte de " + c.getNameCliente());
    }

    @Override
    public void run() {
        try {
            while (Barbearia.isOpen || barbearia.aindaTemClientes()) {

                Cliente c = barbearia.chamarProximo();

                if (c == null) continue;

                cortar(c);
                barbearia.pagar(c, this);

                Thread.sleep(200); // descanso
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}