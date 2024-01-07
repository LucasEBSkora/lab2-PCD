package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import eu.telecomnancy.lab2e2455u.model.CarnetEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class EntryScreenHeader extends EntryScreenView {

    @FXML
    private Label todayLabel;

    @FXML
    private Button yesterdayButton;

    @FXML
    private Button tomorrowButton;

    @FXML
    private TextField titleField;

    public EntryScreenHeader(Main main, Carnet carnet, CarnetEntry entry, CarnetEntry tempEntry) {
        super(main, carnet, entry, tempEntry);
    }


    @FXML
    void initialize() {
        todayLabel.setText(entry.date.toString());
        LocalDate yesterday = entry.date.minusDays(1);
        if (yesterday.isBefore(carnet.getStart())) {
            yesterdayButton.setDisable(true);
        }
        yesterdayButton.setText(yesterday.toString());
        LocalDate tomorrow = entry.date.plusDays(1);
        tomorrowButton.setText(tomorrow.toString());
        if (tomorrow.isAfter(carnet.getEnd())) {
            tomorrowButton.setDisable(true);
        }

        titleField.textProperty().addListener((observable, oldValue, newValue) -> this.tempEntry.title = newValue);
    }

    @FXML
    void backOneDay() throws IOException {
        main.pop();
        main.push(main.makeEntryScreen(carnet, carnet.getEntry(entry.date.minusDays(1))));
        main.show();
    }

    @FXML
    void forwardOneDay() throws IOException {
        main.pop();
        main.push(main.makeEntryScreen(carnet, carnet.getEntry(entry.date.plusDays(1))));
        main.show();
    }

}
