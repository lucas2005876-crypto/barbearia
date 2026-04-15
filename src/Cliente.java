public class Cliente extends Thread {

    private int id;
    private String nome;
    private int tamanhoCabelo;
    private Barbearia barbearia;

    public Cliente(int id, String nome, int tamanho, Barbearia b) {
        this.id = id;
        this.nome = nome;
        this.tamanhoCabelo = tamanho;
        this.barbearia = b;
    }

    public String getNameCliente() {
        return nome;
    }

    public int getTamanhoDeCabelo() {
        return tamanhoCabelo;
    }

    @Override
    public String toString() {
        return "Cliente: " + id + " - " + nome;
    }

    @Override
    public void run() {
        try {
            barbearia.entrar(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}