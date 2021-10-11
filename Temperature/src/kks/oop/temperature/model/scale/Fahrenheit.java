package kks.oop.temperature.model.scale;

import static kks.oop.temperature.utils.Constants.ABSOLUTE_ZERO_CELSIUS;

public class Fahrenheit implements Scale {
    @Override
    public double convertToBaseUnit(double value) {
        return (value - 32) / 1.8 - ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public double convertFromBaseUnit(double value) {
        return (value + ABSOLUTE_ZERO_CELSIUS) * 1.8 + 32;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
