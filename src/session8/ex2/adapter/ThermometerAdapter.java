package session8.ex2.adapter;

import session8.ex2.sensor.OldThermometer;
import session8.ex2.sensor.TemperatureSensor;

public class ThermometerAdapter implements TemperatureSensor {

    private OldThermometer oldThermometer;

    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }

    @Override
    public double getTemperatureCelsius() {
        int f = oldThermometer.getTemperatureFahrenheit();
        return (f - 32) * 5.0 / 9;
    }
}