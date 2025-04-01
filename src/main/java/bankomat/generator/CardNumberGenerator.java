package bankomat.generator;

import java.util.Random;

public class CardNumberGenerator {
    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder("4"); // Prefiks dla Visa

        for (int i = 1; i < 15; i++) { // Generujemy 14 losowych cyfr
            cardNumber.append(random.nextInt(10));
        }

        // Obliczamy cyfrę kontrolną zgodnie z algorytmem Luhna
        cardNumber.append(getLuhnCheckDigit(cardNumber.toString()));

        return cardNumber.toString();
    }

    private static int getLuhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = true;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }
}