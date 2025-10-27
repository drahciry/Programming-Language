public class Teste {
    public static void main(String[] args) {
        try {
            System.out.println(ValidaCPF.imprimeCPF("16251932708"));
        } catch (InvalidCpfException e) {
            System.out.println("Erro: " + e);
        }
    }
}
