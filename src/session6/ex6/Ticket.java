package session6.ex6;

public class Ticket {

    private String id;
    private boolean sold;

    public Ticket(String id) {
        this.id = id;
    }

    public synchronized boolean sell() {
        if (!sold) {
            sold = true;
            return true;
        }
        return false;
    }

    public boolean isSold() {
        return sold;
    }

    public String getId() {
        return id;
    }
}