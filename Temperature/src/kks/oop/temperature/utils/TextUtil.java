package kks.oop.temperature.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

public class TextUtil {
    public static double parseDecimal(String input) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        ParsePosition parsePosition = new ParsePosition(0);
        Number number = numberFormat.parse(input, parsePosition);

        if (parsePosition.getIndex() != input.length()) {
            throw new ParseException("Invalid input", parsePosition.getIndex());
        }

        return number.doubleValue();
    }
}
