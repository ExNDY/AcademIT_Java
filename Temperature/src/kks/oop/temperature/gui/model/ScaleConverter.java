package kks.oop.temperature.gui.model;

import kks.oop.temperature.gui.model.scale.Scale;

public interface ScaleConverter {
    double convert(double value, Scale from, Scale to);
}
