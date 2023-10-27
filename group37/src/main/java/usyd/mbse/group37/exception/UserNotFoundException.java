package usyd.mbse.group37.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(Long scoreId) {
        super("No User associated with UserScore id: " + scoreId);
    }
}