package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;

public class TitleAndPathComponent extends CarnetDeVoyageView {

    @FXML
    private TextField nameField;

    @FXML
    private TextField pathField;

    @FXML
    private Label errorLabel;

    public TitleAndPathComponent(Main main, Carnet carnet) {
        super(main, carnet);
    }

    @FXML
    void initialize() {
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.carnet.setName(newValue);
        });

        pathField.textProperty().addListener((observable, oldValue, newValue) -> {
            setFilePath(newValue + ".json.cdv");
        });

    }

    @FXML
    protected void choosePathButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir chemin pour le nouveau carnet de voyage");
        if (carnet.getFilePath() != null) {
            fileChooser.setInitialFileName(carnet.getFilePath().toString());
        }
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("carnet de voyage", ".json.cdv"));
        File f = fileChooser.showSaveDialog(main.getPrimaryStage());
        if (f == null) return;
        String path = f.getAbsolutePath();
        if (setFilePath(path)) {
            pathField.setText(formatPath(path));
        }
    }

    private String formatPath(String path) {
        return path.split(".json.csv")[0];
    }

    private boolean setFilePath(String pathStr) {
        pathStr = formatPath(pathStr);
        Path path = Path.of(pathStr);
        this.carnet.setFilePath(path);
        errorLabel.setOpacity(0.0);
        return true;
    }
}
