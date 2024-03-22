package hw_12.exception;

public class OlderAnimalCalculationException extends IllegalArgumentException{
    public OlderAnimalCalculationException(String message) {
        super(message);
    }
}
