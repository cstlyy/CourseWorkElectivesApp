package com.example.courseworkappelectives.controllers;

import com.example.courseworkappelectives.models.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentController {
    @FXML private Label studentInfoLabel;
    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
        loadStudentInfo();
    }
    private void loadStudentInfo() {
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT surname, user_name FROM users WHERE user_id = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                studentInfoLabel.setText("Студент: " + rs.getString("surname") + " " + rs.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/appforstudents/login.fxml"));
            Stage stage = (Stage) studentInfoLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
