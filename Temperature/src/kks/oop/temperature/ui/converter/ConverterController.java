package kks.oop.temperature.ui.converter;

import kks.oop.temperature.converter.TemperatureConverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterController implements ActionListener {
    private final ConverterView view;

    private final TemperatureConverter converter;

    public ConverterController(ConverterView view, TemperatureConverter converter) {
        this.view = view;
        this.converter = converter;

        view.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setResult(Math.round(
                converter.convert(view.getTemperature(), view.getScaleFrom(), view.getScaleTo()) * 100.0 / 100.0)
        );
    }
}
