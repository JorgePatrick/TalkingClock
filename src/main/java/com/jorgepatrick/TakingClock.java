package com.jorgepatrick;

import java.sql.Time;

public class TakingClock {
    private final TwentyFourHourTime twentyFourHourtime;

    public TakingClock(TwentyFourHourTime twentyFourHourtime) {
        this.twentyFourHourtime = twentyFourHourtime;
    }

    public String talkTime(String writenTime) {
        String spokenTime = twentyFourHourtime.translateToSpoken(writenTime);
        return "It's " + spokenTime;
    }
}
