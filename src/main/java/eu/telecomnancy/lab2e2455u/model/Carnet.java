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
import java.util.Optional;

public class Carnet {

    private final ObservableList<String> participants;

    public ObservableList<CarnetEntry> getDays() {
        return days;
    }

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
        checkDayArray();
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
        checkDayArray();
    }

    private void checkDayArray() {
        if (start == null || end == null) {
            return;
        }
        if (days.isEmpty()) {
            for (LocalDate date = start; end.isAfter(date); date = date.plusDays(1)) {
                days.add(new CarnetEntry(date));
            }
        }
        LocalDate oldStartDate = days.get(0).date;
        if (start.isBefore(oldStartDate)) {
            int index = 0;
            for (LocalDate date = start; oldStartDate.isAfter(date); date = date.plusDays(1)) {
                days.add(index++, new CarnetEntry(date));
            }
        }

        LocalDate oldEndDate = days.get(days.size() - 1).date;
        if (end.isAfter(oldEndDate)) {
            for (LocalDate date = oldEndDate; end.isAfter(date); date = date.plusDays(1)) {
                days.add(new CarnetEntry(date));
            }
        }
    }

    public CarnetEntry getEntry(LocalDate date) {
        if (isValidDate(date)) {
            return null;
        }
        Optional<CarnetEntry> entry = days.stream().filter(e -> date.equals(e.date)).findAny();
        return entry.orElse(null);
    }


    public void setEntry(CarnetEntry entry) {
        if (isValidDate(entry.date)) {
            CarnetEntry originalEntry = days.stream().filter(e -> entry.date == e.date).findAny().orElse(null);
            if (originalEntry == null) {
                return;
            }
            int index = days.indexOf(originalEntry);
            days.set(index, entry);
        }
    }

    private boolean isValidDate(LocalDate date) {
        return date.isBefore(start) || date.isAfter(end);
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
