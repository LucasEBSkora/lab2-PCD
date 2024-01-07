package eu.telecomnancy.lab2e2455u.model;

import java.nio.file.Path;
import java.time.LocalDate;

public class CarnetEntry {
    public LocalDate date;

    public Path imagePath;

    public String title;

    public String description;

    public CarnetEntry(LocalDate date) {
        this.date = date;
        title = "";
        description = "";
    }
}
