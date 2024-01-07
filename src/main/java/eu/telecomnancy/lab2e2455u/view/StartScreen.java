package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class StartScreen {
    private final Main main;

    public StartScreen(Main main) {
        this.main = main;
    }

    @FXML
    protected void ChangeToCreateScreen() throws IOException {
        main.push(main.makeCreateScreen());
        main.show();
    }

    @FXML
    protected void showPickBook() throws IOException {
        Path path = getPathFromUser();
        if (path == null) {
            main.showWarning("aucune fichier choisi!");
            return;
        }

        Carnet carnet = Carnet.fromPath(path);

        if (carnet == null) {
            main.showWarning("problème au ouvrir carnet");
        }

        main.push(main.makeGlobalScreen(carnet));
        main.show();
    }

    private Path getPathFromUser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir Carnet de Voyage");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("carnet de voyage", ".json.cdv"));
        File f = fileChooser.showOpenDialog(main.getPrimaryStage());
        if (f == null) {
            return null;
        }
        return Path.of(f.getAbsolutePath());
    }

}