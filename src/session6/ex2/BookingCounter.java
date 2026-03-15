package session6.ex2;

public class BookingCounter extends Thread {

    private String counterName;
    private TicketPool pool;

    public BookingCounter(String counterName, TicketPool pool) {
        this.counterName = counterName;
        this.pool = pool;
    }

    @Override
    public void run() {

        while (true) {

            pool.sellTicket(counterName);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}