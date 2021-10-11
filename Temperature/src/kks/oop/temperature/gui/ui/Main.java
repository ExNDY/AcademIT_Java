package kks.oop.temperature.gui.ui;

import kks.oop.temperature.gui.model.TemperatureConverter;
import kks.oop.temperature.gui.model.scale.Celsius;
import kks.oop.temperature.gui.model.scale.Fahrenheit;
import kks.oop.temperature.gui.model.scale.Kelvin;
import kks.oop.temperature.gui.model.scale.Scale;
import kks.oop.temperature.gui.ui.converter_frame.TemperatureConverterController;
import kks.oop.temperature.gui.ui.converter_frame.TemperatureConverterView;
import kks.oop.temperature.gui.ui.converter_frame.TemperatureConverterViewImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        runGUI();
    }

    private static void runGUI(){
        try {
            SwingUtilities.invokeLater(() -> {
                TemperatureConverter converter = new TemperatureConverter();
                TemperatureConverterView view = new TemperatureConverterViewImpl();
                TemperatureConverterController controller = new TemperatureConverterController(view, converter);
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
