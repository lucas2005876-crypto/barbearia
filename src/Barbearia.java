import java.util.ArrayList;

public class Barbearia {

    Barbeiro barbeiros[] = new Barbeiro[3];
    ArrayList<Cliente> listEmPe = new ArrayList<>();
    ArrayList<Cliente> listSofa = new ArrayList<>();
    ArrayList<Cadeira> listCadeiras = new ArrayList<>();
    public Boolean isOpen = true;

    public Barbearia(Barbeiro[] barbeiros) {
        this.barbeiros = barbeiros;
    }

    public void SentarSofa(){
        if(listSofa.size()<4) {
            Cliente cliente = listEmPe.removeFirst();
            listSofa.add(cliente);
        }
        else{
            Cliente cliente = listEmPe.getFirst();
            System.out.println(cliente + "Tentou sentar no sofá, mas o sofá está cheio.");
        }
    }
    
    public void ChegouCliente(Cliente cliente){
        if (listEmPe.size() < 13){
            listEmPe.add(cliente);
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void closing() {
        isOpen = false;
    }
}
