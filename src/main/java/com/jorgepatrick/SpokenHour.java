package com.jorgepatrick;

public enum SpokenHour {
    twelve (0),
    one (1),
    two (2),
    three (3),
    four (4),
    five (5),
    six (6),
    seven (7),
    eight (8),
    nine (9),
    ten (10),
    eleven (11);

    private final int spokenHour;

    SpokenHour(int spokenHour) { this.spokenHour = spokenHour; }

    public int getSpokenHour() { return this.spokenHour; }
}
