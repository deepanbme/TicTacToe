package exceptions;

public class NoStrategySelectedException extends RuntimeException{
    public NoStrategySelectedException(String message) {
        super(message);
    }
}
