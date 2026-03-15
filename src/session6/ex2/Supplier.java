package session6.ex2;

public class Supplier extends Thread {

    private TicketPool pool;

    public Supplier(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.addTickets(3);
    }
}