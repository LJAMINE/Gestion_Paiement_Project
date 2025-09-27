package Exception;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message,Throwable issue) {
        super(message,issue);
    }
}
