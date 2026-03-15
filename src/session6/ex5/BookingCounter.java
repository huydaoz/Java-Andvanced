package session6.ex5;

import java.util.Random;

public class BookingCounter implements Runnable {

    private TicketPool[] pools;
    private Random rand = new Random();

    public BookingCounter(TicketPool[] pools) {
        this.pools = pools;
    }

    @Override
    public void run() {

        while (true) {

            int roomIndex = rand.nextInt(pools.length);

            boolean vip = rand.nextBoolean();

            TicketPool pool = pools[roomIndex];

            Ticket ticket = pool.holdTicket(vip);

            if (ticket != null) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }

                if (rand.nextBoolean()) {
                    pool.sellHeldTicket(ticket);
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}