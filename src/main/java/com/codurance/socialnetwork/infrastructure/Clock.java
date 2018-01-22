package com.codurance.socialnetwork.infrastructure;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Clock {
    private static final String MINUTE = "minute";
    private static final String AGO = " ago";
    private static final String SECOND = "second";
    private static final String PLURAL = "s";

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public String ago(LocalDateTime from) {
        long secondsAgo = secondsAgo(from);

        if (areMinutes(secondsAgo)) {
            return minutesAgo(getMinutes(secondsAgo));
        }

        return secondsAgo(getSeconds(secondsAgo));
    }

    private String secondsAgo(long seconds) {
        return seconds + " " + SECOND + (seconds > 1 ? PLURAL : "") + AGO;
    }

    private long getSeconds(long seconds) {
        return seconds / 1000;
    }

    private String minutesAgo(long minutes) {
        return minutes + " " + MINUTE + (minutes > 1 ? PLURAL : "") + AGO;
    }

    private long getMinutes(long seconds) {
        return (seconds / 1000) / 60;
    }

    private boolean areMinutes(long seconds) {
        return seconds >= (1000 * 60);
    }

    private long secondsAgo(LocalDateTime from) {
        return now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli() - from.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
