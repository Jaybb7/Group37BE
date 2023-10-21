package usyd.mbse.group37.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
