package com.codurance.socialnetwork.infrastructure;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Clock {
    private static final String MINUTE = "minute";
    private static final String AGO = " ago";
    private static final String SECOND = "second";
    private static final String PLURAL = "s";

    public LocalDateTime now() {
        throw new UnsupportedOperationException();
    }

    public String ago(LocalDateTime from) {
        long timediff = now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli() - from.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();

        if (timediff >= (1000 * 60)) {
            long minutes = (timediff / 1000) / 60;
            return minutes + " " + MINUTE + (minutes > 1 ? PLURAL : "") + AGO;
        }

        long seconds = (timediff / 1000);
        return (timediff / 1000) + " " + SECOND + (seconds > 1 ? PLURAL : "") + AGO;
    }
}
