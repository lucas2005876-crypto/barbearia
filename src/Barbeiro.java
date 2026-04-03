public class Barbeiro extends Thread {
    private String nome;
    private Cadeira cadeira;
    private Cliente c;
    public long t = 0;


    public Barbeiro(String nome, Cadeira cadeira) {
        this.nome = nome;
        this.cadeira = cadeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cortarCabelo() {
        c = cadeira.getC();
        
        if(c.getTamanhoDeCabelo() > 1 && c.getTamanhoDeCabelo() < 5) {
            t = 2000;
        } else if (c.getTamanhoDeCabelo() < 8 ){
            t = 4000;
        } else {
            t = 6000;
        }
    }
}
