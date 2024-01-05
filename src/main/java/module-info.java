module eu.telecomnancy.lab2e2455u {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens eu.telecomnancy.lab2e2455u to javafx.fxml;
    exports eu.telecomnancy.lab2e2455u;
    exports eu.telecomnancy.lab2e2455u.view;
    opens eu.telecomnancy.lab2e2455u.view to javafx.fxml;
    opens eu.telecomnancy.lab2e2455u.model to com.google.gson;
}