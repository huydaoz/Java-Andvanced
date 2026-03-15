package session6.ex2;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private String roomName;
    private Queue<String> tickets = new LinkedList<>();
    private int ticketCounter = 1;

    public TicketPool(String roomName, int totalTickets) {
        this.roomName = roomName;

        for (int i = 0; i < totalTickets; i++) {
            tickets.add(roomName + "-" + String.format("%03d", ticketCounter++));
        }
    }

    public synchronized String sellTicket(String counterName) {

        while (tickets.isEmpty()) {
            try {
                System.out.println(counterName +
                        ": Hết vé phòng " + roomName + ", đang chờ...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String ticket = tickets.poll();

        System.out.println(counterName + " bán vé " + ticket);

        return ticket;
    }

    public synchronized void addTickets(int n) {

        for (int i = 0; i < n; i++) {
            tickets.add(roomName + "-" + String.format("%03d", ticketCounter++));
        }

        System.out.println("Nhà cung cấp: Đã thêm " + n +
                " vé vào phòng " + roomName);

        notifyAll();
    }
}