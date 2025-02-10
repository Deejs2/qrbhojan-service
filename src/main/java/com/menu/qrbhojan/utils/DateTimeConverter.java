package com.menu.qrbhojan.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
    private DateTimeConverter() {}

    // Convert this type of format : 2025-02-09 18:22:02.296665 to Feb 09, 2025 6:22:02 PM
    public static String convertDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm:ss a");
        return dateTime.format(formatter);
    }
}