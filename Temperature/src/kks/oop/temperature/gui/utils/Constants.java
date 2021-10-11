package kks.oop.temperature.gui.utils;

import kks.oop.temperature.gui.model.scale.Celsius;
import kks.oop.temperature.gui.model.scale.Fahrenheit;
import kks.oop.temperature.gui.model.scale.Kelvin;
import kks.oop.temperature.gui.model.scale.Scale;

public class Constants {
    public final static double ABSOLUTE_ZERO_CELSIUS = -273.15;
    public final static int ACCURACY = 2;

    public final static Scale[] getScales = {
            new Kelvin(),
            new Celsius(),
            new Fahrenheit()
    };
}
