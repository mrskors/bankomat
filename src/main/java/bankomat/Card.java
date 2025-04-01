package bankomat;

import bankomat.generator.CardNumberGenerator;

public class Card {
    private final String number;
    private String pin;

    public Card(String pin) {
        this.number = CardNumberGenerator.generateCardNumber();
        setPin(pin);
    }

    public String getNumber() {
        return number;
    }

    public boolean verifyPin(String inputPin) {
        return pin.equals(inputPin);
    }

    public String getPin() { return pin; }


    public void setPin(String pin) {
        if (pin.matches("\\d{4}")) {
            this.pin = pin;
        } else {
            throw new IllegalArgumentException("Podany pin nie spełnia kryteriów");
        }
    }
}
