package bankomat.validator;

public class PinValidator {
    public static boolean isValidPin(String pin) {
        return pin != null && pin.matches("\\d{4}");
    }
}
