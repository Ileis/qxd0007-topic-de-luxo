public class Passageiro {

    private final String nome;
    private final int idade;
    private final int IDADE_PRIORITARIA = 65;

    public Passageiro(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    public boolean ePrioritario() {
        return idade >= IDADE_PRIORITARIA;
    }

    String getNome() {
        return nome;
    }
}