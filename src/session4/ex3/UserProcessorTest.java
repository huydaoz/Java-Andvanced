package session4.ex3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserProcessorTest {
    private UserProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }
    @Test
    void shouldReturnSameEmailWhenValid() {

        String email = "user@gmail.com";

        String result = processor.processEmail(email);

        assertEquals("user@gmail.com", result);
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingAtSymbol() {

        String email = "usergmail.com";

        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingDomain() {
        String email = "user@";

        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }
    @Test
    void shouldNormalizeEmailToLowercase() {
        String email = "Example@Gmail.com";

        String result = processor.processEmail(email);

        assertEquals("example@gmail.com", result);
    }
}