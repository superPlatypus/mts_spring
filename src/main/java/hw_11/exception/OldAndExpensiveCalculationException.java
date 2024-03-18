package hw_11.exception;

public class OldAndExpensiveCalculationException  extends IllegalArgumentException{
    public OldAndExpensiveCalculationException(String message) {
        super(message);
    }
}
