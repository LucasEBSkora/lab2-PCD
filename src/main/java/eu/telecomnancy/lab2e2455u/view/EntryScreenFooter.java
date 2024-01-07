package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import eu.telecomnancy.lab2e2455u.model.CarnetEntry;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class EntryScreenFooter extends EntryScreenView {

    @FXML
    private TextArea description;

    public EntryScreenFooter(Main main, Carnet carnet, CarnetEntry entry, CarnetEntry tempEntry) {
        super(main, carnet, entry, tempEntry);
    }

    @FXML
    void initialize() {
        description.setText(tempEntry.description);
        description.textProperty().addListener((observable, oldValue, newValue) -> this.tempEntry.description = newValue);
    }

    @FXML
    void returnGlobal() {
        main.pop();
        main.show();
    }
    @FXML
    void saveEntry() {
        entry.copy(tempEntry);
        carnet.save();
        main.pop();
        main.show();
    }
}
