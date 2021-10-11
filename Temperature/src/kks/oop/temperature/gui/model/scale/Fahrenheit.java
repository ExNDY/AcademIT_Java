package kks.oop.temperature.gui.model.scale;

import static kks.oop.temperature.gui.utils.Constants.ABSOLUTE_ZERO_CELSIUS;

public class Fahrenheit implements Scale {
    @Override
    public double convertToBaseUnit(double value) {
        return (value - 32) * 5 / 9 + ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public double convertFromBaseUnit(double value) {
        return (value - ABSOLUTE_ZERO_CELSIUS) * 9 / 5 + 32;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
