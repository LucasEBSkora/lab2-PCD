package eu.telecomnancy.lab2e2455u.utils;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DDMMYYYYConverter extends StringConverter<LocalDate> {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String toString(LocalDate date) {
        if (date != null) {
            return dateFormatter.format(date);
        } else {
            return "";
        }
    }

    @Override
    public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dateFormatter);
        } else {
            return null;
        }
    }
}

