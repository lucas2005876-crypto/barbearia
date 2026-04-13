public class Cliente extends Thread{

    private String name;
    private int id;
    private int tamanhoDeCabelo;
    private Barbearia barbearia;

    public Cliente (int id, String name, int tamanhoDeCabelo, Barbearia barbearia) {
        this.id = id;
        this.name = name;
        this.tamanhoDeCabelo = tamanhoDeCabelo;
        this.barbearia = barbearia;
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
            try {
                barbearia.entrar(this);
            } catch (InterruptedException i) {
                System.out.println("Interrupted thread " + i.getMessage());
            }
        }



    
}
