package com.jorgepatrick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TakingClockTest {
    private TakingClock takingClock;
    private TwentyFourHourTime twentyFourHourTime;

    @BeforeEach
    public void setup(){
        twentyFourHourTime = new TwentyFourHourTime();
        takingClock = new TakingClock(twentyFourHourTime);
    }

    @ParameterizedTest
    @MethodSource("provide24HourTimesForSpokenTime")
    public void talk (String writtenTime, String result) {
        assertEquals(result, takingClock.talkTime(writtenTime));
    }

    private static Stream<Arguments> provide24HourTimesForSpokenTime() {
        return Stream.of(
                Arguments.of("11:45", "It's fifteen to twelve pm"),
                Arguments.of("00:00", "It's twelve am"),
                Arguments.of("01:30", "It's one thirty am"),
                Arguments.of("12:05", "It's twelve oh five pm"),
                Arguments.of("14:01", "It's two oh one pm"),
                Arguments.of("20:29", "It's eight twenty nine pm"),
                Arguments.of("09:35", "It's twenty five to ten am"),
                Arguments.of("10:40", "It's twenty to eleven am"),
                Arguments.of("12:50", "It's ten to one pm"),
                Arguments.of("13:55", "It's five to two pm"),
                Arguments.of("21:00", "It's nine pm")
        );
    }

    @ParameterizedTest
    @MethodSource("provide24HourTimesForInputValidation")
    public void timeValidation (String writtenTime, String exceptionMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            takingClock.talkTime(writtenTime);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contentEquals(exceptionMessage));
    }

    private static Stream<Arguments> provide24HourTimesForInputValidation() {
        return Stream.of(
                Arguments.of(null, "Time cannot be Null"),
                Arguments.of("", "Time cannot be Empty"),
                Arguments.of("null", "This is not an valid time on planet Earth"),
                Arguments.of("24:00", "This is not an valid time on planet Earth"),
                Arguments.of("15:60", "This is not an valid time on planet Earth"),
                Arguments.of("15-60", "This is not an valid time on planet Earth"),
                Arguments.of("1560", "This is not an valid time on planet Earth"),
                Arguments.of("15::60", "This is not an valid time on planet Earth")
        );
    }

}
