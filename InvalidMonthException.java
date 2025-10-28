/**
 * Classe de excecao customizada para meses que nao sejam validos ou nao existam.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidMonthException extends RuntimeException {
    // Construtor
    public InvalidMonthException(String message) {
        super(message);
    }    
}
