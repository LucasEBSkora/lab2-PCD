package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class CreateScreenButtons extends CarnetDeVoyageView {

    @FXML
    private Label problemLabel;

    public CreateScreenButtons(Main main, Carnet carnet) {
        super(main, carnet);
    }

    @FXML
    void saveNotebook() throws IOException {
        String problem = carnet.problem();
        if (!problem.isEmpty()) {
            problemLabel.setText(problem);
            return;
        }
        carnet.save();
        main.pop();
        main.push(main.makeGlobalScreen(carnet));
        main.show();
    }
}
