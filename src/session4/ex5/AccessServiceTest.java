package session4.ex5;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccessServiceTest {
    private AccessService service;
    private User admin;
    private User moderator;
    private User user;

    @BeforeEach
    void setUp() {
        service = new AccessService();
        admin = new User("Admin1", Role.ADMIN);
        moderator = new User("Mod1", Role.MODERATOR);
        user = new User("User1", Role.USER);
    }

    @AfterEach
    void tearDown() {
        admin = null;
        moderator = null;
        user = null;
    }

    @Test
    void adminShouldDeleteUser() {
        boolean result = service.canPerformAction(admin, Action.DELETE_USER);
        assertTrue(result);
    }

    @Test
    void adminShouldLockUser() {
        boolean result = service.canPerformAction(admin, Action.LOCK_USER);
        assertTrue(result);
    }

    @Test
    void adminShouldViewProfile() {
        boolean result = service.canPerformAction(admin, Action.VIEW_PROFILE);
        assertTrue(result);
    }

    @Test
    void moderatorCannotDeleteUser() {
        boolean result = service.canPerformAction(moderator, Action.DELETE_USER);
        assertFalse(result);
    }

    @Test
    void moderatorCanLockUser() {
        boolean result = service.canPerformAction(moderator, Action.LOCK_USER);
        assertTrue(result);
    }

    @Test
    void moderatorCanViewProfile() {
        boolean result = service.canPerformAction(moderator, Action.VIEW_PROFILE);
        assertTrue(result);
    }

    @Test
    void userCannotDeleteUser() {
        boolean result = service.canPerformAction(user, Action.DELETE_USER);
        assertFalse(result);
    }

    @Test
    void userCannotLockUser() {
        boolean result = service.canPerformAction(user, Action.LOCK_USER);
        assertFalse(result);
    }

    @Test
    void userCanViewProfile() {
        boolean result = service.canPerformAction(user, Action.VIEW_PROFILE);
        assertTrue(result);
    }
}