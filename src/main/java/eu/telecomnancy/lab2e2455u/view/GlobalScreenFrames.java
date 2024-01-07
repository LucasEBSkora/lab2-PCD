package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import eu.telecomnancy.lab2e2455u.model.CarnetEntry;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class GlobalScreenFrames extends CarnetDeVoyageView {

    @FXML
    private ListView<CarnetEntry> thumbnailList;

    public GlobalScreenFrames(Main main, Carnet carnet) {
        super(main, carnet);
    }

    @FXML
    void initialize() {
        thumbnailList.setItems(carnet.getDays());
        thumbnailList.setCellFactory(new EntryCellFactory());
    }

    @FXML
    void enterEntryScreen(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            CarnetEntry entry = thumbnailList.getSelectionModel().getSelectedItem();
            main.push(main.makeEntryScreen(carnet, entry));
            main.show();
        }

    }
}
