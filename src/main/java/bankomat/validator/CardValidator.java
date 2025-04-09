package bankomat.validator;

public class CardValidator {
    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }
}
