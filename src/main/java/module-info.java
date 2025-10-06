module com.example.courseworkappelectives {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.courseworkappelectives to javafx.fxml;
    exports com.example.courseworkappelectives;
}