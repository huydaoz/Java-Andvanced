package session8.ex1.factory;
import session8.ex1.device.Device;
import session8.ex1.device.Light;

public class LightFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        System.out.println("LightFactory: Đã tạo đèn mới.");
        return new Light();
    }
}