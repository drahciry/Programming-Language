import java.util.Scanner;

public class P1nX {
    public static void main(String[] args) {
        if (args.length != 6 && args.length != 9) {
            System.out.println("Quantidade de argumentos invalida. Veja como iniciar o programa corretamente:\n");
            displayHelp();
            return;
        }

        Pessoa pessoaCriada = trataExcecaoCLI(args);

        if (pessoaCriada == null) {
            System.out.println("\nPrograma encerrado devido a erro nos argumentos.");
            return;
        }

        System.out.println("Pessoa cadastrada com sucesso!\n");
        System.out.println(pessoaCriada);

        Scanner scanner = new Scanner(System.in);
        int quantidade = 0;

        while (true) {
            System.out.print("\nDeseja criar mais quantas pessoas? (Digite 0 para encerrar) R: ");
            try {
                String input = scanner.nextLine();
                quantidade = Integer.parseInt(input);

                if (quantidade < 0)
                    System.out.println("Erro: numero nao pode ser negativo. Tente novamente");
                else
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: entrada invalida. Por favor, digite um numero inteiro e positivo.");
            }
        }

        if (quantidade == 0)
            System.out.println("Nenhuma pessoa mais sera criada.");

        Pessoa[] pessoas = new Pessoa[quantidade + 1];

        pessoas[0] = pessoaCriada;

        mainLoop:
        for (int i = 1; i < quantidade; i++) {
            System.out.println("\nCadastrando pessoa " + (i + 1) + " de " + quantidade + ".");
            System.out.println("(Deixe o 'genero' em branco e pressione ENTER para parar)");

            while (true) {
                String[] parameters;

                System.out.println("\nInsira o genero: ");
                String genero = scanner.nextLine();

                if (genero.isEmpty()) {
                    System.out.println("Entrada de dados interrompida pelo usuario.\n");
                    break mainLoop;
                }

                System.out.print("Insira o nome: ");
                String nome = scanner.nextLine();
                System.out.print("Insira o sobrenome: ");
                String sobreNome = scanner.nextLine();
                System.out.print("Insira o dia de nascimento: ");
                String diaNasc = scanner.nextLine();
                System.out.print("Insira o mes de nascimento (ex: 5 ou 'Maio'): ");
                String mesNasc = scanner.nextLine();
                System.out.print("Insira o ano de nascimento: ");
                String anoNasc = scanner.nextLine();

                System.out.print("Insira o CPF (opcional, pressione ENTER para pular): ");
                String numCPF = scanner.nextLine();

                if (!numCPF.isEmpty()) {
                    parameters = new String[9];
                    System.out.print("Insira o peso: ");
                    String peso = scanner.nextLine();
                    System.out.print("Insira a altura: ");
                    String altura = scanner.nextLine();

                    parameters[0] = genero;
                    parameters[1] = nome;
                    parameters[2] = sobreNome;
                    parameters[3] = diaNasc;
                    parameters[4] = mesNasc;
                    parameters[5] = anoNasc;
                    parameters[6] = numCPF;
                    parameters[7] = peso;
                    parameters[8] = altura;
                } else {
                    parameters = new String[6];
                    parameters[0] = genero;
                    parameters[1] = nome;
                    parameters[2] = sobreNome;
                    parameters[3] = diaNasc;
                    parameters[4] = mesNasc;
                    parameters[5] = anoNasc;
                }

                pessoaCriada = trataExcecaoLoop(parameters);
                
                if (pessoaCriada == null) {
                    System.out.println("Por favor, insira os dados novamente.");
                    continue;
                }
                
                System.out.println("\n" + pessoaCriada.getNome() + " cadastrado(a) com sucesso!");
                pessoas[i] = pessoaCriada;
                break;
            }
        }

        int numPessoasCriadas = Pessoa.getNumPessoas();

        for (int i = 0; i < numPessoasCriadas; i++)
            System.out.println("\n" + pessoas[i]);

        scanner.close();
    }

