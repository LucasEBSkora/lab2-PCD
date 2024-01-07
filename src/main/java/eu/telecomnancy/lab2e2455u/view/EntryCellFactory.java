package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.model.CarnetEntry;
import eu.telecomnancy.lab2e2455u.utils.DDMMYYYYConverter;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.util.Objects;

public class EntryCellFactory implements Callback<ListView<CarnetEntry>, ListCell<CarnetEntry>> {

    private final DDMMYYYYConverter converter;
    private final String path;

    public EntryCellFactory() {
        path = Objects.requireNonNull(EntryCellFactory.class.getResource("/eu/telecomnancy/lab2e2455u/placeholder.png")).toExternalForm();
        converter = new DDMMYYYYConverter();
    }

    private static void formatImage(ImageView img) {
        img.setPreserveRatio(true);
        img.setFitWidth(50);
    }

    @Override
    public ListCell<CarnetEntry> call(ListView<CarnetEntry> param) {
        return new ListCell<>() {

            @Override
            public void updateItem(CarnetEntry entry, boolean empty) {
                super.updateItem(entry, empty);
                if (empty || entry == null) {
                    setText(null);
                } else {

                    String text = converter.toString(entry.date) + ": " + entry.title;

                    ImageView img = null;
                    if (entry.imagePath != null) {
                        try {
                            img = new ImageView(entry.imagePath.toAbsolutePath().toString());
                        } catch (IllegalArgumentException ignored) {
                            text += "(image dans " + entry.imagePath + " pas trouvé)";
                        }

                    }
                    if (img == null) {
                        img = new ImageView(path);
                    }
                    EntryCellFactory.formatImage(img);
                    setText(text);
                    setGraphic(img);
                }
            }
        };
    }
}
