package bankomat.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class CardValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "123456781234567",     // 15 cyfr – za krótko
            "12345678123456789",   // 17 cyfr – za długo
            "123456781234567a",    // znak nie-cyfrowy
            ""                     // pusty string
    })
    void shouldRejectInvalidCardNumbers(String cardNumber) {
        assertFalse(CardValidator.isValidCardNumber(cardNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1234567812345678" // dokładnie 16 cyfr
    })
    void shouldAcceptValidCardNumber(String cardNumber) {
        assertTrue(CardValidator.isValidCardNumber(cardNumber));
    }
}
