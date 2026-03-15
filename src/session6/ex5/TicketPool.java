package session6.ex5;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int capacity) {

        this.roomName = roomName;

        for (int i = 1; i <= capacity; i++) {

            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket holdTicket(boolean vip) {

        for (Ticket t : tickets) {

            if (!t.isSold() && !t.isHeld()) {

                long expiry = System.currentTimeMillis() + 5000;

                t.hold(vip, expiry);

                System.out.println(
                        Thread.currentThread().getName() +
                                ": Đã giữ vé " + t.getTicketId() +
                                (vip ? " (VIP)" : "") +
                                ". Thanh toán trong 5s"
                );

                return t;
            }
        }

        return null;
    }

    public synchronized void sellHeldTicket(Ticket t) {

        if (t != null && t.isHeld()) {

            t.sell();

            System.out.println(
                    Thread.currentThread().getName() +
                            ": Thanh toán thành công " +
                            t.getTicketId()
            );
        }
    }

    public synchronized void releaseExpiredTickets() {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (t.isHeld() && now > t.getHoldExpiryTime()) {

                t.release();

                System.out.println(
                        "TimeoutManager: Vé "
                                + t.getTicketId()
                                + " hết hạn giữ, trả lại kho"
                );
            }
        }
    }
}