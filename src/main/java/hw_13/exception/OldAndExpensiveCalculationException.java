package hw_13.exception;

public class OldAndExpensiveCalculationException  extends IllegalArgumentException{
    public OldAndExpensiveCalculationException(String message) {
        super(message);
    }
}
