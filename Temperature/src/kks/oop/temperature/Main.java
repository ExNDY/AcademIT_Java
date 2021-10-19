package kks.oop.temperature;

import kks.oop.temperature.model.TemperatureConverter;
import kks.oop.temperature.controller.TemperatureController;
import kks.oop.temperature.model.scale.CelsiusScale;
import kks.oop.temperature.model.scale.FahrenheitScale;
import kks.oop.temperature.model.scale.KelvinScale;
import kks.oop.temperature.model.scale.Scale;
import kks.oop.temperature.ui.TemperatureView;

public class Main {
    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        TemperatureView view = new TemperatureView("Temperature converter", getScales);
        new TemperatureController(view, new TemperatureConverter());
    }

    private static final Scale[] getScales = {
            new KelvinScale(),
            new CelsiusScale(),
            new FahrenheitScale()
    };
}
