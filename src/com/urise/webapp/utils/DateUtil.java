package com.urise.webapp.utils;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static LocalDate NOW = LocalDate.now();

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}
