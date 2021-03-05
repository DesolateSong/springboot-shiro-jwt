package com.suxs.common.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static final String DEFUALT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFUALT_DATE_PATTERN = "yyyy-MM-dd";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static Date parseDate(String arg) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(arg, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static LocalDate parseLocalDate(String arg) {
        return parseLocalDate(arg, DEFUALT_DATE_PATTERN);
    }

    public static LocalDate parseLocalDate(String arg, String pattern) {
        return LocalDate.parse(arg, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String arg) {
        return parseLocalDateTime(arg, DEFUALT_DATETIME_PATTERN);
    }

    public static LocalDateTime parseLocalDateTime(String arg, String pattern) {
        return LocalDateTime.parse(arg, DateTimeFormatter.ofPattern(pattern));
    }

    public static String formateLocalDate(LocalDate localDate) {
        return formateLocalDate(localDate, DEFUALT_DATE_PATTERN);
    }

    public static String formateLocalDate(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formateLocalDateTime(LocalDateTime localDateTime) {
        return formateLocalDateTime(localDateTime, DEFUALT_DATETIME_PATTERN);
    }

    public static String formateLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
