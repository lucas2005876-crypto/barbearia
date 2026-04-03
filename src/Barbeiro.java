public class Barbeiro extends Thread {
    private String nome;
    private Cadeira cadeira;
    private Cliente c;


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

        if(c.ge)

    }


}
