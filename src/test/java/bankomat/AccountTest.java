package bankomat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        Person person = new Person("Jan", "Kowalski", "1234567890");
        Card card = new Card("0000");
        account = new Account("12345678901234567890123456", person, card);
        account.deposit(1000);
    }

    @Test
    void shouldRecordTransactions() {
        account.deposit(100);
        account.withdraw(50);

        List<Transaction> history = account.getTransactions();

        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(history).hasSize(3);
            soft.assertThat(history.get(0).getType()).isEqualTo(TransactionType.DEPOSIT); // pierwsza: startowe 1000
            soft.assertThat(history.get(1).getType()).isEqualTo(TransactionType.DEPOSIT); // druga: +100
            soft.assertThat(history.get(2).getType()).isEqualTo(TransactionType.WITHDRAW); // trzecia: -50
            soft.assertThat(history.get(2).getAmount()).isEqualTo(50);
        });
    }
}
