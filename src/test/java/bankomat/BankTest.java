package bankomat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private static final Logger logger = LoggerFactory.getLogger(BankTest.class);
    private Bank bank;
    private Account account;
    private String accountNumber;
    private Card card;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        Person person = new Person("Jan", "Kowalski", "1234567890");
        card = new Card("0000");
        accountNumber = "12345678901234567890123456"; // przykładowy numer konta
        account = new Account(accountNumber, person, card);
    }

    @Test
    void shouldAddAccountSuccessfully() {
        bank.addAccount(accountNumber, account);
        assertEquals(account, bank.findAccountByAccountNumber(accountNumber));
    }

    @Test
    void shouldThrowExceptionWhenAddingDuplicateAccount() {
        bank.addAccount(accountNumber, account);
        assertThrows(IllegalArgumentException.class, () -> bank.addAccount(accountNumber, account));
    }

    @Test
    void shouldFindAccountByCardNumber() {
        bank.addAccount(accountNumber, account);
        Account found = bank.findAccountByCardNumber(card.getNumber());
        assertEquals(account, found);
    }

    @Test
    void shouldReturnNullWhenCardNotFound() {
        assertNull(bank.findAccountByCardNumber("9999999999999999"));
    }

    @Test
    void shouldReturnNullWhenAccountNumberNotFound() {
        assertNull(bank.findAccountByAccountNumber("00000000000000000000000000"));
    }
}
