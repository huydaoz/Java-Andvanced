package session2.ex6;

public class Main {
    public static void main(String[] args) {

        User user = new User("huydao");

        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(user);

        System.out.println(result);
    }

}