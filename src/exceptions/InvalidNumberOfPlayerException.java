package exceptions;

public class InvalidNumberOfPlayerException extends RuntimeException{
    public InvalidNumberOfPlayerException(String message) {
        super(message);
    }
}
