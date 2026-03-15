package session6.ex1;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 2);
        TicketPool roomB = new TicketPool("B", 2);

        BookingCounter counter1 = new BookingCounter(
                "Quầy 1", roomA, roomB, true);

        BookingCounter counter2 = new BookingCounter(
                "Quầy 2", roomA, roomB, false);

        counter1.start();
        counter2.start();
    }
}