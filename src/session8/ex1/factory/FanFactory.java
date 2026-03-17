package session8.ex1.factory;
import session8.ex1.device.Device;
import session8.ex1.device.Fan;

public class FanFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        System.out.println("FanFactory: Đã tạo quạt mới.");
        return new Fan();
    }
}