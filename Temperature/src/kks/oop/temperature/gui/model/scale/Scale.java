package kks.oop.temperature.gui.model.scale;

public interface Scale {
    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double value);
}
