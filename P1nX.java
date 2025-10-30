public class P1nX {
    public static void main(String[] args) {
        if (args.length != 6 && args.length != 9) {
            displayHelp();
        }
    }

    private static void displayHelp() {
        System.out.println(
                "Quantidade de argumentos invalida. Veja como iniciar o programa corretamente:\n" +
                "\njava P1nX <genero> <nome> <sobrenome> <dia> <mes> <ano> <CPF> <peso> <altura>\n" +
                "\nVeja os exemplos abaixo:\n" +
                "\nExemplo 1:\njava P1nX Masculino Richard Albino 4 1 2005\n" +
                "\nExemplo 2:\njava P1nX Homem Richard Albino 04 01 2005 000.000.000-00 95 1.73\n" +
                "\nExemplo 3:\njava P1nX M Richard Albino 4 janeiro 2005 000.000.000/00 95.0 1.73\n" +
                "\nExemplo 4:\njava P1nX homem Richard Albino 4 1 2005 00000000000 95 1.73\n" +
                "\nPerceba que os argumentos CPF, peso e altura nao sao obrigatorios.\n" +
                "Para preencher o genero, voce pode usar:\n" +
                "\nPara homens: Homem, Masculino, M" +
                "\nPara mulheres: Mulher, Feminino, F\n" +
                "\nO programa sera encerrado."
            );
    }
}
