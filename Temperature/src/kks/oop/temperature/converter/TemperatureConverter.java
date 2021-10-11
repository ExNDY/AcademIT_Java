package kks.oop.temperature.converter;

import kks.oop.temperature.model.scale.Scale;

public class TemperatureConverter {
    public double convert(double value, Scale from, Scale to) {
        return to.convertFromBaseUnit(from.convertToBaseUnit(value));
    }
}
