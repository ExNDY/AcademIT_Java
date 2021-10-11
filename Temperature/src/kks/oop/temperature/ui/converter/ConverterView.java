package kks.oop.temperature.ui.converter;

import kks.oop.temperature.model.scale.Scale;

import java.awt.event.ActionListener;

public interface ConverterView {
    double getTemperature();

    void setResult(double value);

    Scale getScaleFrom();

    Scale getScaleTo();

    void addListener(ActionListener listener);
}
