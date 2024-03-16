package com.jorgepatrick;

import java.util.Objects;

public class TwentyFourHourTime {
    public String translateToSpoken(String writtenTime) {
        validateWrittenTime(writtenTime);
        return "";
    }

    private void validateWrittenTime(String writtenTime) {
        if (writtenTime == null) {
            throw new IllegalArgumentException("Time cannot be Null");
        }

        if (writtenTime.isEmpty()) {
            throw new IllegalArgumentException("Time cannot be Empty");
        }

        if (writtenTime.length() != 5) {
            throw new IllegalArgumentException("This is not an valid time on planet Earth");
        }

        String writtenHourStr = writtenTime.substring(0, 2);
        String colon = writtenTime.substring(2, 3);
        String writtenMinuteStr = writtenTime.substring(3, 5);

        if (!Objects.equals(colon, ":")) {
            throw new IllegalArgumentException("This is not an valid time on planet Earth");
        }
        
        if (isNotNumeric(writtenHourStr)) {
            throw new IllegalArgumentException("This is not an valid time on planet Earth");
        }

        if (isNotNumeric(writtenMinuteStr)) {
            throw new IllegalArgumentException("This is not an valid time on planet Earth");
        }

        int writtenHourIm = Integer.parseInt(writtenHourStr);
        int writtenMinuteInt = Integer.parseInt(writtenMinuteStr);

        if (writtenHourIm > 23 ||
            writtenHourIm < 0) {
            throw new IllegalArgumentException("This is not an valid time on planet Earth");
        }

        if (writtenMinuteInt > 59 ||
            writtenMinuteInt < 0) {
            throw new IllegalArgumentException("This is not an valid time on planet Earth");
        }
    }

    private boolean isNotNumeric(String possibleNumber) {
        if (possibleNumber == null) {
            return true;
        }
        try {
            int number = Integer.parseInt(possibleNumber);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

}
