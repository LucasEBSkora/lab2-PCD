package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import eu.telecomnancy.lab2e2455u.model.CarnetEntry;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;

public class EntryScreenPhoto extends EntryScreenView {

    @FXML
    ImageView image;

    public EntryScreenPhoto(Main main, Carnet carnet, CarnetEntry entry, CarnetEntry tempEntry) {
        super(main, carnet, entry, tempEntry);
    }

    @FXML
    void initialize() {
        String path = null;
        if (entry.imagePath == null) {
            path = Objects.requireNonNull(EntryCellFactory.class.getResource("/eu/telecomnancy/lab2e2455u/placeholder.png")).toExternalForm();
        } else {
            path = entry.imagePath;
        }
        setImage(path);
    }

    @FXML
    void chooseImage() {
        String path = getPathFromUser();
        if (path == null) {
            main.showWarning("aucune image choisi");
            return;
        }
        try {
            setImage(path);
        } catch (IllegalArgumentException e) {
            main.showWarning("fichier " + path + " n'est pas une image!");
            return;
        }

        tempEntry.imagePath = path;
    }

    private void setImage(String path) throws IllegalArgumentException {
        image.setImage(new Image(path));
    }

    private String getPathFromUser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir image du jour");
        File f = fileChooser.showOpenDialog(main.getPrimaryStage());
        if (f == null) {
            return null;
        }

        return f.toURI().toString();
    }
}
