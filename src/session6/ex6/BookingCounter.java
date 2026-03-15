package session6.ex6;
import java.util.List;
import java.util.Random;

public class BookingCounter implements Runnable {

    private String name;
    private List<TicketPool> pools;
    private volatile boolean running = true;

    public BookingCounter(String name, List<TicketPool> pools) {
        this.name = name;
        this.pools = pools;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {

        Random rand = new Random();

        while (running) {

            TicketPool pool = pools.get(rand.nextInt(pools.size()));

            Ticket ticket = pool.sellTicket();

            if (ticket != null) {

                System.out.println(name +
                        " bán vé " + ticket.getId());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}