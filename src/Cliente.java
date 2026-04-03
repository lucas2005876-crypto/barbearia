public class Cliente extends Thread{

    private String name;
    private int id;
    private int tamanhoDeCabelo;
    private Barbearia b;

    public Cliente (int id, String name, int tamanhoDeCabelo, Barbearia b) {
        this.id = id;
        this.name = name;
        this.tamanhoDeCabelo = tamanhoDeCabelo;
        this.b = b;
    }

    public String getNameCliente() {
        return name;
    }

    public int getIdCliente() {
        return id;
    }

    public int getTamanhoDeCabelo() {
        return tamanhoDeCabelo;
    }

    @Override
    public String toString() {
        return "Cliente: " + id + " - " + name;
    }

    public void run() {
        if (b.isOpen()) {

        }
    }

    
}
