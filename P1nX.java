public class P1nX {
    public static void main(String[] args) {
        if (args.length != 6 && args.length != 9) {
            displayHelp();
            return;
        }

        Pessoa pessoaCriada;

        try {
            pessoaCriada = createPessoa(args);
        } catch (InvalidNameException | InvalidSurnameException e1) {
            System.out.println("Erro de validacao: Nome ou Sobrenome invalido.");
            System.out.println("Detalhe: " + e1.getMessage());
            displayHelp();
        } catch (InvalidDataException e2) {
            System.out.println("Erro de validacao: Data de Nascimento invalida.");
            System.out.println("Detalhe: " + e2.getMessage());
            displayHelp();
        } catch (InvalidCpfException e3) {
            System.out.println("Erro de validacao: CPF invalido.");
            System.out.println("Detalhe: " + e3.getMessage());
            displayHelp();
        } catch (InvalidWeightException e4) {
            System.out.println("Erro de validacao: Peso invalido.");
            System.out.println("Detalhe: " + e4.getMessage());
            displayHelp();
        } catch (InvalidHeightException e5) {
            System.out.println("Erro de validacao: Altura invalida.");
            System.out.println("Detalhe: " + e5.getMessage());
            displayHelp();
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato: Peso ou Altura nao sao numeros validos.");
            System.out.println("Detalhe: \"" + e.getMessage() + "\" nao pode ser convertido.");
            displayHelp();
        } catch (IllegalArgumentException e6) {
            System.out.println("Erro de validacao: Genero invalido.");
            displayHelpGender(args[0]);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado:");
            e.printStackTrace();
            displayHelp();
        }

        if (pessoaCriada == null) {
            System.out.println("\nPrograma encerrado devido a erro nos argumentos.");
            return;
        }

        System.out.println("Pessoa cadastrada com sucesso!");
        System.out.println(pessoaCriada);
    }

    private static Pessoa createPessoa(String[] parameters) {
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

    private static void displayHelp() {
        System.out.println(
            "Quantidade de argumentos invalida. Veja como iniciar o programa corretamente:\n" +
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
