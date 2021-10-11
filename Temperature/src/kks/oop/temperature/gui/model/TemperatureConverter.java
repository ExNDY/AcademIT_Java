package kks.oop.temperature.gui.model;

import kks.oop.temperature.gui.model.scale.Scale;

public class TemperatureConverter implements ScaleConverter {
    public double convert(double value, Scale from, Scale to) {
        return to.convertFromBaseUnit(from.convertToBaseUnit(value));
    }
}
