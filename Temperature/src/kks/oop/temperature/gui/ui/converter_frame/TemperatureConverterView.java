package kks.oop.temperature.gui.ui.converter_frame;

import kks.oop.temperature.gui.model.scale.Scale;

import java.awt.event.ActionListener;

public interface TemperatureConverterView {
    double getTemperature();

    void setValue(double value);

    Scale getFrom();

    Scale getTo();

    void addListener(ActionListener listener);
}
