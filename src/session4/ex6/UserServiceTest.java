package session4.ex6;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class UserServiceTest {

    private UserService service;
    private User existingUser;
    private List<User> allUsers;

    @BeforeEach
    void setUp() {
        service = new UserService();
        existingUser = new User("user1@gmail.com", LocalDate.of(2000, 1, 1));
        User otherUser = new User("user2@gmail.com", LocalDate.of(1999, 1, 1));
        allUsers = new ArrayList<>();
        allUsers.add(existingUser);
        allUsers.add(otherUser);
    }

    @Test
    void shouldUpdateProfileSuccessfully() {
        UserProfile newProfile =
                new UserProfile("newmail@gmail.com", LocalDate.of(2001, 1, 1));

        User result = service.updateProfile(existingUser, newProfile, allUsers);

        assertNotNull(result);
    }

    @Test
    void shouldRejectFutureBirthDate() {
        UserProfile newProfile = new UserProfile("newmail@gmail.com", LocalDate.now().plusDays(1));

        User result = service.updateProfile(existingUser, newProfile, allUsers);

        assertNull(result);
    }
    @Test
    void shouldRejectDuplicateEmail() {
        UserProfile newProfile =
                new UserProfile("user2@gmail.com", LocalDate.of(2001, 1, 1));

        User result = service.updateProfile(existingUser, newProfile, allUsers);

        assertNull(result);
    }

    @Test
    void shouldAllowUpdateWhenEmailUnchanged() {
        UserProfile newProfile = new UserProfile("user1@gmail.com", LocalDate.of(2002, 2, 2));

        User result = service.updateProfile(existingUser, newProfile, allUsers);

        assertNotNull(result);
    }

    @Test
    void shouldUpdateWhenUserListEmpty() {
        List<User> emptyList = new ArrayList<>();

        UserProfile newProfile = new UserProfile("unique@gmail.com", LocalDate.of(2001, 1, 1));

        User result = service.updateProfile(existingUser, newProfile, emptyList);

        assertNotNull(result);
    }

    @Test
    void shouldRejectWhenMultipleConstraintsViolated() {
        UserProfile newProfile = new UserProfile("user2@gmail.com", LocalDate.now().plusDays(1));

        User result = service.updateProfile(existingUser, newProfile, allUsers);

        assertNull(result);
    }
}