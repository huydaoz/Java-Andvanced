package session4.ex4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordServiceTest {
    private PasswordService service;
    @BeforeEach
    void setUp() {
        service = new PasswordService();
    }

    @Test
    void shouldReturnStrongWhenPasswordMeetsAllCriteria() {
        String result = service.evaluatePasswordStrength("Abc123!@");

        assertEquals("Mạnh", result);
    }

    @Test
    void shouldReturnMediumWhenMissingUppercase() {
        String result = service.evaluatePasswordStrength("abc123!@");

        assertEquals("Trung bình", result);
    }

    @Test
    void shouldReturnMediumWhenMissingLowercase() {
        String result = service.evaluatePasswordStrength("ABC123!@");

        assertEquals("Trung bình", result);
    }

    @Test
    void shouldReturnMediumWhenMissingDigit() {
        String result = service.evaluatePasswordStrength("Abcdef!@");

        assertEquals("Trung bình", result);
    }

    @Test
    void shouldReturnMediumWhenMissingSpecialCharacter() {
        String result = service.evaluatePasswordStrength("Abc12345");

        assertEquals("Trung bình", result);
    }

    @Test
    void shouldReturnWeakWhenPasswordTooShort() {
        String result = service.evaluatePasswordStrength("Ab1!");

        assertEquals("Yếu", result);
    }

    @Test
    void shouldReturnWeakWhenOnlyLowercaseLetters() {
        String result = service.evaluatePasswordStrength("password");

        assertEquals("Yếu", result);
    }

    @Test
    void shouldReturnWeakWhenOnlyUppercaseAndNumbers() {
        String result = service.evaluatePasswordStrength("ABC12345");

        assertEquals("Yếu", result);
    }
}