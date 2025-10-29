// Importacoes uteis a classe
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;

/**
 * Classe que representa uma pessoa.
 * Possue atributos como:
 * Nome,
 * Sobrenome,
 * Data de Nascimento,
 * Numero de CPF,
 * Peso e
 * Altura.
 */
public class Pessoa {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/
    
    /**
     * Regex para validar nome e sobrenome.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final Pattern NOME_VALIDO = Pattern.compile("^[\\p{L}\s]+$");
    private static int numPessoas = 0;
    private String nome;
    private String sobreNome;
    private LocalDate dataNasc;
    private long numCPF = 0;
    private float peso = 0;
    private float altura = 0;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor básico.
     * Composto de Nome, Sobrenome e Data de Nascimento.
     * 
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code int}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code int}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code int}): Ano de nascimento da {@code Pessoa}.
     */
    public Pessoa(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc) {
        numPessoas++;
        setNome(nome);
        setSobreNome(sobreNome);
        setDataNasc(diaNasc, mesNasc, anoNasc);
    }

    /**
     * Construtor básico.
     * Composto de Nome, Sobrenome e Data de Nascimento.
     * 
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Pessoa}.
     */
    public Pessoa(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc) {
        numPessoas++;
        setNome(nome);
        setSobreNome(sobreNome);
        setDataNasc(diaNasc, mesNasc, anoNasc);
    }

    /**
     * Construtor especializado.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code int}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code int}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code int}): Ano de nascimento da {@code Pessoa}.
     * @param numCPF ({@code long}): Numero de CPF da {@code Pessoa}.
     * @param peso ({@code float}): Peso da {@code Pessoa}.
     * @param altura ({@code float}): Altura da {@code Pessoa}.
     */
    public Pessoa(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, long numCPF, float peso, float altura) {
        this(sobreNome, sobreNome, diaNasc, mesNasc, anoNasc);
        setNumCPF(numCPF);
        setPeso(peso);
        setAltura(altura);
    }

    /**
     * Construtor especializado.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Pessoa}.
     * @param numCPF ({@code String}): Numero de CPF da {@code Pessoa}.
     * @param peso ({@code float}): Peso da {@code Pessoa}.
     * @param altura ({@code float}): Altura da {@code Pessoa}.
     */
    public Pessoa(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, long numCPF, float peso, float altura) {
        this(sobreNome, sobreNome, diaNasc, mesNasc, anoNasc);
        setNumCPF(numCPF);
        setPeso(peso);
        setAltura(altura);
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    public void setNome(String nome) throws InvalidNameException {
        if (nome.trim().isEmpty() || !NOME_VALIDO.matcher(nome).matches())
            throw new InvalidNameException(
                "O nome \"" + nome + "\" nao pode ser utilizado (deve conter apenas letras e espaco)."
            );
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setSobreNome(String sobreNome) throws InvalidSurnameException {
        if (nome.trim().isEmpty() || !NOME_VALIDO.matcher(sobreNome).matches())
            throw new InvalidSurnameException(
                "O sobrenome \"" + sobreNome + "\" nao pode ser utilizado (deve conter apenas letras e espacos)."
            );
        this.sobreNome = sobreNome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setDataNasc(int dia, int mes, int ano) throws InvalidDataException {
        if (!ValidaData.isData(dia, mes, ano))
            throw new InvalidDataException(dia + "/" + mes + "/" + ano + " nao eh uma data valida.");
        dataNasc = LocalDate.of(ano, mes, dia);
    }

    public void setDataNasc(String dia, String mes, String ano) throws InvalidDataException {
        try {
            if (!ValidaData.isData(dia, mes, ano))
                throw new InvalidDataException(dia + "/" + mes + "/" + ano + " nao eh uma data valida.");
        } catch (InvalidDayException e1) {
            throw new InvalidDataException(dia + " nao eh um dia valido.", e1);
        } catch (InvalidMonthException e2) {
            throw new InvalidDataException(mes + " nao eh um mes valido.", e2);
        } catch (InvalidYearException e3) {
            throw new InvalidDataException(ano + " nao eh um ano valido.", e3);
        }
        int diaInteiro = Integer.parseInt(dia);
        int anoInteiro = Integer.parseInt(ano);
        int mesInteiro;
        try {
            mesInteiro = Integer.parseInt(mes);
        } catch (NumberFormatException e) {
            mesInteiro = ValidaData.converteMes(mes);
        }
        dataNasc = LocalDate.of(anoInteiro, mesInteiro, diaInteiro);
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setNumCPF(long numCPF) {
        if (!ValidaCPF.isCPF(Long.toString(numCPF)))
            throw new InvalidCpfException(numCPF + " nao eh um CPF valido.");
        this.numCPF = numCPF;
    }

    public long getNumCPF() {
        return numCPF;
    }

    public void setPeso(float peso) throws InvalidWeightException {
        if (peso <= 0)
            throw new InvalidWeightException(peso + " nao eh valido. Peso deve ser um valor positivo.");
        this.peso = peso;
    }

    public float getPeso() {
        return peso;
    }

    public void setAltura(float altura) throws InvalidHeightException {
        if (altura < 0.40)
            throw new InvalidHeightException(altura + " nao eh valida. Altura deve ser um valor acima de 0.40cm.");
        this.altura = altura;
    }

    public float getAltura() {
        return altura;
    }

    public int getIdade() {
        return Period.between(this.dataNasc, LocalDate.now()).getYears();
    }

    public int getNumPessoas() {
        return numPessoas;
    }

    // Método toString(), padrão para exibição de informação.
    public String toString() {
        if (numCPF == 0)
            return (
                "Nome: " + nome + sobreNome +
                "\nIdade: " + getIdade() +
                "\nData de Nascimento: " + dataNasc.toString()
            );
        return (
            "Nome: " + nome + sobreNome +
            "\nIdade: " + getIdade() +
            "\nData de Nascimento: " + dataNasc.toString() +
            "\nCPF: " + ValidaCPF.toString(String.format("%011d", numCPF)) +
            "\nPeso: " + peso +
            "\nAltura: " + altura 
        );
    }
}
