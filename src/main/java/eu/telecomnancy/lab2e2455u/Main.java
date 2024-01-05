package eu.telecomnancy.lab2e2455u;

import eu.telecomnancy.lab2e2455u.view.*;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class Main extends Application {

    private Stage primaryStage;

    private final Stack<Scene> scenes;

    public Main() {
        scenes = new Stack<>();
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
    public static void main(String[] args) {
        launch();
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
            else if (ic.equals(CreatorAndParticipantsComponent.class)) return new CreatorAndParticipantsComponent(this, c);
            else if (ic.equals(DatesComponent.class)) return new DatesComponent(this, c);
            else if (ic.equals(TitleAndPathComponent.class)) return new TitleAndPathComponent(this, c);
            else return null;
        });
        return new Scene(fxmlLoader.load());
    }

    public Scene pop() {return scenes.pop();}

    public void push(Scene s) {
        scenes.push(s);
    }

    public void show() {
        primaryStage.setScene(scenes.peek());
        primaryStage.show();
    }

    public Scene makeGlobalScreen(Carnet carnet) {
        return null;
    }
}