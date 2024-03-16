package com.jorgepatrick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TakingClockTest {
    private TakingClock takingClock;

    @BeforeEach
    public void setup(){
        takingClock = new TakingClock();
    }

    @ParameterizedTest
    @MethodSource("provide24HourTimesForSpokenTime")
    public void talk (String hourTime, String result) {
        assertEquals(result, takingClock.talkTime(hourTime));
    }

    private static Stream<Arguments> provide24HourTimesForSpokenTime() {
        return Stream.of(
                Arguments.of("00:00", "It's twelve am"),
                Arguments.of("01:30", "It's twelve am"),
                Arguments.of("12:05", "It's twelve am"),
                Arguments.of("14:01", "It's twelve am"),
                Arguments.of("20:29", "It's twelve am"),
                Arguments.of("21:00", "It's twelve am"),
                Arguments.of("00:00", "It's twelve am"),
                Arguments.of("00:00", "It's twelve am"),
                Arguments.of("00:00", "It's twelve am")
        );
    }

    @ParameterizedTest
    @MethodSource("provide24HourTimesForInputValidation")
    public void timeValidation (String hourTime, String exceptionMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            takingClock.talkTime(hourTime);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contentEquals(exceptionMessage));
    }

    private static Stream<Arguments> provide24HourTimesForInputValidation() {
        return Stream.of(
                Arguments.of(null, "This isnt an hour on planet Earth"),
                Arguments.of("null", "This isnt an hour on planet Earth"),
                Arguments.of("24:00", "This isnt an hour on planet Earth"),
                Arguments.of("15:60", "This isnt an hour on planet Earth"),
                Arguments.of("15-60", "This isnt an hour on planet Earth"),
                Arguments.of("1560", "This isnt an hour on planet Earth"),
                Arguments.of("15::60", "This isnt an hour on planet Earth")
        );
    }

}
