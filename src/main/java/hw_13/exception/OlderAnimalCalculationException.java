package hw_13.exception;

public class OlderAnimalCalculationException extends IllegalArgumentException{
    public OlderAnimalCalculationException(String message) {
        super(message);
    }
}
