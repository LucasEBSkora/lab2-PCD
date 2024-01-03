module eu.telecomnancy.lab2e2455u {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.telecomnancy.lab2e2455u to javafx.fxml;
    exports eu.telecomnancy.lab2e2455u;
    exports eu.telecomnancy.lab2e2455u.controller;
    opens eu.telecomnancy.lab2e2455u.controller to javafx.fxml;
}