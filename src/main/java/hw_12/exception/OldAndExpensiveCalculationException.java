package hw_12.exception;

public class OldAndExpensiveCalculationException  extends IllegalArgumentException{
    public OldAndExpensiveCalculationException(String message) {
        super(message);
    }
}
