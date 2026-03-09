package session2.ex4;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        users.add(new User("Alice"));
        users.add(new User("Bob"));
        users.add(new User("Charlie"));

        Function<User, String> getUsername = User::getUsername;

        Consumer<String> print = System.out::println;

        Supplier<User> createUser = User::new;

        for (User user : users) {
            print.accept(getUsername.apply(user));
        }

        User newUser = createUser.get();
        print.accept(newUser.getUsername());
    }
}