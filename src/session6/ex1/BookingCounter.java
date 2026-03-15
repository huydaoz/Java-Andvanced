package session6.ex1;

public class BookingCounter extends Thread {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private boolean lockAFirst;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB, boolean lockAFirst) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
        this.lockAFirst = lockAFirst;
    }

    @Override
    public void run() {
        sellCombo();
    }
    public void sellCombo() {
        synchronized (roomA) {
            synchronized (roomB) {
                String ticketA = roomA.getTicket();
                String ticketB = roomB.getTicket();
                if (ticketA != null && ticketB != null) {
                    System.out.println(counterName + " bán combo thành công: " + ticketA + " & " + ticketB);
                } else {
                    roomA.returnTicket(ticketA);
                    roomB.returnTicket(ticketB);
                    System.out.println(counterName + " bán combo thất bại");
                }
            }
        }
    }
}