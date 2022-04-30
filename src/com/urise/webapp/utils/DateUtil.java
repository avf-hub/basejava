package com.urise.webapp.utils;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}
