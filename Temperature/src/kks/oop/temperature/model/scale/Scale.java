package kks.oop.temperature.model.scale;

public interface Scale {
    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double value);
}
