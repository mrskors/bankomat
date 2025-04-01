package bankomat.generator;

import java.util.Random;

public class AccountNumberGenerator {

    public static String generateAccountNumber() {
        String bankId = "12345678"; // Przykładowy numer rozliczeniowy banku (8 cyfr)
        String clientPart = generateRandomDigits(16);

        // Tymczasowy numer bez cyfr kontrolnych
        String tempAccount = bankId + clientPart;

        // Dodajemy "00" jako tymczasowe cyfry kontrolne z przodu
        String controlBase = "00" + tempAccount;

        // Zamieniamy na formę do obliczenia modulo 97
        String rearranged = controlBase + "2521"; // 2521 = "PL" jako liczby (P=25, L=21)
        int checkDigits = 98 - mod97(rearranged);

        // Formatowanie z wiodącym zerem jeśli potrzeba
        String control = (checkDigits < 10 ? "0" : "") + checkDigits;

        return control + tempAccount;
    }

    private static int mod97(String number) {
        StringBuilder sb = new StringBuilder();
        for (char c : number.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append((int) c - 55); // A=10, B=11, ..., Z=35
            } else {
                sb.append(c);
            }
        }

        String numStr = sb.toString();
        int remainder = 0;
        for (int i = 0; i < numStr.length(); i += 7) {
            String part = remainder + numStr.substring(i, Math.min(i + 7, numStr.length()));
            remainder = Integer.parseInt(part) % 97;
        }
        return remainder;
    }

    private static String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < length; i++) {
            digits.append(random.nextInt(10));
        }
        return digits.toString();
    }
}
