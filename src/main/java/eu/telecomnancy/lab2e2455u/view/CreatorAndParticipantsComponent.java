package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CreatorAndParticipantsComponent {
    private final Main main;

    private final Carnet carnet;

    @FXML
    private TextField creatorField;

    @FXML
    private TextField memberField;

    @FXML
    private ListView<String> participantsList;

    public CreatorAndParticipantsComponent(Main main, Carnet carnet) {
        this.main = main;
        this.carnet = carnet;
    }

    @FXML
    void initialize() {
        participantsList.setItems(carnet.getParticipants());

        creatorField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.carnet.setAuthor(newValue);
        });

    }

    @FXML
    void addMember() {
        String text = memberField.getText();
        if (text == null || text.isEmpty()) return;
        carnet.addParticipant(text);
        memberField.clear();
    }

    @FXML
    void deleteMember() {
        carnet.removeParticipant(participantsList.getSelectionModel().getSelectedItem());
    }
}
