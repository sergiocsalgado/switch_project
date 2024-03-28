package org.switch2022.project.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateManagement {
    private DateManagement() {
    }

    public static LocalDate toLocalDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date != null ? LocalDate.parse(date, format) : null;
    }
}
