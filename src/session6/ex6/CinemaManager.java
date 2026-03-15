package session6.ex6;
import java.util.*;
import java.util.concurrent.*;

public class CinemaManager {

    private List<TicketPool> rooms = new ArrayList<>();

    private ExecutorService executor;

    private List<BookingCounter> counters = new ArrayList<>();

    public void init(int roomCount, int capacity, int counterCount) {

        rooms.clear();

        for (int i = 0; i < roomCount; i++) {

            char name = (char) ('A' + i);

            rooms.add(new TicketPool("" + name, capacity));
        }

        executor = Executors.newFixedThreadPool(counterCount + 2);

        for (int i = 1; i <= counterCount; i++) {

            BookingCounter counter =
                    new BookingCounter("Quầy " + i, rooms);

            counters.add(counter);

            executor.submit(counter);
        }

        executor.submit(new DeadlockDetector());

        System.out.println(
                "Đã khởi tạo hệ thống với "
                        + roomCount + " phòng, "
                        + counterCount + " quầy"
        );
    }

    public void stopSimulation() {

        for (BookingCounter c : counters) {
            c.stop();
        }

        executor.shutdownNow();

        System.out.println("Đã dừng hệ thống.");
    }

    public void statistics() {

        System.out.println("\n=== THỐNG KÊ ===");

        for (TicketPool p : rooms) {

            System.out.println(
                    "Phòng " + p.getRoomName()
                            + ": "
                            + p.soldCount()
                            + "/" + p.capacity()
                            + " vé"
            );
        }
    }
}