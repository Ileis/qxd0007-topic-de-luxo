import java.util.List;
import java.util.ArrayList;

public class Topic{

    private final int capacidade;
    private final int qtdPrioritarios;
    private List<Passageiro> assentosPrioritarios;
    private List<Passageiro> assentosNormais;
    private int vagasNormais;
    private int vagasPrioritarias;

    public Topic(int capacidade, int qtdPrioritarios) {

        if(qtdPrioritarios > capacidade)
            throw new IllegalArgumentException();

        this.capacidade = capacidade;
        this.qtdPrioritarios = qtdPrioritarios;
        this.assentosPrioritarios = new ArrayList<Passageiro>();
        this.assentosNormais = new ArrayList<Passageiro>();

        vagasNormais = capacidade - qtdPrioritarios;
        vagasPrioritarias = qtdPrioritarios;

        inicializaAssentosNormais();
        inicializaAssentosPrioritarios();
    }

    private void inicializaAssentosNormais(){
        for(int num = 0; num < getNumAssentosNormais(); num++){
            getAssentosNormais().add(null);
        }
    }

    private void inicializaAssentosPrioritarios(){
        for(int num = 0; num < getNumAssentosPrioritarios(); num++){
            getAssentosPrioritarios().add(null);
        }
    }

    public List<Passageiro> getAssentosPrioritarios() {
        return assentosPrioritarios;
    }
    public List<Passageiro> getAssentosNormais() {
        return assentosNormais;
    }

    public boolean subir(Passageiro passageiro) {
        if(temVaga()){
            if(passageiro.ePrioritario())
                if(temVagaPrioritaria())
                    subirEmAssentoPrioritario(passageiro);

                else if(temVagaNormal())
                    subirEmAssentoNormal(passageiro);

                else
                    return false;

            else if(temVagaNormal())
                subirEmAssentoNormal(passageiro);

            else if(temVagaPrioritaria())
                subirEmAssentoPrioritario(passageiro);

            else
                return false;

            return true;
        }
        return false;
    }

    private void subirEmAssentoNormal(Passageiro passageiro){
        int index = getAssentosNormais().indexOf(null);
        getAssentosNormais().set(index, passageiro);
        vagasNormais--;
    }

    private void subirEmAssentoPrioritario(Passageiro passageiro){
        int index = getAssentosPrioritarios().indexOf(null);
        getAssentosPrioritarios().set(index, passageiro);
        vagasPrioritarias--;
    }

    public int getVagas() {
        return vagasNormais + vagasPrioritarias;
    }

    private boolean temVaga(){
        return getVagas() > 0;
    }

    private boolean temVagaNormal(){
        return vagasNormais > 0;
    }

    private boolean temVagaPrioritaria(){
        return vagasPrioritarias > 0;
    }

    private int getNumAssentosNormais(){
        return capacidade - qtdPrioritarios;
    }

    private int getNumAssentosPrioritarios(){
        return qtdPrioritarios;
    }

    public boolean descer(String nome){

        if(descerAssentoNormal(nome))
            return true;

        if(descerAssentoPrioritario(nome))
            return true;

        return false;
    }

    private boolean descerAssentoNormal(String nome){
        for(int index = 0; index < getNumAssentosNormais(); index++){

            if(getAssentosNormais().get(index) == null)
                continue;

            if(getAssentosNormais().get(index).getNome().equals(nome)){
                getAssentosNormais().set(index, null);
                vagasNormais++;
                return true;
            }
        }
        return false;
    }

    private boolean descerAssentoPrioritario(String nome){
        for(int index = 0; index < getNumAssentosPrioritarios(); index++){

            if(getAssentosPrioritarios().get(index) == null)
                continue;

            if(getAssentosPrioritarios().get(index).getNome().equals(nome)){
                getAssentosPrioritarios().set(index, null);
                vagasPrioritarias++;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String topic = "[";

        for(int index = 0; index < getNumAssentosPrioritarios(); index++){
            topic += "@";
            if(getAssentosPrioritarios().get(index) != null)
                topic += getAssentosPrioritarios().get(index).getNome();
            topic += " ";
        }

        for(int index = 0; index < getNumAssentosNormais(); index++){
            topic += "=";
            if(getAssentosNormais().get(index) != null)
                topic += getAssentosNormais().get(index).getNome();
            topic += " ";
        }

        topic += "]";

        return topic;
    }
}