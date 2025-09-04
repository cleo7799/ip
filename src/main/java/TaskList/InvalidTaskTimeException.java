package TaskList;

public class InvalidTaskTimeException extends RuntimeException {
    public InvalidTaskTimeException(String message) {
        super(message);
    }
}
