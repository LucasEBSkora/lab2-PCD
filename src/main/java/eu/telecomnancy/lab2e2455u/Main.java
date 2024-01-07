package eu.telecomnancy.lab2e2455u;

import eu.telecomnancy.lab2e2455u.model.Carnet;
import eu.telecomnancy.lab2e2455u.model.CarnetEntry;
import eu.telecomnancy.lab2e2455u.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class Main extends Application {

    private final Stack<Scene> scenes;
    private Stage primaryStage;

    public Main() {
        scenes = new Stack<>();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Carnet de voyage!");
        push(makeStartScreen());

        show();
    }

    public Scene makeStartScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ecran-debut.fxml"));
        fxmlLoader.setControllerFactory((ic) -> new StartScreen(this));
        return new Scene(fxmlLoader.load());
    }

    public Scene makeCreateScreen() throws IOException {
        Carnet c = new Carnet();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createNotebookScreen.fxml"));
        fxmlLoader.setControllerFactory((ic) -> {
            if (ic.equals(CreateScreenButtons.class)) return new CreateScreenButtons(this, c);
            else if (ic.equals(CreatorAndParticipantsComponent.class))
                return new CreatorAndParticipantsComponent(this, c);
            else if (ic.equals(DatesComponent.class)) return new DatesComponent(this, c);
            else if (ic.equals(TitleAndPathComponent.class)) return new TitleAndPathComponent(this, c);
            else return null;
        });
        return new Scene(fxmlLoader.load());
    }

    public Scene makeGlobalScreen(Carnet carnet) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("globalScreen.fxml"));
        fxmlLoader.setControllerFactory((ic) -> {
            if (ic.equals(GlobalScreenHeader.class)) return new GlobalScreenHeader(this, carnet);
            else if (ic.equals(GlobalScreenFrames.class)) return new GlobalScreenFrames(this, carnet);
            else if (ic.equals(GlobalScreenFooter.class)) return new GlobalScreenFooter(this, carnet);
            else return null;
        });
        return new Scene(fxmlLoader.load());
    }

    public Scene makeEntryScreen(Carnet carnet, CarnetEntry entry) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("entryScreen.fxml"));
        CarnetEntry temporaryEntry = new CarnetEntry(entry.date);
        temporaryEntry.copy(entry);
        fxmlLoader.setControllerFactory((ic) -> {
            if (ic.equals(EntryScreen.class)) return new EntryScreen(this, carnet, entry, temporaryEntry);
            if (ic.equals(EntryScreenHeader.class)) return new EntryScreenHeader(this, carnet, entry, temporaryEntry);
            else if (ic.equals(EntryScreenPhoto.class))
                return new EntryScreenPhoto(this, carnet, entry, temporaryEntry);
            else if (ic.equals(EntryScreenFooter.class))
                return new EntryScreenFooter(this, carnet, entry, temporaryEntry);
            else return null;
        });
        return new Scene(fxmlLoader.load());
    }

    public Scene pop() {
        return scenes.pop();
    }

    public void push(Scene s) {
        scenes.push(s);
    }

    public void show() {
        primaryStage.setScene(scenes.peek());
        primaryStage.show();
    }

    public void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

}