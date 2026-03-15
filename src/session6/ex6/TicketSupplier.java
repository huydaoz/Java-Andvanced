package session6.ex6;

public class TicketSupplier implements Runnable {

    private TicketPool pool;

    public TicketSupplier(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                break;
            }

            System.out.println(
                    "Nhà cung cấp thêm vé vào phòng "
                            + pool.getRoomName());
        }
    }
}