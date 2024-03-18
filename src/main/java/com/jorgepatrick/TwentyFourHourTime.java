package com.jorgepatrick;

import java.util.Objects;

public class TwentyFourHourTime {
    private int writtenHourInt;
    private int writtenMinuteInt;
    private boolean isTimePM;

    public String getSpokenTime(String writtenTime) {
        validateWrittenTime(writtenTime);
        return getSpokenTime();
    }

    private String getSpokenTime() {
        String spokenHour = getSpokenHour();
        String amPm = getAmPm();

        if (writtenMinuteInt == 0) {
            return spokenHour + " " + amPm;
        }

        String spokenMinute = getSpokenMinute();
        String spokenTime = "";
        if (speakTimeBackwards()) {
            spokenTime = spokenMinute + " to " + spokenHour + " " +amPm;
        } else {
            spokenTime = spokenHour + " " + spokenMinute + " " +amPm;
        }

        return spokenTime;
    }

    private String getSpokenHour() {
        int spokenHourInt = writtenHourInt;
        if (speakTimeBackwards()) {
            spokenHourInt = spokenHourInt + 1;
        }

        isTimePM = spokenHourInt > 11;

        if (isTimePM) {
            spokenHourInt = spokenHourInt - 12;
        }

        String spokenHour = "";
        for (SpokenHour hour : SpokenHour.values()) {
            if (spokenHourInt == hour.getSpokenHour()) {
                spokenHour = hour.name();
            }
        }
        return spokenHour;
    }

    private String getSpokenMinute() {
        String spokenMinute = "";
        int spokenMinuteInt = writtenMinuteInt;
        if (speakTimeBackwards()) {
            spokenMinuteInt = 60 - spokenMinuteInt;
        }

        int minuteTens = spokenMinuteInt;
        int minuteOnes = spokenMinuteInt % 10;
        if (isMinutesTwoWords(spokenMinuteInt)) {
            minuteTens = (minuteTens - (minuteOnes));
        }

        if (writtenMinuteInt < 10) {
            spokenMinute += "oh ";
        }

        for (SpokenMinute minute : SpokenMinute.values()) {
            if (minuteTens == minute.getSpokenMinute()) {
                spokenMinute += minute.name();
            }
        }

        if (isMinutesTwoWords(spokenMinuteInt)) {
            for (SpokenMinute minute : SpokenMinute.values()) {
                if (minuteOnes == minute.getSpokenMinute()) {
                    spokenMinute += " " + minute.name();
                }
            }
        }

        return spokenMinute;
    }

    private boolean isMinutesTwoWords(int spokenMinute) {
        return (spokenMinute > 20 && spokenMinute % 10 != 0);
    }

    private boolean speakTimeBackwards() {
        return (writtenMinuteInt > 30 && writtenMinuteInt % 5 == 0);
    }

    private String getAmPm() {
        if (isTimePM) {
            return "pm";
        }
        return "am";
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

        writtenHourInt = Integer.parseInt(writtenHourStr);
        writtenMinuteInt = Integer.parseInt(writtenMinuteStr);

        if (writtenHourInt > 23 ||
            writtenHourInt < 0) {
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
