public class InvalidHeightException extends RuntimeException {
    public InvalidHeightException(String message) {
        super(message);
    }

    public InvalidHeightException(String message, Throwable cause) {
        super(message, cause);
    }
}
