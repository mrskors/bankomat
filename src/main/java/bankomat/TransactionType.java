package bankomat;

public enum TransactionType {
    WITHDRAW("Wypłata gotówki"),
    DEPOSIT("Wpłata środków"),
    CHECK_BALANCE("Sprawdzenie salda"),
    CHANGE_PIN("Zmiana PIN-u");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
