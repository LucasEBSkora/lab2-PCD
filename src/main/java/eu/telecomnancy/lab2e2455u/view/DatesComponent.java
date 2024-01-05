package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import eu.telecomnancy.lab2e2455u.utils.DDMMYYYYConverter;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;

public class DatesComponent {
    private final Main main;

    private final Carnet carnet;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    public DatesComponent(Main main, Carnet carnet) {
        this.main = main;
        this.carnet = carnet;
    }

    @FXML
    void initialize() {
        StringConverter<LocalDate> converter = new DDMMYYYYConverter();
        startDate.setConverter(converter);
        endDate.setConverter(converter);
    }

    @FXML
    void updateStartDate() {
        carnet.setStart(startDate.getValue());
    }

    @FXML
    void updateEndDate() {
        carnet.setEnd(endDate.getValue());
    }
}
