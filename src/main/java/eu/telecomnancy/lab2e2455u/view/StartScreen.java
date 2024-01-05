package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

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
    protected void showPickBook() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir Carnet de Voyage");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("carnet de voyage", ".json.cdv"));
        File f = fileChooser.showOpenDialog(main.getPrimaryStage());
        if (f == null) return;

    }
}