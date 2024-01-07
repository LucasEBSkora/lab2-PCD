package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class GlobalScreenFrames extends CarnetDeVoyageView {

    @FXML
    private ListView<String> thumbnailList;

    public GlobalScreenFrames(Main main, Carnet carnet) {
        super(main, carnet);
    }

    @FXML
    void initialize() {
        thumbnailList.setItems(carnet.getParticipants());
    }

    @FXML
    private String buildLabelText() {
        return carnet.getStart().toString() + " - " + carnet.getEnd().toString() + "Auteur: " + carnet.getAuthor();
    }

    @FXML
    private void openEditScreen() {

    }
}
