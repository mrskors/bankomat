package bankomat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {
    private ATM atm;
    private Card card;

    @BeforeEach
    void setUp() {
        Bank bank = new Bank();
        Person person = new Person("Anna", "Nowak", "9876543210");
        card = new Card("1234");
        Account account = new Account("98765432109876543210987654", person, card);
        account.deposit(1000); // saldo początkowe

        bank.addAccount(account.getNumber(), account);
        atm = new ATM(bank);
    }

    @Test
    void shouldAuthenticateWithCorrectPin() {
        boolean result = atm.authenticate(card, "1234");
        assertTrue(result);
        assertTrue(atm.isAuthenticated());
    }

    @Test
    void shouldNotAuthenticateWithIncorrectPin() {
        boolean result = atm.authenticate(card, "9999");
        assertFalse(result);
        assertFalse(atm.isAuthenticated());
    }

    @Test
    void shouldCheckBalanceAfterLogin() {
        atm.authenticate(card, "1234");
        double balance = atm.checkBalance();
        assertEquals(1000, balance, 0.01);
    }

    @Test
    void shouldThrowWhenCheckingBalanceWithoutLogin() {
        assertThrows(IllegalStateException.class, () -> atm.checkBalance());
    }

    @Test
    void shouldDepositAfterLogin() {
        atm.authenticate(card, "1234");
        atm.deposit(500);
        assertEquals(1500, atm.checkBalance(), 0.01);
    }

    @Test
    void shouldWithdrawAfterLogin() {
        atm.authenticate(card, "1234");
        atm.withdraw(400);
        assertEquals(600, atm.checkBalance(), 0.01);
    }

    @Test
    void shouldThrowWhenDepositingWithoutLogin() {
        assertThrows(IllegalStateException.class, () -> atm.deposit(100));
    }

    @Test
    void shouldThrowWhenWithdrawingWithoutLogin() {
        assertThrows(IllegalStateException.class, () -> atm.withdraw(100));
    }

    @Test
    void shouldLogoutSuccessfully() {
        atm.authenticate(card, "1234");
        atm.logout();
        assertFalse(atm.isAuthenticated());
    }
}
