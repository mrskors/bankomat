package bankomat;

import java.time.LocalDateTime;

public class ATM {
    private Bank bank;
    private Account loggedInAccount;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public boolean authenticate(Card card, String pin) {
        if (card == null || pin == null)
            return false;

        Account account = bank.findAccountByCardNumber(card.getNumber());
        if (account != null && card.verifyPin(pin)) {
            this.loggedInAccount = account;
            return true;
        }
        return false;
    }

    public boolean isAuthenticated() {
        return loggedInAccount != null;
    }

    public void logout() {
        this.loggedInAccount = null;
    }

    public double checkBalance() {
        if (!isAuthenticated()) {
            throw new IllegalStateException("Brak dostępu. Użytkownik nie jest zalogowany.");
        }

        double balance = loggedInAccount.getBalance();
        loggedInAccount.getTransactions().add(
                new Transaction(TransactionType.CHECK_BALANCE, 0.0, LocalDateTime.now())
        );
        return balance;
    }


    public void showTransactionHistory() {
        if (!isAuthenticated()) {
            throw new IllegalStateException("Brak dostępu. Użytkownik nie jest zalogowany.");
        }
        for (Transaction transaction : loggedInAccount.getTransactions()) {
            System.out.println(transaction);
        }
    }

    public void showTransactionHistoryByType(TransactionType filterType) {
        if (!isAuthenticated()) {
            throw new IllegalStateException("Brak dostępu. Użytkownik nie jest zalogowany.");
        }

        for (Transaction transaction : loggedInAccount.getTransactions()) {
            if (transaction.getType() == filterType) {
                System.out.println(transaction);
            }
        }
    }

    public void changePin(String pin) {
        if (!isAuthenticated()) {
            throw new IllegalStateException("Brak dostępu. Użytkownik nie jest zalogowany.");
        }
        loggedInAccount.getCard().setPin(pin);
        loggedInAccount.getTransactions().add(
                new Transaction(TransactionType.CHANGE_PIN, 0.0, LocalDateTime.now())
        );
    }

    public void deposit(double amount) {
        if (!isAuthenticated()) {
            throw new IllegalStateException("Brak dostępu. Użytkownik nie jest zalogowany.");
        }
        loggedInAccount.deposit(amount);
    }

    public void withdraw(double amount) {
        if (!isAuthenticated()) {
            throw new IllegalStateException("Brak dostępu. Użytkownik nie jest zalogowany.");
        }
        loggedInAccount.withdraw(amount);
    }

}