    /**
     * Metodo que retorna uma pessoa criada ou trata as excecoes. Seas excecoes forem disparadas,
     * todas serao tratadas, porem a pessoa nao sera criada, entao sera retornado null.
     * Este metodo sera usado na criacao de uma pessoa com passagem de parametros no CLI.
     * 
     * @param parameters ({@code String[]}): Array de String contendo todos os parametros de criacao de {@code Pessoa}.
     * 
     * @return {@code Pessoa}: Retorna objeto da classe {@code Pessoa} instanciado.
     */
    private static Pessoa trataExcecaoCLI(String[] parameters) {
        Pessoa pessoaCriada = null;

        try {
            pessoaCriada = createPessoa(parameters);
        } catch (InvalidNameException | InvalidSurnameException e1) {
            System.out.println("Erro de validacao: Nome ou Sobrenome invalido.");
            System.out.println("Detalhe: " + e1.getMessage());
        } catch (InvalidDataException e2) {
            System.out.println("Erro de validacao: Data de Nascimento invalida.");
            System.out.println("Detalhe: " + e2.getMessage());
        } catch (InvalidCpfException e3) {
            System.out.println("Erro de validacao: CPF invalido.");
            System.out.println("Detalhe: " + e3.getMessage());
        } catch (InvalidWeightException e4) {
            System.out.println("Erro de validacao: Peso invalido.");
            System.out.println("Detalhe: " + e4.getMessage());
        } catch (InvalidHeightException e5) {
            System.out.println("Erro de validacao: Altura invalida.");
            System.out.println("Detalhe: " + e5.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato: Peso ou Altura nao sao numeros validos.");
            System.out.println("Detalhe: \"" + e.getMessage() + "\" nao pode ser convertido.");
        } catch (IllegalArgumentException e6) {
            System.out.println("Erro de validacao: Genero invalido.");
            displayHelpGender(parameters[0]);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado:");
            e.printStackTrace();
            displayHelp();
        }

        return pessoaCriada;
    }

    /**
     * Metodo que retorna uma pessoa criada ou trata as excecoes. Seas excecoes forem disparadas,
     * todas serao tratadas, porem a pessoa nao sera criada, entao sera retornado null.
     * Este metodo sera usado no loop onde mais pessoas serao criadas.
     * 
     * @param parameters ({@code String[]}): Array de String contendo todos os parametros de criacao de {@code Pessoa}.
     * 
     * @return {@code Pessoa}: Retorna objeto da classe {@code Pessoa} instanciado.
     */
    private static Pessoa trataExcecaoLoop(String[] parameters) {
        Pessoa pessoaCriada = null;

        try {
            pessoaCriada = createPessoa(parameters);
        } catch (InvalidNameException | InvalidSurnameException e1) {
            System.out.println("\nErro de validacao: Nome ou Sobrenome invalido.");
            System.out.println("Detalhe: " + e1.getMessage());
        } catch (InvalidDataException e2) {
            System.out.println("\nErro de validacao: Data de Nascimento invalida.");
            System.out.println("Detalhe: " + e2.getMessage());
        } catch (InvalidCpfException e3) {
            System.out.println("\nErro de validacao: CPF invalido.");
            System.out.println("Detalhe: " + e3.getMessage());
        } catch (InvalidWeightException e4) {
            System.out.println("\nErro de validacao: Peso invalido.");
            System.out.println("Detalhe: " + e4.getMessage());
        } catch (InvalidHeightException e5) {
            System.out.println("\nErro de validacao: Altura invalida.");
            System.out.println("Detalhe: " + e5.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\nErro de formato: Peso ou Altura nao sao numeros validos.");
            System.out.println("Detalhe: \"" + e.getMessage() + "\" nao pode ser convertido.");
        } catch (IllegalArgumentException e6) {
            System.out.println("\nErro de validacao: Genero invalido.");
            displayHelpGender(parameters[0]);
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro inesperado:");
            e.printStackTrace();
            displayHelp();
        }

        return pessoaCriada;
    }

    /**
     * Metodo que cria uma pessoa baseado no sexo inserido.
     * 
     * @param parameters ({@code String[]}): Array de String com argumentos para criar {@code Pessoa}.
     * 
     * @return {@code Pessoa}: Retorna objeto instanciado da classe {@code Pessoa}.
     * 
     * @throws InvalidNameException Lanca excecao se o nome possuir caracteres invalidos ou estiver vazio.
     * @throws InvalidSurnameException Lanca excecao se o sobrenome possuir caracteres invalidos ou estiver vazio.
     * @throws InvalidDataException Lanca excecao se a data for invalida, como um dia negativo, um mes que nao existe
     * ou um ano que nao esta no intervalo determinado (120 anos).
     * @throws InvalidCpfException Lanca excecao se o CPF for invalido, podendo ser o formato ou os digitos verificadores.
     * @throws InvalidWeightException Lanca excecao se o peso for negativo.
     * @throws InvalidHeightException Lanca excecao se a altura for menor que 0.40m.
     * @throws NumberFormatException Lanca excecao se o peso ou a altura nao for um float.
     * @throws IllegalArgumentException Lanca excecao se o genero for invalido (help sera exibido para ajudar).
     */
    private static Pessoa createPessoa(String[] parameters)
        throws InvalidNameException, InvalidSurnameException, InvalidDataException, 
               InvalidCpfException, InvalidWeightException, InvalidHeightException,
               NumberFormatException, IllegalArgumentException {
        String genero = parameters[0].toLowerCase();
        String nome = parameters[1];
        String sobreNome = parameters[2];
        String diaNasc = parameters[3];
        String mesNasc = parameters[4];
        String anoNasc = parameters[5];
             
        if (parameters.length == 9) {
            String numCPF = parameters[6];
            String pesoStr = parameters[7];
            String alturaStr = parameters[8];

            float peso = Float.parseFloat(pesoStr);
            float altura = Float.parseFloat(alturaStr);

            if (genero.equals("masculino") || genero.equals("m"))
                return (new Homem(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura));
            else if (genero.equals("feminino") || genero.equals("f"))
                return (new Mulher(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura));
            else
                throw new IllegalArgumentException("Genero \"" + parameters[0] + "\" invalido.");
        } else {
            if (genero.equals("masculino") || genero.equals("m"))
                return (new Homem(nome, sobreNome, diaNasc, mesNasc, anoNasc));
            else if (genero.equals("feminino") || genero.equals("f"))
                return (new Mulher(nome, sobreNome, diaNasc, mesNasc, anoNasc));
            else
                throw new IllegalArgumentException("Genero \"" + parameters[0] + "\" invalido.");
        }
    }

    /**
     * Metodo que exibe mensagem de ajuda apos iniciar com argumentos incorretos.
     */
    private static void displayHelp() {
        System.out.println(
            "\njava P1nX <genero> <nome> <sobrenome> <dia> <mes> <ano> <CPF> <peso> <altura>\n" +
            "\nVeja os exemplos abaixo:\n" +
            "\nExemplo 1:\njava P1nX Masculino Richard Albino 4 1 2005\n" +
            "\nExemplo 2:\njava P1nX masculino Richard Albino 04 01 2005 000.000.000-00 95 1.73\n" +
            "\nExemplo 3:\njava P1nX M Richard Albino 4 janeiro 2005 000.000.000/00 95.0 1.73\n" +
            "\nExemplo 4:\njava P1nX m Richard Albino 4 1 2005 00000000000 95 1.73\n" +
            "\nPerceba que os argumentos CPF, peso e altura nao sao obrigatorios.\n" +
            "Para preencher o genero, voce pode usar:\n" +
            "\nPara homens: Masculino, M." +
            "\nPara mulheres: Feminino, F.\n" +
            "\nO programa sera encerrado."
        );
    }

    /**
     * Metodo estatico que exibe mensagem de ajuda para preencher genero.
     * 
     * @param genero ({@code String}): Genero da pessoa criada.
     */
    private static void displayHelpGender(String genero) {
        System.out.println(
            "O genero " + genero + " esta incorreto.\n" +
            "Para preencher o genero, voce pode usar:\n" +
            "\nPara homens: Masculino, M." +
            "\nPara mulheres: Feminino, F.\n" +
            "\nO programa sera encerrado."
        );
    }
}
