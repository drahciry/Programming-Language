public class InvalidSurnameException extends RuntimeException {
    public InvalidSurnameException(String message) {
        super(message);
    }

    public InvalidSurnameException(String message, Throwable cause) {
        super(message, cause);
    }
}
