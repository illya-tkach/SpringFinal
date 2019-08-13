package net.springapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServiceUtil {
    public static LocalDateTime convertToDateAndTime(String stringToSplit, String regex, String datePatter) {
        String[] dateAndTimeString = stringToSplit.split(regex);
        String date = dateAndTimeString[0];
        String time = dateAndTimeString[1];
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern(datePatter);
        LocalDate localDate = LocalDate.parse(date, dateformatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        return LocalDateTime.of(localDate, localTime);
    }

}
