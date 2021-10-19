package kks.oop.temperature.model.scale;

public class KelvinScale implements Scale {
    @Override
    public double convertToBaseUnit(double value) {
        return value;
    }

    @Override
    public double convertFromBaseUnit(double value) {
        return value;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}
