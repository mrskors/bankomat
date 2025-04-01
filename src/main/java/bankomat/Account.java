package bankomat;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Account {
    @NonNull @Getter private String number;
    @NonNull @Getter private Person owner;
    @NonNull @Getter private Card card;
    @Getter private double balance;
    @Getter private List<Transaction> transactions = new ArrayList<>();

    public void withdraw(double amount) {
        if (balance >= amount && amount > 0) {
            balance -= amount;
            transactions.add(new Transaction(TransactionType.WITHDRAW, amount, LocalDateTime.now()));
        } else
            throw new IllegalArgumentException("Niewystarczająco środków na koncie lub podano niepoprawną kwotę");
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now()));
        } else
            throw new IllegalArgumentException("Kwota wpłaty musi być dodatnia");
    }


}
