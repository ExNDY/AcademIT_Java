package kks.oop.temperature.ui.temperature_frame;

import kks.oop.temperature.model.scale.Scale;

import java.awt.event.ActionListener;

public interface TemperatureView {
    Scale getScaleFrom();

    Scale getScaleTo();

    String getInputTemperature();

    void setConversionResult(double resultConversion);

    void addConvertButtonActionListener(ActionListener listener);

    void showErrorMessage(String message, String title);
}
