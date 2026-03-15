package session6.ex3;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool pool;
    private int soldCount = 0;

    public BookingCounter(String counterName, TicketPool pool) {
        this.counterName = counterName;
        this.pool = pool;
    }

    @Override
    public void run() {

        while (true) {

            Ticket ticket = pool.sellTicket();

            if (ticket != null) {

                soldCount++;

                System.out.println(counterName +
                        " đã bán vé " + ticket.getId());

            } else {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println(counterName +
                " bán được: " + soldCount + " vé");
    }
}