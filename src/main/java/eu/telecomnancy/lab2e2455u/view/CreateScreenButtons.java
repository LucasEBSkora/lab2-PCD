package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CreateScreenButtons {
    private final Main main;

    private final Carnet carnet;

    @FXML
    private Label problemLabel;

    public CreateScreenButtons(Main main, Carnet carnet) {
        this.main = main;
        this.carnet = carnet;
    }

    @FXML
    void saveNotebook() {
        String problem = carnet.problem();
        if (!problem.isEmpty()) {
            problemLabel.setText(problem);
            return;
        }
        carnet.save();
        main.pop();
        main.push(main.makeGlobalScreen(carnet));
    }
}
