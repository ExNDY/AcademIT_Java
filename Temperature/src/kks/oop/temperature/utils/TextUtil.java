package kks.oop.temperature.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

public class TextUtil {
    public static double parseStringToDouble(String input) throws NumberFormatException {
        input = input.trim();

        if (input.equals("")) {
            throw new NumberFormatException("Entered value is EMPTY");
        }

        double inputValue;

        try {
            inputValue = TextUtil.parseDecimal(input);
        } catch (NumberFormatException | ParseException ex) {
            try {
                inputValue = Double.parseDouble(input.replaceAll(",", "."));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("error:" + e + " input value: " + input);
            }
        }

        return inputValue;
    }

    private static double parseDecimal(String input) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        ParsePosition parsePosition = new ParsePosition(0);
        Number number = numberFormat.parse(input, parsePosition);

        if (parsePosition.getIndex() != input.length()) {
            throw new ParseException("Invalid input", parsePosition.getIndex());
        }

        return number.doubleValue();
    }

    public static double roundToNearestHundred(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
