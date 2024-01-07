package eu.telecomnancy.lab2e2455u.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import eu.telecomnancy.lab2e2455u.utils.LocalDateAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class Carnet {

    private final ObservableList<String> participants;

    private final ObservableList<CarnetEntry> days;

    public String name;

    private String author;

    private LocalDate start;

    private LocalDate end;

    private transient Path filePath;

    public Carnet() {
        participants = FXCollections.observableArrayList();
        days = FXCollections.observableArrayList();
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
        if (end == null) {
            str += "date de fin vide! ";
        } else if (start != null && start.isAfter(end)) {
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

    public static Carnet fromJSON(String json, Path path) {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeHierarchyAdapter(LocalDate.class, new LocalDateAdapter());
        builder.registerTypeAdapter(new TypeToken<ObservableList<String>>() {
        }.getType(), new ListDeserializer());
        builder.registerTypeAdapter(new TypeToken<ObservableList<CarnetEntry>>() {
        }.getType(), new EntryDeserializer());
        final Gson gson = builder.create();
        Carnet carnet = gson.fromJson(json, Carnet.class);
        carnet.setFilePath(path);
        return carnet;
    }

    public static Carnet fromPath(Path path) {
        String fileContent;
        try {
            fileContent = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("unable to read file " + path + "!");
        }
        return fromJSON(fileContent, path);
    }

    public void save() {
        try {
            Files.write(filePath, asJSON().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static class ListDeserializer implements JsonDeserializer<ObservableList<String>> {
        public ObservableList<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            ObservableList<String> list = FXCollections.observableArrayList();
            list.addAll(new Gson().fromJson(json, String[].class));
            return list;
        }
    }

    private static class EntryDeserializer implements JsonDeserializer<ObservableList<CarnetEntry>> {
        public ObservableList<CarnetEntry> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            ObservableList<CarnetEntry> list = FXCollections.observableArrayList();
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            list.addAll(gson.fromJson(json, CarnetEntry[].class));
            return list;
        }
    }
}
