package kks.oop.temperature;

import kks.oop.temperature.converter.TemperatureConverter;
import kks.oop.temperature.ui.temperature_converter.TemperatureController;
import kks.oop.temperature.ui.temperature_converter.TemperatureView;

import javax.swing.*;

import static kks.oop.temperature.utils.Constants.getScales;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        new TemperatureController(new TemperatureView("Temperature converter", getScales), new TemperatureConverter());
    }
}
