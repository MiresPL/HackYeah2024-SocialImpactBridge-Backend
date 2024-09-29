package com.mires.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class DateHelper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy, HH:mm:ss");
    private static final ZoneId POLISH_ZONE = ZoneId.of("Poland");

    public static String getDate(long time, boolean now) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli((now ? System.currentTimeMillis() : 0L) + time), POLISH_ZONE).format(DATE_TIME_FORMATTER);
    }

    public static String now() {
        return LocalDateTime.now(POLISH_ZONE).format(DATE_TIME_FORMATTER);
    }

    public static String durationToString(long time, boolean fromNow) {
        if (fromNow) time -= System.currentTimeMillis();
        if (time < 1L) return "<1s";
        long months = TimeUnit.MILLISECONDS.toDays(time) / 30L;
        long days = TimeUnit.MILLISECONDS.toDays(time) % 30L;
        long hours = TimeUnit.MILLISECONDS.toHours(time) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(time));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time));
        StringBuilder stringBuilder = new StringBuilder();
        if (months > 0L) stringBuilder.append(months).append("msc").append(" ");
        if (days > 0L) stringBuilder.append(days).append("d").append(" ");
        if (hours > 0L) stringBuilder.append(hours).append("h").append(" ");
        if (minutes > 0L) stringBuilder.append(minutes).append("m").append(" ");
        if (seconds > 0L) stringBuilder.append(seconds).append("s");
        return (stringBuilder.length() > 0) ? stringBuilder.toString().trim() : (time + "ms");
    }

    public static long durationFromString(String string, boolean fromNow) {
        if (string == null || string.isEmpty()) return 0L;
        StringBuilder stringBuilder = new StringBuilder();
        long time = fromNow ? System.currentTimeMillis() : 0L;
        for (char character : string.toCharArray()) {
            if (Character.isDigit(character)) {
                stringBuilder.append(character);
            } else {
                int amount = Integer.parseInt(stringBuilder.toString());
                switch (character) {
                    case 'd':
                        time += amount * 86400000L;
                        break;
                    case 'h':
                        time += amount * 3600000L;
                        break;
                    case 'm':
                        time += amount * 60000L;
                        break;
                    case 's':
                        time += amount * 1000L;
                        break;
                }
                stringBuilder = new StringBuilder();
            }
        }
        return time;
    }
}
