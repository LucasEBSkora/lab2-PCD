package eu.telecomnancy.lab2e2455u;

import eu.telecomnancy.lab2e2455u.controller.CreateNotebookScreen;
import eu.telecomnancy.lab2e2455u.controller.StartScreen;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createNotebookScreen.fxml"));
        fxmlLoader.setControllerFactory((ic) -> new CreateNotebookScreen(this));
        return new Scene(fxmlLoader.load());
    }

    public void push(Scene s) {
        scenes.push(s);
    }

    public void show() {
        primaryStage.setScene(scenes.peek());
        primaryStage.show();
    }
}