module com.example.courseworkappelectives {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;

    opens com.example.courseworkappelectives to javafx.fxml;
    exports com.example.courseworkappelectives;
    exports com.example.courseworkappelectives.controllers;
    opens com.example.courseworkappelectives.controllers to javafx.fxml;
    exports com.example.courseworkappelectives.models;
    opens com.example.courseworkappelectives.models to javafx.fxml;
}