package session8.ex1.connection;

public class HardwareConnection {

    private static HardwareConnection instance;

    private HardwareConnection() {
        System.out.println("HardwareConnection: Đã kết nối phần cứng.");
    }

    public static HardwareConnection getInstance() {
        if (instance == null) {
            instance = new HardwareConnection();
        }
        return instance;
    }

    public void connect() {
        // đã connect ở constructor
    }

    public void disconnect() {
        System.out.println("HardwareConnection: Ngắt kết nối.");
    }
}