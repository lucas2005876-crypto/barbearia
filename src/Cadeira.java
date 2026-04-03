public class Cadeira {
    private Boolean ocupada = false;
    private int cadeira;
    private Cliente c;

    public Cadeira(int cadeira) {
        this.cadeira = cadeira;
    }

    public Boolean isOcupada() {
        return ocupada;
    }

    public void desocupaCadeira() {
        this.ocupada = false;
        c = null;
    }

    public int getCadeira() {
        return cadeira;
    }

    public Cliente getC() {
        return c;
    }

    public void sentar(Cliente c) {
        this.c = c;
    }
    
}
