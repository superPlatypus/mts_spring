package hw_11.exception;

public class OlderAnimalCalculationException extends IllegalArgumentException{
    public OlderAnimalCalculationException(String message) {
        super(message);
    }
}
