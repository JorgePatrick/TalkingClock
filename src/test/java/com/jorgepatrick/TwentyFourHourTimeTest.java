package com.jorgepatrick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TwentyFourHourTimeTest {
    private TwentyFourHourTime twentyFourHourTime;

    @BeforeEach
    public void setup(){
        twentyFourHourTime = new TwentyFourHourTime();
    }

    @ParameterizedTest
    @MethodSource("provide24HourTimesForSpokenTime")
    public void speak (String writtenTime, String result) {
        assertEquals(result, twentyFourHourTime.getSpokenTime(writtenTime));
    }

    private static Stream<Arguments> provide24HourTimesForSpokenTime() {
        return Stream.of(
                Arguments.of("11:45", "fifteen to twelve pm"),
                Arguments.of("00:00", "twelve am"),
                Arguments.of("01:30", "one thirty am"),
                Arguments.of("12:05", "twelve oh five pm"),
                Arguments.of("14:01", "two oh one pm"),
                Arguments.of("20:29", "eight twenty nine pm"),
                Arguments.of("09:35", "twenty five to ten am"),
                Arguments.of("10:40", "twenty to eleven am"),
                Arguments.of("12:50", "ten to one pm"),
                Arguments.of("13:55", "five to two pm"),
                Arguments.of("21:00", "nine pm")
        );
    }
}
