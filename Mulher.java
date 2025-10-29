public class Mulher extends Pessoa {
    private static final String SEXO = "Feminino";

    public Mulher(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
    }

    public Mulher(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
    }

    public Mulher(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, long numCPF, float peso, float altura) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
    }

    public Mulher(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, long numCPF, float peso, float altura) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
    }

    public String getSexo() {
        return SEXO;
    }

    public String toString() {
        return super.toString() + "\nGenero: " + SEXO;
    }
}
