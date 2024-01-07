package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GlobalScreenHeader extends CarnetDeVoyageView {
    @FXML
    private Label titleLabel;

    public GlobalScreenHeader(Main main, Carnet carnet) {
        super(main, carnet);
    }

    @FXML
    void initialize() {
        titleLabel.setText(carnet.getName());
    }
}
