package bankomat;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(String accountNumber, Account account) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Konto o numerze " + accountNumber + " już istnieje.");
        }
        accounts.put(accountNumber, account);
    }

    public Account findAccountByAccountNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public Account findAccountByCardNumber(String cardNumber) {
        for (Account account : accounts.values()) {
            if (account.getCard().getNumber().equals(cardNumber)) {
                return account;
            }
        }
        return null; // Nie znaleziono konta
    }

}
