package session3.ex1;
import java.util.List;

public class Main {

    public static record User(String username, String email, String status) {}

    public static void main(String[] args) {

        List<User> users = List.of(
                new User("alice", "alice@example.com", "ACTIVE"),
                new User("bob", "bob@example.com", "INACTIVE"),
                new User("charlie", "charlie@example.com", "ACTIVE")
        );

        users.forEach(user ->
                System.out.println(user.username() + " - " +
                        user.email() + " - " +
                        user.status())
        );
    }
}