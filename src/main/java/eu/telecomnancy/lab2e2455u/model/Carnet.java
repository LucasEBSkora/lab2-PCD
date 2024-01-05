package eu.telecomnancy.lab2e2455u.model;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import eu.telecomnancy.lab2e2455u.utils.DDMMYYYYConverter;
import eu.telecomnancy.lab2e2455u.utils.LocalDateAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;

public class Carnet {
    @Expose
    public String name;
    @Expose
    private String author;
    @Expose
    private final ObservableList<String> participants;
    @Expose
    private LocalDate start;
    @Expose
    private LocalDate end;

    private transient Path filePath;

    public Carnet() {
        participants = FXCollections.observableArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public void addParticipant(String participant) {
        if (participants.contains(participant)) return;
        participants.add(participant);
    }

    public void removeParticipant(String participant) {
        participants.remove(participant);
    }

    public ObservableList<String> getParticipants() {
        return participants;
    }

    public String problem() {
        String str = "";
        if (name == null || name.isEmpty()) {
            str += "nom du carnet vide! ";
        }
        if (author == null || author.isEmpty()) {
            str += "nom du auteur vide! ";
        }
        if (start == null) {
                str += "date de debut vide! ";
        }
        if  (end == null) {
            str += "date de fin vide! ";
        } else if (start != null && start.isAfter(end))
        {
            str += "la date de fin est avant le debut! ";
        }
        if (filePath == null) {
            str += "chemin du fichier vide!";
        }
        return str;
    }

    public String asJSON() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        final Gson gson = builder.create();

        return gson.toJson(this);
    }
    public void save() {
        try {
            Files.write(filePath, asJSON().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
