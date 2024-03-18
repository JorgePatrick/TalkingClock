package com.jorgepatrick;

public class TakingClock {
    private final TwentyFourHourTime twentyFourHourtime;

    public TakingClock(TwentyFourHourTime twentyFourHourTime) {
        this.twentyFourHourtime = twentyFourHourTime;
    }

    public String talkTime(String writtenTime) {
        String spokenTime = twentyFourHourtime.getSpokenTime(writtenTime);
        return "It's " + spokenTime;
    }
}
