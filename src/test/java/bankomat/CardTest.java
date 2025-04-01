package bankomat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void shouldSetValidPin() {
        Card card = new Card("1234");
        assertEquals("1234", card.getPin());
    }

    @Test
    void shouldThrowExceptionForInvalidPin() {
        assertThrows(IllegalArgumentException.class, () -> new Card("abc"));
        assertThrows(IllegalArgumentException.class, () -> new Card("12"));
        assertThrows(IllegalArgumentException.class, () -> new Card("12345"));
    }

    @Test
    void shouldVerifyCorrectPin() {
        Card card = new Card("0000");
        assertTrue(card.verifyPin("0000"));
    }

    @Test
    void shouldNotVerifyIncorrectPin() {
        Card card = new Card("0000");
        assertFalse(card.verifyPin("9999"));
    }
}
