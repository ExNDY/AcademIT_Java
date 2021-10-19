package kks.oop.temperature.model;

import kks.oop.temperature.model.scale.Scale;

public class TemperatureConverter {
    public double convert(double temperature, Scale fromScale, Scale toScale) {
        return toScale.convertFromBaseUnit(fromScale.convertToBaseUnit(temperature));
    }
}
