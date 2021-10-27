package kks.oop.temperature;

import kks.oop.temperature.model.TemperatureConverter;
import kks.oop.temperature.controller.TemperatureController;
import kks.oop.temperature.model.scale.CelsiusScale;
import kks.oop.temperature.model.scale.FahrenheitScale;
import kks.oop.temperature.model.scale.KelvinScale;
import kks.oop.temperature.model.scale.Scale;
import kks.oop.temperature.ui.temperature_frame.TemperatureView;
import kks.oop.temperature.ui.temperature_frame.TemperatureViewImpl;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = {
                new KelvinScale(),
                new CelsiusScale(),
                new FahrenheitScale()
        };

        createAndShowGUI(scales);
    }

    private static void createAndShowGUI(Scale[] scales) {
        TemperatureView view = new TemperatureViewImpl("Temperature converter", scales);
        new TemperatureController(view, new TemperatureConverter());
    }
}
