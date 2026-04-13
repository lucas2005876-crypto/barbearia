public class Barbeiro extends Thread {
    private String nome;
    private Cliente clienteAtual;
    private Barbearia barbearia;



    public Barbeiro(String nome, Barbearia barbearia) {
        this.nome = nome;
        this.barbearia = barbearia;
    }

    public String getNameBarbeiro() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cortarCabelo(Cliente c) throws InterruptedException{
        long tempoDeCorte = 0;
        int tamanho = c.getTamanhoDeCabelo();

        if(tamanho > 1 && tamanho < 5) {
            tempoDeCorte = 2000;
        } else if (tamanho < 8 ){
            tempoDeCorte = 4000;
        } else {
            tempoDeCorte = 6000;
        }

        System.out.println(nome + " está cortando o cabelo de " + c.getNameCliente());
        Thread.sleep(tempoDeCorte);
        System.out.println(nome + " terminou o corte de  " + c.getNameCliente());
    }

    @Override
    public void run() {
        while(Barbearia.isOpen || barbearia.temClienteNoSofa()) {
            try {
                this.clienteAtual = barbearia.chamarProximoDoSofa();
                cortarCabelo(clienteAtual);
                barbearia.realizarPagamento(clienteAtual, this);

                Thread.sleep(200); //descansa
            }
            catch (InterruptedException i) {
                System.out.println("Interrupted thread " + i.getMessage());
            }
        }
    }
}
