package kks.oop.temperature.gui.ui.converter_frame;

import kks.oop.temperature.gui.model.ScaleConverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static kks.oop.temperature.gui.utils.Constants.ACCURACY;

public class TemperatureConverterController implements ActionListener {
    private final TemperatureConverterView frame;

    private final ScaleConverter scaleConverter;

    public TemperatureConverterController(TemperatureConverterView frame, ScaleConverter scaleConverter){
        this.frame = frame;
        this.scaleConverter = scaleConverter;

        frame.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setValue(Math.round(
                scaleConverter.convert(frame.getTemperature(), frame.getFrom(), frame.getTo()) * Math.pow(10, ACCURACY)) / Math.pow(10, ACCURACY)
        );
    }
}
