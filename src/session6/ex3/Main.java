package session6.ex3;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter counter1 =
                new BookingCounter("Quầy 1", roomA);

        BookingCounter counter2 =
                new BookingCounter("Quầy 2", roomB);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        TicketSupplier supplier =
                new TicketSupplier(roomA, roomB,
                        3, 3000, 3);

        Thread supplierThread = new Thread(supplier);

        t1.start();
        t2.start();
        supplierThread.start();

        Thread.sleep(12000);

        t1.interrupt();
        t2.interrupt();

        Thread.sleep(1000);

        System.out.println("Vé còn lại phòng A: "
                + roomA.getRemainingTickets());

        System.out.println("Vé còn lại phòng B: "
                + roomB.getRemainingTickets());
    }
}