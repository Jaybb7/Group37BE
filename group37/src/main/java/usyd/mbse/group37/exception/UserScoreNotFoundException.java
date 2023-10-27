package usyd.mbse.group37.exception;

public class UserScoreNotFoundException extends RuntimeException {
    public UserScoreNotFoundException(Long id) {
        super("No UserScore found with id: " + id);
    }
}