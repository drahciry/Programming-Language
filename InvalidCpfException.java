/**
 * Classe de excecao customizada para CPFs que possuam um formato invalido
 * ou que tenha digitos verificadores incorretos.
 * 
 * Esta classe eh uma "Checked Exception", ou seja, voce deve trata-la ou
 * sua classe nao sera compilada pela JVM.
 */
public class InvalidCpfException extends Exception {
    // Construtor
    public InvalidCpfException(String message) {
        super(message);
    }
}