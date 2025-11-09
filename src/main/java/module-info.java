module com.example.courseworkappelectives {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;

    opens com.example.courseworkappelectives to javafx.fxml;
    opens com.example.courseworkappelectives.controllers to javafx.fxml;
    opens com.example.courseworkappelectives.models to javafx.base, javafx.fxml;

    exports com.example.courseworkappelectives;
}
