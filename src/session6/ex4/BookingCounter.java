package session6.ex4;

import java.util.Random;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;

    public BookingCounter(String counterName,
                          TicketPool roomA,
                          TicketPool roomB) {

        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public int getSoldCount() {
        return soldCount;
    }

    @Override
    public void run() {

        Random rand = new Random();

        while (true) {

            if (roomA.getRemainingTickets() == 0 &&
                    roomB.getRemainingTickets() == 0) {
                break;
            }

            Ticket ticket = null;

            if (rand.nextBoolean()) {
                ticket = roomA.sellTicket();
            } else {
                ticket = roomB.sellTicket();
            }

            if (ticket != null) {

                soldCount++;

                System.out.println(counterName +
                        " đã bán vé " +
                        ticket.getTicketId());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}