package bankomat;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Account {
    private static final Logger logger = LoggerFactory.getLogger(Account.class);
    @NonNull @Getter private String number;
    @NonNull @Getter private Person owner;
    @NonNull @Getter private Card card;
    @Getter private double balance;
    @Getter private List<Transaction> transactions = new ArrayList<>();

    public void withdraw(double amount) {
        if (balance >= amount && amount > 0) {
            balance -= amount;
            transactions.add(new Transaction(TransactionType.WITHDRAW, amount, LocalDateTime.now()));
            logger.info("Z konta {} wypłacono {} PLN. Nowe saldo: {}", number, amount, balance);
        } else {
            logger.warn("Nieudana próba wypłaty {} PLN z konta {} – niewystarczające środki", amount, number);
            throw new IllegalArgumentException("Niewystarczająco środków na koncie lub podano niepoprawną kwotę");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now()));
            logger.info("Na konto {} wpłacono {} PLN. Nowe saldo: {}", number, amount, balance);
        } else {
            logger.warn("Nieudana próba wpłaty {} PLN na konto {} – kwota niedodatnia", amount, number);
            throw new IllegalArgumentException("Kwota wpłaty musi być dodatnia");
        }
    }


}
