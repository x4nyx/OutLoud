package fpmibsu.outloud.exception;

public class PersistentException extends Exception {
    public PersistentException() {
        super();
    }
    public PersistentException(String message) {
        super(message);
    }
    public PersistentException(String message, Throwable cause) {
        super(message, cause);
    }
    public PersistentException(Throwable cause) {
        super(cause);
    }
}
