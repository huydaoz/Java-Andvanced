package session6.demo;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class Main {
    public static void main(String[] args) {
        List<String> students = Arrays.asList("An", "Bình", "Chi", "Dũng", "Em", "Giang", "Hòa");
        Random random = new Random();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            int randomIndex = random.nextInt(students.size());
            String randomStudent = students.get(randomIndex);
            System.out.println("Bạn may mắn là: " + randomStudent);
    }
}