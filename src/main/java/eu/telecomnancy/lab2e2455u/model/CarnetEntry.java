package eu.telecomnancy.lab2e2455u.model;

import java.time.LocalDate;

public class CarnetEntry {
    public LocalDate date;

    public String imagePath;

    public String title;

    public String description;

    public CarnetEntry(LocalDate date) {
        this.date = date;
    }

    public void copy(CarnetEntry entry) {
        if (!date.equals(entry.date)) return;
        imagePath = entry.imagePath;
        title = entry.title;
        description = entry.description;
    }
}
