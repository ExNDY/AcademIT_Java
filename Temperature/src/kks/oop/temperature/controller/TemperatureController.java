package kks.oop.temperature.controller;

import kks.oop.temperature.model.TemperatureConverter;
import kks.oop.temperature.model.scale.Scale;
import kks.oop.temperature.ui.TemperatureView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureController implements ActionListener {
    private final TemperatureView view;
    private final TemperatureConverter converter;

    public TemperatureController(TemperatureView view, TemperatureConverter converter) {
        this.view = view;
        this.converter = converter;

        view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (view.getSwapButton().equals(source)) {
            view.swapScales();
        }

        if (view.getConvertButton().equals(source)) {
            convertTemperature(view.getTemperature(), view.getScaleFrom(), view.getScaleTo());
        }
    }

    private void convertTemperature(double temperature, Scale fromScale, Scale toScale) {
        view.setConversionResult(converter.convert(temperature, fromScale, toScale));
    }
}