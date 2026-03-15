package session6.ex5;

public class Ticket {

    private String ticketId;
    private String roomName;

    private boolean isSold = false;
    private boolean isHeld = false;

    private long holdExpiryTime;
    private boolean isVIP;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean isSold() {
        return isSold;
    }

    public boolean isHeld() {
        return isHeld;
    }

    public void hold(boolean vip, long expiry) {
        this.isHeld = true;
        this.isVIP = vip;
        this.holdExpiryTime = expiry;
    }

    public void sell() {
        this.isSold = true;
        this.isHeld = false;
    }

    public void release() {
        this.isHeld = false;
    }

    public long getHoldExpiryTime() {
        return holdExpiryTime;
    }
}