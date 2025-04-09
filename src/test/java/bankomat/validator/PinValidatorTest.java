package bankomat.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class PinValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "123",     // 3 cyfry – za krótko
            "12345",   // 5 cyfr – za długo
            "12a4",    // zawiera nie-cyfrę
            ""         // pusty string
    })
    void shouldRejectInvalidPins(String pin) {
        assertFalse(PinValidator.isValidPin(pin));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0000",
            "1234",
            "9999"
    })
    void shouldAcceptValidPins(String pin) {
        assertTrue(PinValidator.isValidPin(pin));
    }
}
