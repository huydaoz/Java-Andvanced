package session8.ex1.factory;
import session8.ex1.device.Device;
import session8.ex1.device.AirConditioner;

public class AirConditionerFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        System.out.println("AirConditionerFactory: Đã tạo điều hòa mới.");
        return new AirConditioner();
    }
}