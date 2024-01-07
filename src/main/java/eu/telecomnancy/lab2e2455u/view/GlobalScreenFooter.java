package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class GlobalScreenFooter extends CarnetDeVoyageView {

    @FXML
    private Label descriptionLabel;

    @FXML
    private ListView<String> participantsList;

    public GlobalScreenFooter(Main main, Carnet carnet) {
        super(main, carnet);
    }


    @FXML
    void initialize() {
        descriptionLabel.setText(buildLabelText());
        participantsList.setItems(carnet.getParticipants());
    }

    private String buildLabelText() {
        return carnet.getStart().toString() + " - " + carnet.getEnd().toString() + "\nAuteur: " + carnet.getAuthor();
    }

    @FXML
    private void openEditScreen() {
    }
}
