package kks.oop.temperature;

import kks.oop.temperature.converter.TemperatureConverter;
import kks.oop.temperature.ui.converter.ConverterController;
import kks.oop.temperature.ui.converter.ConverterView;
import kks.oop.temperature.ui.converter.ConverterViewImpl;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        TemperatureConverter converter = new TemperatureConverter();
        ConverterView view = new ConverterViewImpl();
        ConverterController controller = new ConverterController(view, converter);
    }
}
