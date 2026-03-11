package session4.ex1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {
    @Test
    void TC01_validUsername() {

        UserValidator validator = new UserValidator();
        String username = "user123";

        boolean result = validator.isValidUsername(username);

        assertTrue(result);
    }
    @Test
    void TC02_usernameTooShort() {
        UserValidator validator = new UserValidator();
        String username = "abc";

        boolean result = validator.isValidUsername(username);

        assertFalse(result);
    }
    @Test
    void TC03_usernameContainsSpace() {
        UserValidator validator = new UserValidator();
        String username = "user name";

        boolean result = validator.isValidUsername(username);

        assertFalse(result);
    }
}