package kks.oop.temperature.ui.temperature_converter;

import kks.oop.temperature.converter.TemperatureConverter;
import kks.oop.temperature.utils.TextUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class TemperatureController implements ActionListener {
    private final TemperatureView view;
    private final TemperatureConverter converter;

    public TemperatureController(TemperatureView view, TemperatureConverter converter) {
        this.view = view;
        this.converter = converter;

        bindButtons();
    }

    private void bindButtons() {
        view.getSwapButton().addActionListener(this);
        view.getConvertButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (view.getSwapButton().equals(source)) {
            swapScales();
        }
        if (view.getConvertButton().equals(source)) {
            convertTemperature();
        }
    }

    private void convertTemperature() {
        view.getOutputTextField().setText(String.valueOf(Math.round(
                converter.convert(parseInputValue(), view.getScaleFrom(), view.getScaleTo()) * 100.0 / 100.0)));
    }

    private double parseInputValue() {
        String input = view.getInputTextField().getText().trim();

        double inputValue = 0.0;

        if (input.equals("")) {
            view.getInputTextField().setText(String.valueOf(inputValue));
        } else {
            try {
                inputValue = TextUtil.parseDecimal(input);

                view.getInputTextField().setText(input);
            } catch (NumberFormatException | ParseException ex) {
                try {
                    inputValue = Double.parseDouble(input.replaceAll(",", "."));

                    view.getInputTextField().setText(String.valueOf(inputValue));
                } catch (NumberFormatException e) {
                    view.getOutputTextField().setText("");

                    JOptionPane.showMessageDialog(null, "Check the entered value.\n Error message: \n" + e, "ERROR: Wrong input format", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        return inputValue;
    }

    private void swapScales() {
        if (view.getScaleFrom().equals(view.getScaleTo())) {
            return;
        }

        int fromIndex = view.getScaleFromComboBox().getSelectedIndex();
        view.getScaleFromComboBox().setSelectedIndex(view.getScaleToComboBox().getSelectedIndex());
        view.getScaleToComboBox().setSelectedIndex(fromIndex);
    }
}