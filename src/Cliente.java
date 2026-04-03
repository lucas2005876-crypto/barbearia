public class Cliente extends Thread{

    private String name;
    private int id;
    private int tamanhoDeCabelo;

    public Cliente (int id, String name, int tamanhoDeCabelo) {
        this.id = id;
        this.name = name;
        this.tamanhoDeCabelo = tamanhoDeCabelo;
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

    
}
