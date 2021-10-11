package kks.oop.temperature.utils;

import kks.oop.temperature.model.scale.Celsius;
import kks.oop.temperature.model.scale.Fahrenheit;
import kks.oop.temperature.model.scale.Kelvin;
import kks.oop.temperature.model.scale.Scale;

public class Constants {
    public final static double ABSOLUTE_ZERO_CELSIUS = -273.15;

    public final static Scale[] getScales = {
            new Kelvin(),
            new Celsius(),
            new Fahrenheit()
    };
}
