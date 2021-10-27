package kks.oop.temperature.controller;

import kks.oop.temperature.model.TemperatureConverter;
import kks.oop.temperature.model.scale.Scale;
import kks.oop.temperature.ui.temperature_frame.TemperatureView;
import kks.oop.temperature.utils.TextUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureController implements ActionListener {
    private final TemperatureView view;
    private final TemperatureConverter converter;

    public TemperatureController(TemperatureView view, TemperatureConverter converter) {
        this.view = view;
        this.converter = converter;

        view.addConvertButtonActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        convertTemperature(parseValue(view.getInputTemperature()), view.getScaleFrom(), view.getScaleTo());
    }

    private void convertTemperature(double temperature, Scale fromScale, Scale toScale) {
        view.setConversionResult(converter.convert(temperature, fromScale, toScale));
    }

    private double parseValue(String input) {
        try {
            return TextUtil.parseStringToDouble(input);
        } catch (NumberFormatException exception) {
            view.showErrorMessage("Check the entered value: \"" + input + "\".", "ERROR: Wrong input format");
        }

        return 0.0;
    }
}